package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.config.NamedDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;

public class PrefixFormNameExceptFieldPropertyNameDecorator implements WithPropertyNameDecorator.PropertyNameDecorator {
    private final FormDefinition<?> formDefinition;
    private final SwitchableDefinition definition;

    public PrefixFormNameExceptFieldPropertyNameDecorator(
            final FormDefinition<?> formDefinition,
            final SwitchableDefinition definition
    ) {
        this.formDefinition = formDefinition;
        this.definition = definition;
    }

    @Override
    public String apply(final String propertyName) {
        if (definition.getForms().stream().map(NamedDefinition::getName).anyMatch(propertyName::equals)) {
            //for jcr structure compatibility
            return propertyName;
        }
        return formDefinition.getName() + "_" + propertyName;
    }
}
