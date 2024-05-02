package com.merkle.oss.magnolia.definition.builder.contentapp.action;

import info.magnolia.importexport.command.JcrExportCommand;
import info.magnolia.ui.contentapp.action.JcrExportActionDefinition;

import java.util.Optional;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.action.AbstractCommandActionDefinitionBuilder;

public class JcrExportActionDefinitionBuilder extends AbstractCommandActionDefinitionBuilder<JcrExportActionDefinition, JcrExportActionDefinitionBuilder> {
    @Nullable
    private JcrExportCommand.Format format;

    public JcrExportActionDefinitionBuilder() {}
    public JcrExportActionDefinitionBuilder(final JcrExportActionDefinition definition) {
        super(definition);
        Optional.ofNullable(definition.getFormat()).ifPresent(this::format);
    }

    public JcrExportActionDefinitionBuilder format(final JcrExportCommand.Format format) {
        this.format = format;
        return self();
    }

    public JcrExportActionDefinition build(final String name) {
        final JcrExportActionDefinition definition = new JcrExportActionDefinition();
        populate(definition, name);
        Optional.ofNullable(format).ifPresent(definition::setFormat);
        return definition;
    }
}
