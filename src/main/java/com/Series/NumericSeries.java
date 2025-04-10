package com.Series;

public interface NumericSeries <T extends Number> {
    double mean();

    T sum();

    T min();

    T max();
}

