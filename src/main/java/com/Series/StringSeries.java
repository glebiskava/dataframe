package com.Series;

public class StringSeries extends Series<String> {
    public StringSeries(String name, String[] data) {
        super(name, data);
    }

    @Override
    public String get(int index) {
        return data[index];
    }

    @Override
    public Series<String> select(Integer[] indices) {
        String[] selectedData = new String[indices.length];
        for (int i = 0; i < indices.length; i++) {
            selectedData[i] = data[indices[i]];
        }
        return new StringSeries(name, selectedData);
    }

    @Override
    public String getType() {
        return "String";
    }
}
