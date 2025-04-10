package com.Series;

public class IntSeries extends Series<Integer> {
    public IntSeries(String name, Integer[] data) {
        super(name, data);
    }

    @Override
    public Integer get(int index) {
        return data[index];
    }

    @Override
    public Series<Integer> select(Integer[] indices) {
        Integer[] selectedData = new Integer[indices.length];
        for (int i = 0; i < indices.length; i++) {
            selectedData[i] = data[indices[i]];
        }
        return new IntSeries(name, selectedData);
    }

    @Override
    public String getType() {
        return "Integer";
    }
}
