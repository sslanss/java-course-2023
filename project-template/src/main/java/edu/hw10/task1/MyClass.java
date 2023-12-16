package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class MyClass {

    private byte byteField;

    private short shortField;

    private int intField;

    private double doubleField;

    private String stringField;

    public MyClass(
        @Min(value = 20) @Max(value = 40) byte byteField,
        @Min(value = Short.MIN_VALUE) @Max(value = Short.MAX_VALUE) short shortField,
        @Min(value = Integer.MIN_VALUE) @Max(value = Integer.MAX_VALUE) int intField,
        @Min(value = Double.MIN_VALUE) @Max(value = Double.MAX_VALUE) double doubleField,
        @NotNull String stringField
    ) {
        this.byteField = byteField;
        this.shortField = shortField;
        this.intField = intField;
        this.doubleField = doubleField;
        this.stringField = stringField;
    }

    public MyClass() {
        stringField = "";
    }

    public static MyClass create(
        @Min(value = 20) @Max(value = 40) byte byteField,
        @Min(value = Short.MIN_VALUE) @Max(value = Short.MAX_VALUE) short shortField,
        @Min(value = Integer.MIN_VALUE) @Max(value = Integer.MAX_VALUE) int intField,
        @Min(value = Double.MIN_VALUE) @Max(value = Double.MAX_VALUE) double doubleField,
        @NotNull String stringField
    ) {
        return new MyClass(byteField, shortField, intField, doubleField, stringField);
    }

    public byte getByteField() {
        return byteField;
    }

    public short getShortField() {
        return shortField;
    }

    public int getIntField() {
        return intField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public String getStringField() {
        return stringField;
    }

}
