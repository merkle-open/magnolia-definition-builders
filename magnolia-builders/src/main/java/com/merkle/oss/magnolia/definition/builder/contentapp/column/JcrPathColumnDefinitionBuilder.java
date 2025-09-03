package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.column.jcr.JcrPathColumnDefinition;

import java.util.Optional;

import jakarta.annotation.Nullable;
import javax.jcr.Item;

public class JcrPathColumnDefinitionBuilder extends AbstractColumnDefinitionBuilder<Item, JcrPathColumnDefinition, JcrPathColumnDefinitionBuilder> {
    @Nullable
    private String name;

    public JcrPathColumnDefinitionBuilder() {}
    public JcrPathColumnDefinitionBuilder(final JcrPathColumnDefinition definition) {
        super(definition);
    }

    public JcrPathColumnDefinitionBuilder name(final String name) {
        this.name = name;
        return self();
    }

    public JcrPathColumnDefinition build() {
        final JcrPathColumnDefinition definition = new JcrPathColumnDefinition();
        super.populate(definition, Optional.ofNullable(name).orElseGet(definition::getName));
        return definition;
    }
}
