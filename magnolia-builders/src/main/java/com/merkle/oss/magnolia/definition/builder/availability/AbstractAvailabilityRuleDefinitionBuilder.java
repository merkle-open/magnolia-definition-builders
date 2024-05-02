package com.merkle.oss.magnolia.definition.builder.availability;

import info.magnolia.ui.api.availability.AvailabilityRule;
import info.magnolia.ui.api.availability.ConfiguredAvailabilityRuleDefinition;

import java.util.Optional;

import javax.annotation.Nullable;

public class AbstractAvailabilityRuleDefinitionBuilder<D extends ConfiguredAvailabilityRuleDefinition, B extends AbstractAvailabilityRuleDefinitionBuilder<D, B>> {
    @Nullable
    protected Class<? extends AvailabilityRule> implementationClass;
    @Nullable
    private Boolean negate;

    public AbstractAvailabilityRuleDefinitionBuilder() {}

    public AbstractAvailabilityRuleDefinitionBuilder(final D definition) {
        Optional.ofNullable(definition.getImplementationClass()).ifPresent(this::implementationClass);
        negate(definition.getNegate());
    }

    public B implementationClass(final Class<? extends AvailabilityRule> implementationClass) {
        this.implementationClass = implementationClass;
        return self();
    }

    public B negate(final boolean negate) {
        this.negate = negate;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    protected void populate(final D definition) {
        Optional.ofNullable(implementationClass).ifPresent(definition::setImplementationClass);
        Optional.ofNullable(negate).ifPresent(definition::setNegate);
    }
}