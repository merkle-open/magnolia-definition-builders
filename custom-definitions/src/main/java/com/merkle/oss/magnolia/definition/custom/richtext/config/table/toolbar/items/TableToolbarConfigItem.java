package com.merkle.oss.magnolia.definition.custom.richtext.config.table.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class TableToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {

    private TableToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends AbstractBuilder<Builder> {

        public Builder() {}
        public Builder(final TableToolbarConfigItem fontToolbarConfigItem) {
            super(fontToolbarConfigItem);
        }

        public Builder blockQuote() {
            return item("blockQuote");
        }

        @Override
        public TableToolbarConfigItem build() {
            return new TableToolbarConfigItem(super.build());
        }
    }
}
