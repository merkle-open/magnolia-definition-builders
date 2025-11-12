package com.merkle.oss.magnolia.definition.custom.richtext.config.table;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.BorderStyle;
import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.TextAlignment;

public class TablePropertiesOptions {
    @Nullable
    public final String width;
    @Nullable
    public final String height;
    @Nullable
    public final String backgroundColor;
    @Nullable
    public final String borderColor;
    @Nullable
    public final String borderWidth;
    @Nullable
    public final String borderStyle;
    @Nullable
    public final String alignment;

    public TablePropertiesOptions(
            @Nullable final String width,
            @Nullable final String height,
            @Nullable final String backgroundColor,
            @Nullable final String borderColor,
            @Nullable final String borderWidth,
            @Nullable final String borderStyle,
            @Nullable final String alignment
    ) {
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.borderStyle = borderStyle;
        this.alignment = alignment;
    }

    public static class Builder {
        @Nullable
        private String width;
        @Nullable
        private String height;
        @Nullable
        private String backgroundColor;
        @Nullable
        private String borderColor;
        @Nullable
        private String borderWidth;
        @Nullable
        private String borderStyle;
        @Nullable
        private String alignment;

        public Builder() {}
        public Builder(final TablePropertiesOptions tablePropertiesOptions) {
            this.width = tablePropertiesOptions.width;
            this.height = tablePropertiesOptions.height;
            this.backgroundColor = tablePropertiesOptions.backgroundColor;
            this.borderColor = tablePropertiesOptions.borderColor;
            this.borderWidth = tablePropertiesOptions.borderWidth;
            this.borderStyle = tablePropertiesOptions.borderStyle;
            this.alignment = tablePropertiesOptions.alignment;
        }

        public Builder width(final String width) {
            this.width = width;
            return this;
        }
        public Builder height(final String height) {
            this.height = height;
            return this;
        }
        public Builder backgroundColor(final String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }
        public Builder borderColor(final String borderColor) {
            this.borderColor = borderColor;
            return this;
        }
        public Builder borderWidth(final String borderWidth) {
            this.borderWidth = borderWidth;
            return this;
        }
        public Builder borderStyle(final BorderStyle borderStyle) {
            this.borderStyle = borderStyle.getValue();
            return this;
        }
        public Builder alignment(final TextAlignment alignment) {
            this.alignment = alignment.getValue();
            return this;
        }

        public TablePropertiesOptions build() {
            return new TablePropertiesOptions(
                    width,
                    height,
                    backgroundColor,
                    borderColor,
                    borderWidth,
                    borderStyle,
                    alignment
            );
        }
    }
}
