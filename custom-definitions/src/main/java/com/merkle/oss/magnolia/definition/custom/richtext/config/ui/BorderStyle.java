package com.merkle.oss.magnolia.definition.custom.richtext.config.ui;

public enum BorderStyle {
    NONE("none"),
    DOTTED("dotted"),
    DASHED("dashed"),
    SOLID("solid"),
    DOUBLE("double"),
    GROOVE("groove"),
    RIDGE("ridge"),
    INSET("inset"),
    OUTSET("outset");

    private final String value;

    BorderStyle(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
