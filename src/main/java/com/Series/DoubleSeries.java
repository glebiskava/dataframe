package com.Series;

import java.util.Arrays;
import java.util.Objects;

public class DoubleSeries extends Series<Double> implements NumericSeries<Double> {
    private final String name;
    private final Double[] data;

    public DoubleSeries(String name, Double[] data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public Series<Double> select(Integer[] indices) {
        Double[] newData = new Double[indices.length];
        for (int i = 0; i < indices.length; i++)
            newData[i] = data[indices[i]];
        return new DoubleSeries(name, newData);
    }

    @Override
    public Double get(int index) {
        return data[index];
    }

    @Override
    public int count() {
        return (int) Arrays.stream(data).filter(Objects::nonNull).count();
    }

    @Override
    public String getType() {
        return "Double";
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
        return Arrays.stream(data).filter(Objects::nonNull).mapToDouble(Double::doubleValue).average().orElse(0);
    }

    @Override
    public Double sum() {
        return Arrays.stream(data).filter(Objects::nonNull).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public Double min() {
        return Arrays.stream(data).filter(Objects::nonNull).mapToDouble(Double::doubleValue).min().orElse(0);
    }

    @Override
    public Double max() {
        return Arrays.stream(data).filter(Objects::nonNull).mapToDouble(Double::doubleValue).max().orElse(0);
    }
}