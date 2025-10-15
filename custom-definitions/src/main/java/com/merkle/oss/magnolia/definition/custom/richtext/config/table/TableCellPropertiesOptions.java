package com.merkle.oss.magnolia.definition.custom.richtext.config.table;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.BorderStyle;
import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.TextAlignment;
import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.VerticalAlignment;

public class TableCellPropertiesOptions {
    @Nullable
    public final String width;
    @Nullable
    public final String height;
    @Nullable
    public final String padding;
    @Nullable
    public final String backgroundColor;
    @Nullable
    public final String borderColor;
    @Nullable
    public final String borderWidth;
    @Nullable
    public final String borderStyle;
    @Nullable
    public final String horizontalAlignment;
    @Nullable
    public final String verticalAlignment;

    public TableCellPropertiesOptions(
            @Nullable final String width,
            @Nullable final String height,
            @Nullable final String padding,
            @Nullable final String backgroundColor,
            @Nullable final String borderColor,
            @Nullable final String borderWidth,
            @Nullable final String borderStyle,
            @Nullable final String horizontalAlignment,
            @Nullable final String verticalAlignment
    ) {
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.borderStyle = borderStyle;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public static class Builder {
        @Nullable
        private String width;
        @Nullable
        private String height;
        @Nullable
        private String padding;
        @Nullable
        private String backgroundColor;
        @Nullable
        private String borderColor;
        @Nullable
        private String borderWidth;
        @Nullable
        private String borderStyle;
        @Nullable
        private String horizontalAlignment;
        @Nullable
        private String verticalAlignment;

        public Builder() {}
        public Builder(final TableCellPropertiesOptions tableCellPropertiesOptions) {
            this.width = tableCellPropertiesOptions.width;
            this.height = tableCellPropertiesOptions.height;
            this.padding = tableCellPropertiesOptions.padding;
            this.backgroundColor = tableCellPropertiesOptions.backgroundColor;
            this.borderColor = tableCellPropertiesOptions.borderColor;
            this.borderWidth = tableCellPropertiesOptions.borderWidth;
            this.borderStyle = tableCellPropertiesOptions.borderStyle;
            this.horizontalAlignment = tableCellPropertiesOptions.horizontalAlignment;
            this.verticalAlignment = tableCellPropertiesOptions.verticalAlignment;
        }


        public Builder width(final String width) {
            this.width = width;
            return this;
        }
        public Builder height(final String height) {
            this.height = height;
            return this;
        }
        public Builder padding(final String padding) {
            this.padding = padding;
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
        public Builder horizontalAlignment(final TextAlignment horizontalAlignment) {
            this.horizontalAlignment = horizontalAlignment.getValue();
            return this;
        }
        public Builder verticalAlignment(final VerticalAlignment verticalAlignment) {
            this.verticalAlignment = verticalAlignment.getValue();
            return this;
        }

        public TableCellPropertiesOptions build() {
            return new TableCellPropertiesOptions(
                    width,
                    height,
                    padding,
                    backgroundColor,
                    borderColor,
                    borderWidth,
                    borderStyle,
                    horizontalAlignment,
                    verticalAlignment
            );
        }
    }
}
