package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.contentapp.action.ConfirmationActionDefinition;

public class ConfirmationActionDefinitionBuilder extends AbstractConfirmationActionDefinitionBuilder<ConfirmationActionDefinition, ConfirmationActionDefinitionBuilder> {

    public ConfirmationActionDefinitionBuilder() {}
    public ConfirmationActionDefinitionBuilder(final ConfirmationActionDefinition definition) {
        super(definition);
    }

    public ConfirmationActionDefinition build(final String name) {
        final ConfirmationActionDefinition definition = new ConfirmationActionDefinition();
        populate(definition, name);
        return definition;
    }
}