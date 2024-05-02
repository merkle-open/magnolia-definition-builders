package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;

public class ColumnDefinitionBuilder<T> extends AbstractColumnDefinitionBuilder<T, ConfiguredColumnDefinition<T>, ColumnDefinitionBuilder<T>> {

    public ColumnDefinitionBuilder() {}
    public ColumnDefinitionBuilder(final ConfiguredColumnDefinition<T> definition) {
        super(definition);
    }

    public ConfiguredColumnDefinition<T> build(final String name) {
        final ConfiguredColumnDefinition<T> definition = new ConfiguredColumnDefinition<>();
        super.populate(definition, name);
        return definition;
    }
}
