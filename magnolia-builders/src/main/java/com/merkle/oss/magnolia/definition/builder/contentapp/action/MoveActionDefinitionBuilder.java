package com.merkle.oss.magnolia.definition.builder.contentapp.action;

import info.magnolia.ui.contentapp.action.MoveActionDefinition;

import java.util.Optional;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.action.AbstractActionDefinitionBuilder;

public class MoveActionDefinitionBuilder extends AbstractActionDefinitionBuilder<MoveActionDefinition<?>, MoveActionDefinitionBuilder> {
    @Nullable
    private String dialogId;

    public MoveActionDefinitionBuilder() {}
    public MoveActionDefinitionBuilder(final MoveActionDefinition<?> definition) {
        super(definition);
        Optional.ofNullable(definition.getDialogId()).ifPresent(this::dialogId);
    }

    public MoveActionDefinitionBuilder dialogId(final String dialogId) {
        this.dialogId = dialogId;
        return self();
    }

    public MoveActionDefinition<?> build(final String name) {
        final MoveActionDefinition<?> definition = new MoveActionDefinition<>();
        populate(definition, name);
        Optional.ofNullable(dialogId).ifPresent(definition::setDialogId);
        return definition;
    }
}
