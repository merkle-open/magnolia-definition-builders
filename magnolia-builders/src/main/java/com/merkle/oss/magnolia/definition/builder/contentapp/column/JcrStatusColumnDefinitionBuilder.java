package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.column.jcr.JcrStatusColumnDefinition;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.jcr.Item;

public class JcrStatusColumnDefinitionBuilder extends AbstractColumnDefinitionBuilder<Item, JcrStatusColumnDefinition, JcrStatusColumnDefinitionBuilder> {
    @Nullable
    private Boolean activation;
    @Nullable
    private Boolean permissions;
    @Nullable
    private String name;

    public JcrStatusColumnDefinitionBuilder() {}
    public JcrStatusColumnDefinitionBuilder(final JcrStatusColumnDefinition definition) {
        super(definition);
        activation(definition.isActivation());
        permissions(definition.isPermissions());
    }

    public JcrStatusColumnDefinitionBuilder activation(final boolean activation) {
        this.activation = activation;
        return self();
    }

    public JcrStatusColumnDefinitionBuilder permissions(final boolean permissions) {
        this.permissions = permissions;
        return self();
    }

    public JcrStatusColumnDefinitionBuilder name(final String name) {
        this.name = name;
        return self();
    }

    public JcrStatusColumnDefinition build() {
        final JcrStatusColumnDefinition definition = new JcrStatusColumnDefinition();
        super.populate(definition, Optional.ofNullable(name).orElseGet(definition::getName));
        Optional.ofNullable(activation).ifPresent(definition::setActivation);
        Optional.ofNullable(permissions).ifPresent(definition::setPermissions);
        return definition;
    }
}
