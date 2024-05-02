package com.merkle.oss.magnolia.definition.builder.contentapp.action;

import info.magnolia.ui.contentapp.action.OpenDetailSubappActionDefinition;

import com.merkle.oss.magnolia.definition.builder.action.AbstractActionDefinitionBuilder;

public class OpenDetailSubappActionDefinitionBuilder extends AbstractActionDefinitionBuilder<OpenDetailSubappActionDefinition, OpenDetailSubappActionDefinitionBuilder> {

    public OpenDetailSubappActionDefinitionBuilder() {}
    public OpenDetailSubappActionDefinitionBuilder(final OpenDetailSubappActionDefinition definition) {
        super(definition);
    }

    public OpenDetailSubappActionDefinition build(final String name,  final String appName, final String subAppName) {
        final OpenDetailSubappActionDefinition definition = new OpenDetailSubappActionDefinition();
        populate(definition, name);
        definition.setAppName(appName);
        definition.setSubAppName(subAppName);
        return definition;
    }
}