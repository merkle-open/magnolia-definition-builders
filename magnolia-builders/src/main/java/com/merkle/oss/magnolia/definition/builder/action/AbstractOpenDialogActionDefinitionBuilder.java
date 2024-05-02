package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.dialog.actions.OpenDialogActionDefinition;

import java.util.Optional;

import javax.annotation.Nullable;

public class AbstractOpenDialogActionDefinitionBuilder<D extends OpenDialogActionDefinition, B extends AbstractOpenDialogActionDefinitionBuilder<D, B>>  extends AbstractActionDefinitionBuilder<D, B> {
    @Nullable
    private Boolean populate;
    @Nullable
    private Boolean alwaysOnTop;

    public AbstractOpenDialogActionDefinitionBuilder() {}
    public AbstractOpenDialogActionDefinitionBuilder(final D definition) {
        super(definition);
        populate(definition.isPopulate());
        alwaysOnTop(definition.isAlwaysOnTop());
    }

    public B populate(final boolean populate) {
        this.populate = populate;
        return self();
    }

    public B alwaysOnTop(final boolean alwaysOnTop) {
        this.alwaysOnTop = alwaysOnTop;
        return self();
    }

    protected void populate(final D definition, final String name, final String dialogId) {
        super.populate(definition, name);
        definition.setDialogId(dialogId);
        Optional.ofNullable(populate).ifPresent(definition::setPopulate);
        Optional.ofNullable(alwaysOnTop).ifPresent(definition::setAlwaysOnTop);
    }
}
