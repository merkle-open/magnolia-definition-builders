package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.api.action.CommandActionDefinition;

public class CommandActionDefinitionBuilder extends AbstractCommandActionDefinitionBuilder<CommandActionDefinition, CommandActionDefinitionBuilder> {

    public CommandActionDefinitionBuilder() {}
    public CommandActionDefinitionBuilder(final CommandActionDefinition definition) {
        super(definition);
    }

    public CommandActionDefinition build(final String name) {
        final CommandActionDefinition definition = new CommandActionDefinition();
        populate(definition, name);
        return definition;
    }
}
