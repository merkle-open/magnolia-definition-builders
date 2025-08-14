package com.merkle.oss.magnolia.definition.custom.videoset;

import info.magnolia.config.NamedDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;

import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;

public class PrefixExceptVideoFieldPropertyNameDecorator implements WithPropertyNameDecorator.PropertyNameDecorator {
    private final SwitchableDefinition definition;

    public PrefixExceptVideoFieldPropertyNameDecorator(final SwitchableDefinition definition) {
        this.definition = definition;
    }

    @Override
    public String apply(final String propertyName) {
        if (definition.getForms().stream().map(NamedDefinition::getName).anyMatch(propertyName::equals)) {
            //for jcr structure compatibility
            return definition.getName() + propertyName;
        }
        return definition.getName() + "_" + propertyName;
    }
}
