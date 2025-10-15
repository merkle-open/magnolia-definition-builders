package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class PositionToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {
    private PositionToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("position");
        }
        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("position");
        }

        public Builder alignment() {
            return item("alignment");
        }

        public Builder outdent() {
            return item("outdent");
        }

        public Builder indent() {
            return item("indent");
        }

        @Override
        public PositionToolbarConfigItem build() {
            return new PositionToolbarConfigItem(super.build());
        }
    }
}
