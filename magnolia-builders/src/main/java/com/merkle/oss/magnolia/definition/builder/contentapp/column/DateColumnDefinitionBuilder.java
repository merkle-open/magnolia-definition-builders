package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.configuration.column.DateColumnDefinition;

import java.util.Date;

public class DateColumnDefinitionBuilder<T> extends AbstractColumnDefinitionBuilder<Date, DateColumnDefinition, DateColumnDefinitionBuilder<T>> {

    public DateColumnDefinitionBuilder() {}
    public DateColumnDefinitionBuilder(final DateColumnDefinition definition) {
        super(definition);
    }

    public DateColumnDefinition build(final String name) {
        final DateColumnDefinition definition = new DateColumnDefinition();
        super.populate(definition, name);
        return definition;
    }
}
