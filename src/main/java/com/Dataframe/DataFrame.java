package com.Dataframe;

import java.util.*;
import com.Series.*;

public class DataFrame {
    private final List<Series<?>> columns;
    private final int rowCount;
    private final Map<String, Series<?>> columnMap;

    DataFrame(List<Series<?>> columns) {
        this.columns = new ArrayList<>(columns);
        this.columnMap = new HashMap<>();
        for (Series<?> series : columns) {
            columnMap.put(series.getName(), series);
        }
        this.rowCount = columns.isEmpty() ? 0 : columns.get(0).size();
    }

    public String[] getHeaders() {
        return columns.stream().map(Series::getName).toArray(String[]::new);
    }

    public String[][] getRows(int start, int end) {
        start = Math.max(start, 0);
        end = Math.min(end, rowCount);
        String[][] rows = new String[end - start][columns.size()];
        for (int i = start; i < end; i++) {
            for (int j = 0; j < columns.size(); j++) {
                Object val = columns.get(j).get(i);
                rows[i - start][j] = val == null ? "null" : val.toString();
            }
        }
        return rows;
    }

    public DataFrame selectRows(int... indices) {
        Integer[] validIndices = Arrays.stream(indices).filter(i -> i >= 0 && i < rowCount).boxed()
                .toArray(Integer[]::new);
        List<Series<?>> newColumns = new ArrayList<>();
        for (Series<?> col : columns) {
            newColumns.add(col.select(validIndices));
        }
        return new DataFrame(newColumns);
    }

    public DataFrame selectColumns(String... columnNames) {
        List<Series<?>> newColumns = new ArrayList<>();
        for (String name : columnNames) {
            if (!columnMap.containsKey(name))
                throw new IllegalArgumentException("Column not found: " + name);
            newColumns.add(columnMap.get(name));
        }
        return new DataFrame(newColumns);
    }

    public Series<?> getColumn(String name) {
        return columnMap.get(name);
    }
    private boolean compareInt(int a, String op, int b) {
        switch (op) {
            case ">":
                return a > b;
            case "<":
                return a < b;
            case ">=":
                return a >= b;
            case "<=":
                return a <= b;
            case "=":
                return a == b;
            case "!=":
                return a != b;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + op);
        }
    }

    private boolean compareDouble(double a, String op, double b) {
        switch (op) {
            case ">":
                return a > b;
            case "<":
                return a < b;
            case ">=":
                return a >= b;
            case "<=":
                return a <= b;
            case "=":
                return a == b;
            case "!=":
                return a != b;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + op);
        }
    }

    private boolean compareString(String a, String op, String b) {
        switch (op) {
            case "=":
                return a.equals(b);
            case "!=":
                return !a.equals(b);
            default:
                throw new IllegalArgumentException("Unsupported operator for String: " + op);
        }
    }
}