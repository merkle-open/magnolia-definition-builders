package com.merkle.oss.magnolia.definition.custom.richtext.config.ui;

import javax.annotation.Nullable;

public class ColorOption {
    public final String color;
    @Nullable
    public final String label;
    public final boolean hasBorder;

    public ColorOption(final String color, @Nullable final String label, final boolean hasBorder) {
        this.color = color;
        this.label = label;
        this.hasBorder = hasBorder;
    }
}
