package com.merkle.oss.magnolia.definition.custom.videoset;

import info.magnolia.config.NamedDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;

public class PrefixExceptVideoFieldPropertyNameDecorator implements WithPropertyNameDecorator.PropertyNameDecorator {
private final VideoSetDefinition definition;

    public PrefixExceptVideoFieldPropertyNameDecorator(final VideoSetDefinition definition) {
        this.definition = definition;
    }

    @Override
    public String apply(final String propertyName) {
        if (definition.getVideo().getForms().stream().map(NamedDefinition::getName).anyMatch(propertyName::equals)) {
            //for jcr structure compatibility
            return definition.getName() + propertyName;
        }
        return definition.getName() + "_" + propertyName;
    }
}
