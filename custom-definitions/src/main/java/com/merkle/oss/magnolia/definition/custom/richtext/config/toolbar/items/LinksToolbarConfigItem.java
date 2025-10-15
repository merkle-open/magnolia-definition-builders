package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class LinksToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {
    private LinksToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("links");
        }
        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("links");
        }

        public Builder link() {
            return item("link");
        }

        public Builder internalLink() {
            return item("mgnlPagesLink");
        }

        public Builder damLink() {
            return item("mgnlAssetsLink");
        }

        @Override
        public LinksToolbarConfigItem build() {
            return new LinksToolbarConfigItem(super.build());
        }
    }
}
