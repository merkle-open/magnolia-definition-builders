package com.merkle.oss.magnolia.definition.custom.richtext.config.ui;

public class ColorPickerConfig {
    public final String format;

    public ColorPickerConfig(final ColorPickerOutputFormat format) {
        this.format = format.value;
    }

    public enum ColorPickerOutputFormat {
        HEX("hex"),
        RGB("rgb"),
        HSL("hsl"),
        HWB("hwb"),
        LAB("lab"),
        LCH("lch");

        private final String value;

        ColorPickerOutputFormat(final String value) {
            this.value = value;
        }
    }
}
