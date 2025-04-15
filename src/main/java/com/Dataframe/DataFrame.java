package com.Dataframe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Series.NumericSeries;
import com.Series.Series;

public class DataFrame {
    private final List<Series<?>> columns;
    private final int rowCount;
    private final Map<String, Series<?>> columnMap;

    public DataFrame(List<Series<?>> columns) {
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

    public void print() {
        DataFramePrinter.print(this, 0, rowCount);
    }

    public void head(int n) {
        DataFramePrinter.print(this, 0, Math.min(n, rowCount));
    }

    public void tail(int n) {
        DataFramePrinter.print(this, Math.max(0, rowCount - n), rowCount);
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

    public DataFrame filter(String condition) {
        Condition c = parseCondition(condition);
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            if (evaluateCondition(c, i))
                indices.add(i);
        }
        int[] indexArray = indices.stream().mapToInt(Integer::intValue).toArray();
        return selectRows(indexArray);
    }

    private static Condition parseCondition(String condition) {
        Pattern pattern = Pattern.compile("(\\w+)\\s*([><=!]+)\\s*([\\w.]+)");
        Matcher matcher = pattern.matcher(condition.trim());
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid condition format");
        return new Condition(matcher.group(1), matcher.group(2), matcher.group(3));
    }

    private boolean evaluateCondition(Condition c, int row) {
        Series<?> series = columnMap.get(c.column);
        Object value = series.get(row);
        if (value == null)
            return false;

        switch (series.getType()) {
            case "Integer":
                return compareInt((Integer) value, c.operator, Integer.parseInt(c.value));
            case "Double":
                return compareDouble((Double) value, c.operator, Double.parseDouble(c.value));
            case "String":
                return compareString(value.toString(), c.operator, c.value);
            default:
                throw new IllegalArgumentException("Unsupported column type for filtering");
        }
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

    public Series<?> getColumn(String name) {
        return columnMap.get(name);
    }

    public double mean(String column) {
        Series<?> series = columnMap.get(column);
        if (!(series instanceof NumericSeries<?>))
            throw new IllegalArgumentException("Column is not numeric.");
        return ((NumericSeries<?>) series).mean();
    }

    public double max(String column) {
        Series<?> series = columnMap.get(column);
        if (!(series instanceof NumericSeries<?>))
            throw new IllegalArgumentException("Column is not numeric.");
        return ((NumericSeries<?>) series).max().doubleValue();
    }

    public double min(String column) {
        Series<?> series = columnMap.get(column);
        if (!(series instanceof NumericSeries<?>))
            throw new IllegalArgumentException("Column is not numeric.");
        return ((NumericSeries<?>) series).min().doubleValue();
    }

    public double sum(String column) {
        Series<?> series = columnMap.get(column);
        if (!(series instanceof NumericSeries<?>))
            throw new IllegalArgumentException("Column is not numeric.");
        return ((NumericSeries<?>) series).sum().doubleValue();
    }

    public long count(String column) {
        return columnMap.get(column).count();
    }

    public int size() {
        return rowCount;
    }

}   