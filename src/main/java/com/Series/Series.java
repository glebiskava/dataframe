package com.Series;

public abstract class Series<T> {
    protected final String name;
    protected final T[] data;

    public Series(String name, T[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public abstract T get(int index);

    public abstract Series<T> select(Integer[] indices);

    public int size() {
        return data.length;
    }

    public abstract String getType();
}

