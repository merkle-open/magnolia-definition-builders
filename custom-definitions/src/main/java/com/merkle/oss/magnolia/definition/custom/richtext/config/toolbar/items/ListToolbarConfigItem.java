package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class ListToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {
    private ListToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("list");
        }
        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("list");
        }

        public Builder bulletedList() {
            return item("bulletedList");
        }

        public Builder numberedList() {
            return item("numberedList");
        }

        public Builder todoList() {
            return item("todoList");
        }

        @Override
        public ListToolbarConfigItem build() {
            return new ListToolbarConfigItem(super.build());
        }
    }
}
