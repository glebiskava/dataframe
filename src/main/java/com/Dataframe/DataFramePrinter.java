package com.Dataframe;

public class DataFramePrinter {
    public static void print(DataFrame dataFrame, int start, int end) {
        String[] headers = dataFrame.getHeaders();
        String[][] rows = dataFrame.getRows(start, end);
        printTable(headers, rows);
    }

    private static void printTable(String[] headers, String[][] rows) {
        int[] colWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            colWidths[i] = headers[i].length();
            for (String[] row : rows) {
                if (row[i] != null) {
                    colWidths[i] = Math.max(colWidths[i], row[i].length());
                }
            }
        }

        StringBuilder format = new StringBuilder();
        for (int w : colWidths) {
            format.append("%-").append(w + 2).append("s");
        }
        format.append("\n");

        System.out.printf(format.toString(), (Object[]) headers);
        for (String[] row : rows) {
            System.out.printf(format.toString(), (Object[]) row);
        }
    }
}