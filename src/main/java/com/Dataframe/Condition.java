package com.Dataframe;

public class Condition {
    public final String column;
    public final String operator;
    public final String value;

    public Condition(String column, String operator, String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }
}