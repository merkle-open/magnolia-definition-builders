package com.merkle.oss.magnolia.definition.custom.richtext.config.font;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.ColorPickerConfig;

public class FontColor {
    @Nullable
    public final ColorPickerConfig colorPicker;
    public final List<Option> colors;
    @Nullable
    public final Integer columns;
    @Nullable
    public final Integer documentColors;

    public FontColor(
            @Nullable final ColorPickerConfig colorPicker,
            final List<Option> colors,
            @Nullable final Integer columns,
            @Nullable final Integer documentColors
    ) {
        this.colorPicker = colorPicker;
        this.colors = colors;
        this.columns = columns;
        this.documentColors = documentColors;
    }

    public static class Option {
        public final String color;
        public final String label;

        public Option(final String label, final String color) {
            this.color = color;
            this.label = label;
        }
    }

    public static class Builder {
        @Nullable
        private List<Option> colors;
        @Nullable
        private ColorPickerConfig colorPicker;
        @Nullable
        private Integer columns;
        @Nullable
        private Integer documentColors;

        public Builder color(final String label, final int red, final int green, final int blue) {
            return color(label, "rgb(" + red + ", " + green + ", " + blue + ")");
        }
        public Builder color(final String label, final String color) {
            return color(new Option(label, color.trim()));
        }
        public Builder color(final Option color) {
            return colors(Stream.concat(
                    Stream.ofNullable(colors).flatMap(Collection::stream),
                    Stream.of(color)
            ).collect(Collectors.toList()));
        }
        public Builder colors(final List<Option> colors) {
            this.colors = colors;
            return this;
        }

        public Builder colorPicker(@Nullable final ColorPickerConfig colorPicker) {
            this.colorPicker = colorPicker;
            return this;
        }

        public Builder columns(final int columns) {
            this.columns = columns;
            return this;
        }

        public Builder documentColors(final int documentColors) {
            this.documentColors = documentColors;
            return this;
        }

        public FontColor build() {
            return new FontColor(
                    colorPicker,
                    Stream.ofNullable(colors)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList()),
                    columns,
                    documentColors
            );
        }
    }
}
