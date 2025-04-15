package com.Series;

public abstract class Series<T> {
    public abstract Series<?> select(Integer[] indices);

    public abstract int count();

    public abstract T get(int index);

    public abstract String getType();

    public abstract int size();

    public abstract String getName();
}

