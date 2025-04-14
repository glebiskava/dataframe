package com.Dataframe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.Series.DoubleSeries;
import com.Series.IntSeries;
import com.Series.Series;
import com.Series.StringSeries;

public class CSVLoader {
    public static DataFrame fromCSV(String filePath) throws IOException {
        List<String[]> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                csvData.add(line.split(",(?=([^\"']*[\"'][^\"']*[\"'])*[^\"']*$)", -1));
            }
        } catch (java.io.FileNotFoundException e) {
            throw new IllegalArgumentException("CSV file does not exist: " + filePath);
        }

        if (csvData.isEmpty()) {
            throw new IllegalArgumentException("CSV file is empty.");
        }

        if (csvData.size() == 1) {
            throw new IllegalArgumentException("CSV file has headers but no data rows.");
        }

        String[] headers = Arrays.stream(csvData.get(0)).map(String::trim).toArray(String[]::new);
        int colCount = headers.length;

        List<List<String>> rawColumns = new ArrayList<>();
        for (int i = 0; i < colCount; i++) {
            rawColumns.add(new ArrayList<>());
        }

        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            if (row.length != colCount) {
                throw new IllegalArgumentException("Inconsistent columns in row " + i);
            }
            for (int j = 0; j < colCount; j++) {
                rawColumns.get(j).add(row[j].trim().replaceAll("^['\"]|['\"]$", ""));
            }
        }

        List<Series<?>> parsedColumns = new ArrayList<>();
        for (int i = 0; i < colCount; i++) {
            String header = headers[i];
            List<String> data = rawColumns.get(i);
            DataType type = inferType(data);
            parsedColumns.add(createSeries(header, data, type));
        }

        return new DataFrame(parsedColumns);
    }

    private static DataType inferType(List<String> data) {
        boolean isInteger = true;
        boolean isDouble = true;

        for (String s : data) {
            if (s == null || s.isEmpty() || s.equals("null"))
                continue;
            if (isInteger) {
                try {
                    Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    isInteger = false;
                }
            }
            if (isDouble) {
                try {
                    Double.parseDouble(s);
                } catch (NumberFormatException e) {
                    isDouble = false;
                }
            }
        }

        if (isInteger)
            return DataType.INTEGER;
        if (isDouble)
            return DataType.DOUBLE;
        return DataType.STRING;
    }

    private static Series<?> createSeries(String name, List<String> data, DataType type) {
        List<String> sanitizedData = data.stream().map(s -> s.isEmpty() || s.equals("null") ? null : s)
                .collect(Collectors.toList());
        switch (type) {
            case INTEGER:
                Integer[] intData = sanitizedData.stream().map(s -> s == null ? null : Integer.parseInt(s))
                        .toArray(Integer[]::new);
                return new IntSeries(name, intData);
            case DOUBLE:
                Double[] doubleData = sanitizedData.stream().map(s -> s == null ? null : Double.parseDouble(s))
                        .toArray(Double[]::new);
                return new DoubleSeries(name, doubleData);
            default:
                String[] strData = sanitizedData.toArray(new String[0]);
                return new StringSeries(name, strData);
        }
    }
}