package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.api.action.ConfiguredActionDefinition;

public class ActionDefinitionBuilder extends AbstractActionDefinitionBuilder<ConfiguredActionDefinition, ActionDefinitionBuilder> {

    public ActionDefinitionBuilder() {}
    public ActionDefinitionBuilder(final ConfiguredActionDefinition definition) {
        super(definition);
    }

    public ConfiguredActionDefinition build(final String name) {
        final ConfiguredActionDefinition definition = new ConfiguredActionDefinition();
        populate(definition, name);
        return definition;
    }
}