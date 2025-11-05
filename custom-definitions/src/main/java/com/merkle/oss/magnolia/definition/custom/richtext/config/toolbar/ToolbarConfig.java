package com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

// https://github.com/ckeditor/ckeditor5/blob/3df56dac515177a96ca56e2bfa7004dbad82973b/packages/ckeditor5-core/src/editor/editorconfig.ts#L912
public class ToolbarConfig {
    public final List<ToolbarConfigItem> items;
    public final List<String> removeItems;
    public final boolean shouldNotGroupWhenFull;
    @Nullable
    public final String icon;

    protected ToolbarConfig(final ToolbarConfig wrapped) {
        this(wrapped.items, wrapped.removeItems, wrapped.shouldNotGroupWhenFull, wrapped.icon);
    }
    private ToolbarConfig(
            final List<ToolbarConfigItem> items,
            final List<String> removeItems,
            final boolean shouldNotGroupWhenFull,
            @Nullable final String icon
    ) {
        this.items = items;
        this.removeItems = removeItems;
        this.shouldNotGroupWhenFull = shouldNotGroupWhenFull;
        this.icon = icon;
    }

    public boolean containsToolbarConfigItem(final String item) {
        return items.stream().flatMap(ToolbarConfigItem::streamValues).anyMatch(item::equals);
    }

    public static class Builder {
        @Nullable
        private List<ToolbarConfigItem> items;
        @Nullable
        private List<String> removeItems;
        @Nullable
        private Boolean shouldNotGroupWhenFull;
        @Nullable
        private String icon;

        public Builder() {}
        public Builder(final ToolbarConfig toolbarConfig) {
            this.items = toolbarConfig.items;
            this.removeItems = toolbarConfig.removeItems;
            this.shouldNotGroupWhenFull = toolbarConfig.shouldNotGroupWhenFull;
            this.icon = toolbarConfig.icon;
        }

        public Builder item(final String item) {
            return item(new ToolbarConfigItem(item), false);
        }
        public Builder item(final ToolbarConfigItem.NestedToolbarConfigItem item) {
            return item(new ToolbarConfigItem(item), true);
        }
        public Builder item(final ToolbarConfigItem item, final boolean flatten) {
            return items(Stream.concat(
                Stream.ofNullable(this.items).flatMap(Collection::stream),
                flatten ? item.flatten() : Stream.of(item)
            ).collect(Collectors.toList()), false);
        }
        public Builder items(final List<ToolbarConfigItem> items) {
            return items(items, true);
        }
        public Builder items(final List<ToolbarConfigItem> items, final boolean flatten) {
            this.items = items.stream().flatMap(item ->
                    flatten ? item.flatten() : Stream.of(item)
            ).collect(Collectors.toList());
            return this;
        }

        public Builder removeItem(final String removeItem) {
            return removeItems(Stream.concat(
                    Stream.ofNullable(this.removeItems).flatMap(Collection::stream),
                    Stream.of(removeItem)
            ).collect(Collectors.toList()));
        }
        public Builder removeItems(final List<String> removeItems) {
            this.removeItems = removeItems;
            return this;
        }

        public Builder shouldNotGroupWhenFull(final boolean shouldNotGroupWhenFull) {
            this.shouldNotGroupWhenFull = shouldNotGroupWhenFull;
            return this;
        }

        public Builder icon(final String icon) {
            this.icon = icon;
            return this;
        }

        public ToolbarConfig build() {
            return new ToolbarConfig(
                    Optional.ofNullable(items).orElseGet(Collections::emptyList),
                    Optional.ofNullable(removeItems).orElseGet(Collections::emptyList),
                    Optional.ofNullable(shouldNotGroupWhenFull).orElse(false),
                    icon
            );
        }
    }
}
