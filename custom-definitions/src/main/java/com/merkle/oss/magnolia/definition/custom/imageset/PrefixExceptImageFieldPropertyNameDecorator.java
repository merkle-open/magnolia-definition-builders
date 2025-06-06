package com.merkle.oss.magnolia.definition.custom.imageset;

import info.magnolia.config.NamedDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;

public class PrefixExceptImageFieldPropertyNameDecorator implements WithPropertyNameDecorator.PropertyNameDecorator {
    private final ImageSetDefinition definition;

    public PrefixExceptImageFieldPropertyNameDecorator(final ImageSetDefinition definition) {
        this.definition = definition;
    }

    @Override
    public String apply(final String propertyName) {
        if (definition.getImageField().getForms().stream().map(NamedDefinition::getName).anyMatch(propertyName::equals)) {
            //for jcr structure compatibility
            return definition.getName() + propertyName;
        }
        return definition.getName() + "_" + propertyName;
    }
}
