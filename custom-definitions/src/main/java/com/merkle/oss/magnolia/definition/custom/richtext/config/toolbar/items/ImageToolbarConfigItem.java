package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;


import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class ImageToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {
    private ImageToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {
        public Builder() {
            label("image");
        }
        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("image");
        }

        public Builder image() {
            return super.item("insertImage");
        }

        @Override
        public ImageToolbarConfigItem build() {
            return new ImageToolbarConfigItem(super.build());
        }
    }
}
