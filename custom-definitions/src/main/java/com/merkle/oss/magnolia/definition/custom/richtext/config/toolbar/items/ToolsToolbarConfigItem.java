package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class ToolsToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {
    private ToolsToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("tools");
        }
        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("tools");
        }

        public Builder source() {
            return item("sourceEditing");
        }

        public Builder codeBlock() {
            return item("codeBlock");
        }

        @Override
        public ToolsToolbarConfigItem build() {
            return new ToolsToolbarConfigItem(super.build());
        }
    }
}
