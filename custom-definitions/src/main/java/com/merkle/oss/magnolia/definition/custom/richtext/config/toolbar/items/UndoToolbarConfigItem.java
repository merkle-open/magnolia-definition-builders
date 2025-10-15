package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class UndoToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {
    private UndoToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("undo");
        }
        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("undo");
        }

        public Builder undo() {
            return item("undo");
        }

        public Builder redo() {
            return item("redo");
        }

        @Override
        public UndoToolbarConfigItem build() {
            return new UndoToolbarConfigItem(super.build());
        }
    }
}
