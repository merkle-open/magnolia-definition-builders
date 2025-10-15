package com.merkle.oss.magnolia.definition.custom.richtext.config.table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.ColorOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.ui.ColorPickerConfig;

public class TableCellPropertiesConfig {
    public final List<ColorOption> borderColors;
    public final List<ColorOption> backgroundColors;
    @Nullable
    public final TableCellPropertiesOptions defaultProperties;
    @Nullable
    public final ColorPickerConfig colorPicker;

    public TableCellPropertiesConfig(
            final List<ColorOption> borderColors,
            final List<ColorOption> backgroundColors,
            @Nullable final TableCellPropertiesOptions defaultProperties,
            @Nullable final ColorPickerConfig colorPicker
    ) {
        this.borderColors = borderColors;
        this.backgroundColors = backgroundColors;
        this.defaultProperties = defaultProperties;
        this.colorPicker = colorPicker;
    }

    public static class Builder {
        @Nullable
        private List<ColorOption> borderColors;
        @Nullable
        private List<ColorOption> backgroundColors;
        @Nullable
        private TableCellPropertiesOptions defaultProperties;
        @Nullable
        private ColorPickerConfig colorPicker;

        public Builder() {}
        public Builder(final TableCellPropertiesConfig tableCellPropertiesConfig) {
            this.borderColors = tableCellPropertiesConfig.borderColors;
            this.backgroundColors = tableCellPropertiesConfig.backgroundColors;
            this.defaultProperties = tableCellPropertiesConfig.defaultProperties;
            this.colorPicker = tableCellPropertiesConfig.colorPicker;
        }

        public Builder borderColor(final ColorOption color) {
            return borderColors(Stream.concat(
                    Stream.ofNullable(this.borderColors).flatMap(Collection::stream),
                    Stream.of(color)
            ).collect(Collectors.toList()));
        }
        public Builder borderColors(final List<ColorOption> borderColors) {
            this.borderColors = borderColors;
            return this;
        }

        public Builder backgroundColor(final ColorOption color) {
            return borderColors(Stream.concat(
                    Stream.ofNullable(this.backgroundColors).flatMap(Collection::stream),
                    Stream.of(color)
            ).collect(Collectors.toList()));
        }
        public Builder backgroundColors(final List<ColorOption> backgroundColors) {
            this.backgroundColors = backgroundColors;
            return this;
        }

        public Builder defaultProperties(final TableCellPropertiesOptions defaultProperties) {
            this.defaultProperties = defaultProperties;
            return this;
        }

        public Builder colorPicker(final ColorPickerConfig colorPicker) {
            this.colorPicker = colorPicker;
            return this;
        }

        public TableCellPropertiesConfig build() {
            return new TableCellPropertiesConfig(
                    Optional.ofNullable(borderColors).orElseGet(Collections::emptyList),
                    Optional.ofNullable(backgroundColors).orElseGet(Collections::emptyList),
                    defaultProperties,
                    colorPicker
            );
        }
    }
}
