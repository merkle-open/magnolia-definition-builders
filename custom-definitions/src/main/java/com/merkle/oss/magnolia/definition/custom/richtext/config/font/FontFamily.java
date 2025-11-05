package com.merkle.oss.magnolia.definition.custom.richtext.config.font;

import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontFamily.Option;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

public class FontFamily {
    public final List<Option> options;
    public final boolean supportAllValues;

    private FontFamily(final List<Option> options, final boolean supportAllValues) {
        this.options = options;
        this.supportAllValues = supportAllValues;
    }

    public static class Builder {
        @Nullable
        private List<Option> fonts;
        private boolean supportAllValues;

        public Builder font(final String font) {
            return font(font, font);
        }
        public Builder font(final String label, final String font) {
            return font(new Option(label + "/" + font.trim()));
        }
        private Builder font(final Option font) {
            return fonts(Stream.concat(
                    Stream.ofNullable(fonts).flatMap(Collection::stream),
                    Stream.of(font)
            ).collect(Collectors.toList()));
        }

        private Builder fonts(final List<Option> fonts) {
            this.fonts = fonts;
            return this;
        }

        public Builder supportAllValues(final boolean supportAllValues) {
            this.supportAllValues = supportAllValues;
            return this;
        }

        public FontFamily build() {
            return new FontFamily(
                    Stream.ofNullable(fonts)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList()),
                    supportAllValues
            );
        }
    }
}
