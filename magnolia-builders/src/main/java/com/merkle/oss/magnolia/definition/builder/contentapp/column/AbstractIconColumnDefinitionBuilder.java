package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;
import info.magnolia.ui.contentapp.configuration.column.icon.IconColumnDefinition;

import java.util.Optional;

import javax.annotation.Nullable;

// since magnolia messed up generics, we cannot be typed here
public class AbstractIconColumnDefinitionBuilder<T, D extends IconColumnDefinition, B extends AbstractIconColumnDefinitionBuilder<T, D, B>> extends AbstractColumnDefinitionBuilder<T, ConfiguredColumnDefinition<T>, B> {
    @Nullable
    private String icon;

    public AbstractIconColumnDefinitionBuilder() {}
    public AbstractIconColumnDefinitionBuilder(final IconColumnDefinition definition) {
        super(definition);
        icon(definition.getIcon());
    }

    public B icon(final String icon) {
        this.icon = icon;
        return self();
    }

    protected void populateTyped(final D definition, String name) {
        super.populate(definition, name);
        Optional.ofNullable(icon).ifPresent(definition::setIcon);
    }
}
