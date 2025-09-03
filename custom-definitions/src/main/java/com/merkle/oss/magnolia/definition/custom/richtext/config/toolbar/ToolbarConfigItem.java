package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

// https://github.com/ckeditor/ckeditor5/blob/3df56dac515177a96ca56e2bfa7004dbad82973b/packages/ckeditor5-core/src/editor/editorconfig.ts#L919
public class ToolbarConfigItem {
    @Nullable
    public final String flatValue;
    @Nullable
    public final NestedToolbarConfigItem nestedValue;

    public ToolbarConfigItem(final String flatValue) {
        this.flatValue = flatValue;
        this.nestedValue = null;
    }
    public ToolbarConfigItem(final NestedToolbarConfigItem nestedValue) {
        this.flatValue = null;
        this.nestedValue = nestedValue;
    }

    public Stream<ToolbarConfigItem> flatten() {
        return Optional.ofNullable(nestedValue)
                .map(NestedToolbarConfigItem::flatten)
                .orElseGet(() -> Stream.of(this));
    }

    public Stream<String> streamValues() {
        return Stream.concat(
                Stream.ofNullable(flatValue),
                Stream.ofNullable(nestedValue).flatMap(NestedToolbarConfigItem::streamValues)
        );
    }

    public static class NestedToolbarConfigItem {
        public final List<ToolbarConfigItem> items;
        public final String label;
        @Nullable
        public final String icon;
        public final boolean withText;
        @Nullable
        public final String tooltip;

        protected NestedToolbarConfigItem(final NestedToolbarConfigItem wrapped) {
            this(wrapped.items, wrapped.label, wrapped.icon, wrapped.withText, wrapped.tooltip);
        }
        protected NestedToolbarConfigItem(
                final List<ToolbarConfigItem> items,
                final String label,
                @Nullable final String icon,
                final boolean withText,
                @Nullable final String tooltip
        ) {
            this.items = items;
            this.label = label;
            this.icon = icon;
            this.withText = withText;
            this.tooltip = tooltip;
        }

        public Stream<ToolbarConfigItem> flatten() {
            return items.stream();
        }

        public Stream<String> streamValues() {
            return flatten().flatMap(ToolbarConfigItem::streamValues);
        }

        public static class Builder extends AbstractBuilder<Builder>{
            public Builder() {}
            public Builder(final NestedToolbarConfigItem nestedToolbarConfigItem) {
                super(nestedToolbarConfigItem);
            }
        }
        public static abstract class AbstractBuilder<T extends AbstractBuilder<T>> {
            private List<ToolbarConfigItem> items;
            @Nullable
            private String label;
            @Nullable
            private String icon;
            @Nullable
            private Boolean withText;
            @Nullable
            private String tooltip;
            private boolean appendItemSeparator = true;

            protected AbstractBuilder() {}
            protected AbstractBuilder(final NestedToolbarConfigItem toolbarConfigItem) {
                this.items = toolbarConfigItem.items;
                this.label = toolbarConfigItem.label;
                this.icon = toolbarConfigItem.icon;
                this.withText = toolbarConfigItem.withText;
                this.tooltip = toolbarConfigItem.tooltip;
            }

            public T item(final String item) {
                return item(new ToolbarConfigItem(item));
            }
            public T item(final NestedToolbarConfigItem item) {
                return item(new ToolbarConfigItem(item));
            }
            public T item(final ToolbarConfigItem item) {
                this.items = Stream.concat(
                        Stream.ofNullable(this.items).flatMap(Collection::stream),
                        Stream.of(item)
                ).collect(Collectors.toList());
                return self();
            }
            public T items(final List<ToolbarConfigItem> items) {
                this.items = items;
                return self();
            }

            public T label(final String label) {
                this.label = label;
                return self();
            }

            public T icon(final String icon) {
                this.icon = icon;
                return self();
            }

            public T withText(final boolean withText) {
                this.withText = withText;
                return self();
            }

            public T tooltip(final String tooltip) {
                this.tooltip = tooltip;
                return self();
            }

            public T appendItemSeparator(final boolean appendItemSeparator) {
                this.appendItemSeparator = appendItemSeparator;
                return self();
            }

            public T self() {
                return (T) this;
            }

            public NestedToolbarConfigItem build() {
                return new NestedToolbarConfigItem(
                        Stream.concat(
                                items.stream(),
                                appendItemSeparator ? Stream.of(new ToolbarConfigItem("|")) : Stream.empty()
                        ).collect(Collectors.toList()),
                        label,
                        icon,
                        Optional.ofNullable(withText).orElse(false),
                        tooltip
                );
            }
        }
    }
}
