package org.example.model;

public class LineData {
    private final String value;
    private final int index;

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public LineData(String value, int index) {
        this.value = value;
        this.index = index;
    }
}
