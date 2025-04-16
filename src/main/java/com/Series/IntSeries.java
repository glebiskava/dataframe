package com.Series;

import java.util.Arrays;
import java.util.Objects;

public class IntSeries extends Series<Integer> implements NumericSeries<Integer> {
    private final String name;
    private final Integer[] data;

    public IntSeries(String name, Integer[] data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public Series<Integer> select(Integer[] indices) {
        Integer[] newData = new Integer[indices.length];
        for (int i = 0; i < indices.length; i++)
            newData[i] = data[indices[i]];
        return new IntSeries(name, newData);
    }

    @Override
    public Integer get(int index) {
        return data[index];
    }

    @Override
    public int count() {
        return (int) Arrays.stream(data).filter(Objects::nonNull).count();
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double mean() {
        return Arrays.stream(data).filter(Objects::nonNull).mapToInt(Integer::intValue).average().orElse(0);
    }

    @Override
    public Integer sum() {
        return Arrays.stream(data).filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
    }

    @Override
    public Integer min() {
        return Arrays.stream(data).filter(Objects::nonNull).mapToInt(Integer::intValue).min().orElse(0);
    }

    @Override
    public Integer max() {
        return Arrays.stream(data).filter(Objects::nonNull).mapToInt(Integer::intValue).max().orElse(0);
    }
}