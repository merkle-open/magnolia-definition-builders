package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class TableToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {
    private TableToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("table");
        }

        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("table");
        }

        public Builder table() {
            return item("insertTable");
        }

        @Override
        public TableToolbarConfigItem build() {
            return new TableToolbarConfigItem(super.build());
        }
    }
}
