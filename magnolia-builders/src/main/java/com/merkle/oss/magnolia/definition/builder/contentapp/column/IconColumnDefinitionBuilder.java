package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;
import info.magnolia.ui.contentapp.configuration.column.icon.IconColumnDefinition;

public class IconColumnDefinitionBuilder<T> extends AbstractIconColumnDefinitionBuilder<T, IconColumnDefinition, IconColumnDefinitionBuilder<T>> {

    public IconColumnDefinitionBuilder() {}
    public IconColumnDefinitionBuilder(final IconColumnDefinition definition) {
        super(definition);
    }

    public IconColumnDefinition build(final String name) {
        final IconColumnDefinition definition = new IconColumnDefinition();
        super.populateTyped(definition, name);
        return definition;
    }

    public ConfiguredColumnDefinition<T> buildTyped(final String name) {
        return (ConfiguredColumnDefinition<T>)build(name);
    }
}
