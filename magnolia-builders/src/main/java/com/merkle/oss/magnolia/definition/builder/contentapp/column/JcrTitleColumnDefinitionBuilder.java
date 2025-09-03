package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.column.jcr.JcrTitleColumnDefinition;
import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;
import info.magnolia.ui.contentapp.configuration.column.icon.IconColumnDefinition;

import java.util.Map;
import java.util.Optional;

import jakarta.annotation.Nullable;
import javax.jcr.Item;

public class JcrTitleColumnDefinitionBuilder extends AbstractIconColumnDefinitionBuilder<Item, JcrTitleColumnDefinition, JcrTitleColumnDefinitionBuilder>{
    @Nullable
    private Map<String, String> nodeTypeToIcon;
    @Nullable
    private String propertyIcon;
    @Nullable
    private String name;

    public JcrTitleColumnDefinitionBuilder() {}
    public JcrTitleColumnDefinitionBuilder(final JcrTitleColumnDefinition definition) {
        super(definition);
        nodeTypeToIcon(definition.getNodeTypeToIcon());
        propertyIcon(definition.getPropertyIcon());
    }

    public JcrTitleColumnDefinitionBuilder nodeTypeToIcon(final Map<String, String> nodeTypeToIcon) {
        this.nodeTypeToIcon = nodeTypeToIcon;
        return self();
    }

    public JcrTitleColumnDefinitionBuilder propertyIcon(final String propertyIcon) {
        this.propertyIcon = propertyIcon;
        return self();
    }

    public JcrTitleColumnDefinitionBuilder name(final String name) {
        this.name = name;
        return self();
    }

    public JcrTitleColumnDefinition build() {
        final JcrTitleColumnDefinition definition = new JcrTitleColumnDefinition();
        super.populateTyped(definition, Optional.ofNullable(name).orElseGet(definition::getName));
        Optional.ofNullable(nodeTypeToIcon).ifPresent(definition::setNodeTypeToIcon);
        Optional.ofNullable(propertyIcon).ifPresent(definition::setPropertyIcon);
        return definition;
    }

    public ConfiguredColumnDefinition<Item> buildTyped() {
        return (ConfiguredColumnDefinition<Item>)build();
    }
}
