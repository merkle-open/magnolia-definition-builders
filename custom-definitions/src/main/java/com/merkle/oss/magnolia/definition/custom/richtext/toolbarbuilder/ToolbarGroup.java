package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder;

import java.util.List;
import java.util.Objects;

public class ToolbarGroup {
    private final String name;
    private final List<String> items;

    public ToolbarGroup(final String name, final List<String> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ToolbarGroup that)) {
            return false;
        }
        return Objects.equals(name, that.name) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, items);
    }

    @Override
    public String toString() {
        return "ToolbarGroup{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
