package com.pluralsight.deli.options;

public enum SandwichSize {
    FOUR_INCHES(4),
    EIGHT_INCHES(8),
    TWELVE_INCHES(12);

    private final int size;

    private SandwichSize(int value) {
        this.size = value;
    }

    public int getSize() {
        return size;
    }
}
