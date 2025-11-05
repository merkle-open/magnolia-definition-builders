package com.merkle.oss.magnolia.definition.custom.richtext.config.font;


import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontSize.Option;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

public class FontSize {
    public final List<Option> options;
    public final boolean supportAllValues;

    private FontSize(final List<Option> options, final boolean supportAllValues) {
        this.options = options;
        this.supportAllValues = supportAllValues;
    }

    public static class Builder {
        @Nullable
        private List<Option> sizes;
        private boolean supportAllValues;

        public Builder size(final String title, final String model) {
            return size(new Option(title + "/" + model.trim()));
        }
        private Builder size(final Option size) {
            return sizes(Stream.concat(
                    Stream.ofNullable(sizes).flatMap(Collection::stream),
                    Stream.of(size)
            ).collect(Collectors.toList()));
        }
        public Builder sizes(final List<Option> sizes) {
            this.sizes = sizes;
            return this;
        }

        public Builder supportAllValues(final boolean supportAllValues) {
            this.supportAllValues = supportAllValues;
            return this;
        }

        public FontSize build() {
            return new FontSize(
                    Stream.ofNullable(sizes)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList()),
                    supportAllValues
            );
        }
    }
}
