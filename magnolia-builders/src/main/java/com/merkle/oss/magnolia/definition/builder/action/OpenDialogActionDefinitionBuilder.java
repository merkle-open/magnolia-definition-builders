package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.dialog.actions.OpenDialogActionDefinition;

public class OpenDialogActionDefinitionBuilder extends AbstractOpenDialogActionDefinitionBuilder<OpenDialogActionDefinition, OpenDialogActionDefinitionBuilder> {

    public OpenDialogActionDefinitionBuilder() {}
    public OpenDialogActionDefinitionBuilder(final OpenDialogActionDefinition definition) {
        super(definition);
    }

    public OpenDialogActionDefinition build(final String name, final String dialogId) {
        final OpenDialogActionDefinition definition = new OpenDialogActionDefinition();
        super.populate(definition, name, dialogId);
        return definition;
    }
}
