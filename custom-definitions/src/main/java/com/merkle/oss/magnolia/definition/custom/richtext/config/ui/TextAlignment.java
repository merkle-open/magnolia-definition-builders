package com.merkle.oss.magnolia.definition.custom.richtext.config.ui;

public enum TextAlignment {
    LEFT("left"),
    CENTER("center"),
    RIGHT("right"),
    JUSTIFY("justify");

    private final String value;

    TextAlignment(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
