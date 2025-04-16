package com.Series;
    
import java.util.Arrays;
import java.util.Objects;

public class StringSeries extends Series<String> {
    private final String name;
    private final String[] data;

    public StringSeries(String name, String[] data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public Series<String> select(Integer[] indices) {
        String[] newData = new String[indices.length];
        for (int i = 0; i < indices.length; i++)
            newData[i] = data[indices[i]];
        return new StringSeries(name, newData);
    }

    @Override
    public String get(int index) {
        return data[index];
    }

    @Override
    public int count() {
        return (int) Arrays.stream(data).filter(Objects::nonNull).count();
    }

    @Override
    public String getType() {
        return "String";
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public String getName() {
        return name;
    }
}
