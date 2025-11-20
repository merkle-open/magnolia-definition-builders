package com.merkle.oss.magnolia.definition.custom.imageset;

import info.magnolia.config.NamedDefinition;
import info.magnolia.ui.editor.SwitchableFormDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;
import info.magnolia.warp.engine.form.content.processor.SwitchableFormDefinitionWrapper;

import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;

public class PrefixExceptImageFieldPropertyNameDecorator implements WithPropertyNameDecorator.PropertyNameDecorator {
    private final SwitchableFormDefinition<?> definition;

    public PrefixExceptImageFieldPropertyNameDecorator(final SwitchableDefinition definition) {
        this.definition = definition;
    }

    /**
     * Depends on <a href="https://magnolia-cms.atlassian.net/browse/WARPFORM-1022">WARPFORM-1022</a>
     */
    public PrefixExceptImageFieldPropertyNameDecorator(final SwitchableFormDefinitionWrapper<?> definition) {
        this.definition = definition.getSwitchableFormDefinition();
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
