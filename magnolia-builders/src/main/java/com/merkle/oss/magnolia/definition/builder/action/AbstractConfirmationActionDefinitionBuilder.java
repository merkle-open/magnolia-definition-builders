package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.contentapp.action.ConfirmationActionDefinition;

import java.util.Optional;

import javax.annotation.Nullable;

public class AbstractConfirmationActionDefinitionBuilder<D extends ConfirmationActionDefinition, B extends AbstractConfirmationActionDefinitionBuilder<D, B>> extends AbstractActionDefinitionBuilder<D, B> {
    @Nullable
    private String confirmationHeader;
    @Nullable
    private String confirmationMessage;
    @Nullable
    private String successActionName;
    @Nullable
    private String cancelActionName;
    @Nullable
    private String proceedLabel;
    @Nullable
    private String cancelLabel;
    @Nullable
    private Boolean defaultCancel;

    public AbstractConfirmationActionDefinitionBuilder() {}
    public AbstractConfirmationActionDefinitionBuilder(final D definition) {
        super(definition);
        Optional.ofNullable(definition.getConfirmationHeader()).ifPresent(this::confirmationHeader);
        Optional.ofNullable(definition.getConfirmationMessage()).ifPresent(this::confirmationMessage);
        Optional.ofNullable(definition.getSuccessActionName()).ifPresent(this::successActionName);
        Optional.ofNullable(definition.getCancelActionName()).ifPresent(this::cancelActionName);
        Optional.ofNullable(definition.getProceedLabel()).ifPresent(this::proceedLabel);
        Optional.ofNullable(definition.getCancelLabel()).ifPresent(this::cancelLabel);
        defaultCancel(definition.isDefaultCancel());
    }

    public B confirmationHeader(final String confirmationHeader) {
        this.confirmationHeader = confirmationHeader;
        return self();
    }
    public B confirmationMessage(final String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
        return self();
    }
    public B successActionName(final String successActionName) {
        this.successActionName = successActionName;
        return self();
    }
    public B cancelActionName(final String cancelActionName) {
        this.cancelActionName = cancelActionName;
        return self();
    }
    public B proceedLabel(final String proceedLabel) {
        this.proceedLabel = proceedLabel;
        return self();
    }
    public B cancelLabel(final String cancelLabel) {
        this.cancelLabel = cancelLabel;
        return self();
    }
    public B defaultCancel(final boolean defaultCancel) {
        this.defaultCancel = defaultCancel;
        return self();
    }

    @Override
    protected void populate(D definition, String name) {
        super.populate(definition, name);
        Optional.ofNullable(confirmationHeader).ifPresent(definition::setConfirmationHeader);
        Optional.ofNullable(confirmationMessage).ifPresent(definition::setConfirmationMessage);
        Optional.ofNullable(successActionName).ifPresent(definition::setSuccessActionName);
        Optional.ofNullable(cancelActionName).ifPresent(definition::setCancelActionName);
        Optional.ofNullable(proceedLabel).ifPresent(definition::setProceedLabel);
        Optional.ofNullable(cancelLabel).ifPresent(definition::setCancelLabel);
        Optional.ofNullable(defaultCancel).ifPresent(definition::setDefaultCancel);
    }
}
