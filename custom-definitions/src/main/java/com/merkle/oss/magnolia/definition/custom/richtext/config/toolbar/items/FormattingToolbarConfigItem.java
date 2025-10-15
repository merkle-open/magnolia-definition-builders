package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items;

import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;

public class FormattingToolbarConfigItem extends ToolbarConfigItem.NestedToolbarConfigItem {

    private FormattingToolbarConfigItem(final ToolbarConfigItem.NestedToolbarConfigItem wrapped) {
        super(wrapped);
    }

    public static class Builder extends ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<Builder> {

        public Builder() {
            label("formatting");
        }
        public Builder(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
            super(toolbarConfigItem);
            label("formatting");
        }

        public Builder bold() {
            return item("bold");
        }

        public Builder italic() {
            return item("italic");
        }

        public Builder underline() {
            return item("underline");
        }

        public Builder strike() {
            return item("strikethrough");
        }

        public Builder subscript() {
            return item("subscript");
        }

        public Builder superscript() {
            return item("superscript");
        }

        public Builder removeFormat() {
            return item("removeFormat");
        }

        public Builder specialChar() {
            return item("specialCharacters");
        }

        @Override
        public FormattingToolbarConfigItem build() {
            return new FormattingToolbarConfigItem(super.build());
        }
    }
}
