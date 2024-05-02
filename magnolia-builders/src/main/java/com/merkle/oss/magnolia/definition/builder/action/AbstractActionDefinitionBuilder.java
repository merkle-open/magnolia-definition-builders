package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.api.action.Action;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.api.availability.AvailabilityDefinition;

import java.util.Optional;

import javax.annotation.Nullable;

public class AbstractActionDefinitionBuilder<D extends ConfiguredActionDefinition, B extends AbstractActionDefinitionBuilder<D, B>> {
    @Nullable
    private String label;
    @Nullable
    private String description;
    @Nullable
    private String icon;
    @Nullable
    private String i18nBasename;
    @Nullable
    private Class<? extends Action> implementationClass;
    @Nullable
    private String successMessage;
    @Nullable
    private String failureMessage;
    @Nullable
    private String errorMessage;
    @Nullable
    private AvailabilityDefinition availability;

    public AbstractActionDefinitionBuilder() {}
    public AbstractActionDefinitionBuilder(final D definition) {
        Optional.ofNullable(definition.getLabel()).ifPresent(this::label);
        Optional.ofNullable(definition.getDescription()).ifPresent(this::description);
        Optional.ofNullable(definition.getIcon()).ifPresent(this::icon);
        Optional.ofNullable(definition.getI18nBasename()).ifPresent(this::i18nBasename);
        Optional.ofNullable(definition.getImplementationClass()).ifPresent(this::implementationClass);
        Optional.ofNullable(definition.getSuccessMessage()).ifPresent(this::successMessage);
        Optional.ofNullable(definition.getFailureMessage()).ifPresent(this::failureMessage);
        Optional.ofNullable(definition.getErrorMessage()).ifPresent(this::errorMessage);
        Optional.ofNullable(definition.getAvailability()).ifPresent(this::availability);
    }

    public B label(final String label) {
        this.label = label;
        return self();
    }
    public B description(final String description) {
        this.description = description;
        return self();
    }
    public B icon(final String icon) {
        this.icon = icon;
        return self();
    }
    public B i18nBasename(final String i18nBasename) {
        this.i18nBasename = i18nBasename;
        return self();
    }
    public B implementationClass(final Class<? extends Action> implementationClass) {
        this.implementationClass = implementationClass;
        return self();
    }
    public B successMessage(final String successMessage) {
        this.successMessage = successMessage;
        return self();
    }
    public B failureMessage(final String failureMessage) {
        this.failureMessage = failureMessage;
        return self();
    }
    public B errorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
        return self();
    }
    public B availability(final AvailabilityDefinition availability) {
        this.availability = availability;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    protected void populate(final D definition, final String name) {
        definition.setName(name);
        Optional.ofNullable(label).ifPresent(definition::setLabel);
        Optional.ofNullable(description).ifPresent(definition::setDescription);
        Optional.ofNullable(icon).ifPresent(definition::setIcon);
        Optional.ofNullable(i18nBasename).ifPresent(definition::setI18nBasename);
        Optional.ofNullable(implementationClass).ifPresent(definition::setImplementationClass);
        Optional.ofNullable(successMessage).ifPresent(definition::setSuccessMessage);
        Optional.ofNullable(failureMessage).ifPresent(definition::setFailureMessage);
        Optional.ofNullable(errorMessage).ifPresent(definition::setErrorMessage);
        Optional.ofNullable(availability).ifPresent(definition::setAvailability);
    }
}