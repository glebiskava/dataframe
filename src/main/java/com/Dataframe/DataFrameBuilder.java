package com.Dataframe;

import java.util.ArrayList;
import java.util.List;

import com.Series.DoubleSeries;
import com.Series.IntSeries;
import com.Series.Series;
import com.Series.StringSeries;

public class DataFrameBuilder {
    private final List<Series<?>> columns = new ArrayList<>();

    public DataFrameBuilder addStringColumn(String name, String[] data) {
        columns.add(new StringSeries(name, data));
        return this;
    }

    public DataFrameBuilder addIntColumn(String name, Integer[] data) {
        columns.add(new IntSeries(name, data));
        return this;
    }

    public DataFrameBuilder addDoubleColumn(String name, Double[] data) {
        columns.add(new DoubleSeries(name, data));
        return this;
    }

    public DataFrame build() {
        if (columns.isEmpty()) {
            throw new IllegalArgumentException("No columns added.");
        }
        int rowCount = columns.get(0).size();
        for (Series<?> col : columns) {
            if (col.size() != rowCount) {
                throw new IllegalArgumentException("All columns must have the same number of rows.");
            }
        }
        return new DataFrame(columns);
    }
}