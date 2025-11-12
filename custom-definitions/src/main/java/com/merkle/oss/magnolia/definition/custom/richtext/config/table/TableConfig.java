package com.merkle.oss.magnolia.definition.custom.richtext.config.table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

// See implementation here:
// https://github.com/ckeditor/ckeditor5/blob/v41.4.2/packages/ckeditor5-table/src/tableconfig.ts
public class TableConfig {
    @Nullable
    public final DefaultHeadings defaultHeadings;
    public final List<ToolbarConfigItem> contentToolbar;
    public final List<ToolbarConfigItem> tableToolbar;
    @Nullable
    public final TablePropertiesConfig tableProperties;
    @Nullable
    public final TableCellPropertiesConfig tableCellProperties;
    @Nullable
    public final TableLayoutConfig tableLayout;
    @Nullable
    public final TableCaptionConfig tableCaption;
    public final boolean allowNesting;

    private TableConfig(
            @Nullable final DefaultHeadings defaultHeadings,
            @Nullable final List<ToolbarConfigItem> contentToolbar,
            @Nullable final List<ToolbarConfigItem> tableToolbar,
            @Nullable final TablePropertiesConfig tableProperties,
            @Nullable final TableCellPropertiesConfig tableCellProperties,
            @Nullable final TableLayoutConfig tableLayout,
            @Nullable final TableCaptionConfig tableCaption,
            final boolean allowNesting
    ) {
        this.defaultHeadings = defaultHeadings;
        this.contentToolbar = contentToolbar;
        this.tableToolbar = tableToolbar;
        this.tableProperties = tableProperties;
        this.tableCellProperties = tableCellProperties;
        this.tableLayout = tableLayout;
        this.tableCaption = tableCaption;
        this.allowNesting = allowNesting;
    }

    public static class Builder {
        @Nullable
        private DefaultHeadings defaultHeadings;
        @Nullable
        private List<ToolbarConfigItem> contentToolbar;
        @Nullable
        private List<ToolbarConfigItem> tableToolbar;
        @Nullable
        private TablePropertiesConfig tableProperties;
        @Nullable
        private TableCellPropertiesConfig tableCellProperties;
        @Nullable
        private TableLayoutConfig tableLayout;
        @Nullable
        private TableCaptionConfig tableCaption;
        private boolean allowNesting;

        public Builder() {}
        public Builder(final TableConfig tableConfig) {
            this.defaultHeadings = tableConfig.defaultHeadings;
            this.contentToolbar = tableConfig.contentToolbar;
            this.tableToolbar = tableConfig.tableToolbar;
            this.tableProperties = tableConfig.tableProperties;
            this.tableCellProperties = tableConfig.tableCellProperties;
        }

        public Builder defaultHeadings(final DefaultHeadings defaultHeadings) {
            this.defaultHeadings = defaultHeadings;
            return this;
        }

        public Builder contentToolbarItem(final String contentToolbarItem) {
            return contentToolbarItem(new ToolbarConfigItem(contentToolbarItem), false);
        }
        public Builder contentToolbarItem(final ToolbarConfigItem.NestedToolbarConfigItem contentToolbarItem) {
            return contentToolbarItem(new ToolbarConfigItem(contentToolbarItem), true);
        }
        public Builder contentToolbarItem(final ToolbarConfigItem contentToolbarItem, final boolean flatten) {
            return contentToolbarItems(Stream.concat(
                    Stream.ofNullable(this.contentToolbar).flatMap(Collection::stream),
                    flatten ? contentToolbarItem.flatten() : Stream.of(contentToolbarItem)
            ).collect(Collectors.toList()), false);
        }
        public Builder contentToolbarItems(final List<ToolbarConfigItem> contentToolbar, final boolean flatten) {
            this.contentToolbar = contentToolbar.stream().flatMap(item ->
                    flatten ? item.flatten() : Stream.of(item)
            ).collect(Collectors.toList());
            return this;
        }

        public Builder tableToolbarItem(final String tableToolbarItem) {
            return tableToolbarItem(new ToolbarConfigItem(tableToolbarItem), false);
        }
        public Builder tableToolbarItem(final ToolbarConfigItem.NestedToolbarConfigItem tableToolbarItem) {
            return tableToolbarItem(new ToolbarConfigItem(tableToolbarItem), true);
        }
        public Builder tableToolbarItem(final ToolbarConfigItem tableToolbarItem, final boolean flatten) {
            return tableToolbarItems(Stream.concat(
                    Stream.ofNullable(this.tableToolbar).flatMap(Collection::stream),
                    flatten ? tableToolbarItem.flatten() : Stream.of(tableToolbarItem)
            ).collect(Collectors.toList()), false);
        }
        public Builder tableToolbarItems(final List<ToolbarConfigItem> tableToolbar, final boolean flatten) {
            this.tableToolbar = tableToolbar.stream().flatMap(item ->
                    flatten ? item.flatten() : Stream.of(item)
            ).collect(Collectors.toList());
            return this;
        }

        public Builder tableProperties(final TablePropertiesConfig tableProperties){
            this.tableProperties = tableProperties;
            return this;
        }

        public Builder tableCellProperties(final TableCellPropertiesConfig tableCellProperties) {
            this.tableCellProperties = tableCellProperties;
            return this;
        }

        public Builder tableLayoutConfig(final TableLayoutConfig tableLayout) {
            this.tableLayout = tableLayout;
            return this;
        }

        public Builder tableCaptionConfig(final TableCaptionConfig tableCaption) {
            this.tableCaption = tableCaption;
            return this;
        }

        public Builder allowNesting(final boolean allowNesting) {
            this.allowNesting = allowNesting;
            return this;
        }

        public TableConfig build() {
            return new TableConfig(
                    defaultHeadings,
                    Optional.ofNullable(contentToolbar).orElseGet(Collections::emptyList),
                    Optional.ofNullable(tableToolbar).orElseGet(Collections::emptyList),
                    tableProperties,
                    tableCellProperties,
                    tableLayout,
                    tableCaption,
                    allowNesting
            );
        }
    }
}
