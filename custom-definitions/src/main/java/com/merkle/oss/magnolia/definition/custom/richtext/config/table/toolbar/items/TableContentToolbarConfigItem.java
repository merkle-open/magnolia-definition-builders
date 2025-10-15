package com.merkle.oss.magnolia.definition.custom.richtext.config.table.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class TableContentToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {

    private TableContentToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends AbstractBuilder<Builder> {

        public Builder() {}
        public Builder(final TableContentToolbarConfigItem fontToolbarConfigItem) {
            super(fontToolbarConfigItem);
        }

        public Builder tableColumn() {
            return item("tableColumn");
        }

        public Builder tableRow() {
            return item("tableRow");
        }

        public Builder mergeTableCells() {
            return item("mergeTableCells");
        }

        public Builder tableCellProperties() {
            return item("tableCellProperties");
        }

        public Builder tableProperties() {
            return item("tableProperties");
        }

        @Override
        public TableContentToolbarConfigItem build() {
            return new TableContentToolbarConfigItem(super.build());
        }
    }
}
