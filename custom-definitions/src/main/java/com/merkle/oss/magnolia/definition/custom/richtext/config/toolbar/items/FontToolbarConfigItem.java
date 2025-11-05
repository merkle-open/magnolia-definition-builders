package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class FontToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {

    private FontToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("font");
        }
        public Builder(final FontToolbarConfigItem fontToolbarConfigItem) {
            super(fontToolbarConfigItem);
            label("font");
        }

        public Builder heading() {
            return item("heading");
        }

        public Builder fontFamily() {
            return item("fontFamily");
        }

        public Builder fontSize() {
            return item("fontSize");
        }

        public Builder fontColor() {
            return item("fontColor");
        }

        @Override
        public FontToolbarConfigItem build() {
            return new FontToolbarConfigItem(super.build());
        }
    }
}
