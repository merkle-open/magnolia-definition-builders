package com.merkle.oss.magnolia.definition.custom.richtext.config.ui;

public enum VerticalAlignment {
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom");

    private final String value;

    VerticalAlignment(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
