package com.Series;

public class DoubleSeries extends Series<Double> {
    public DoubleSeries(String name, Double[] data) {
        super(name, data);
    }

    @Override
    public Double get(int index) {
        return data[index];
    }

    @Override
    public Series<Double> select(Integer[] indices) {
        Double[] selectedData = new Double[indices.length];
        for (int i = 0; i < indices.length; i++) {
            selectedData[i] = data[indices[i]];
        }
        return new DoubleSeries(name, selectedData);
    }

    @Override
    public String getType() {
        return "Double";
    }
}
