package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.config.NamedDefinition;
import info.magnolia.ui.editor.EditorDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.editor.SwitchableFormDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;
import info.magnolia.warp.engine.form.content.processor.SwitchableFormDefinitionWrapper;

import javax.jcr.Node;

public class PrefixFormNameExceptFieldPropertyNameDecorator implements WithPropertyNameDecorator.PropertyNameDecorator {
    private final EditorDefinition<?> formDefinition;
    private final SwitchableFormDefinition<Node> definition;

    public PrefixFormNameExceptFieldPropertyNameDecorator(
            final FormDefinition<?> formDefinition,
            final SwitchableDefinition definition
    ) {
        this.formDefinition = formDefinition;
        this.definition = definition;
    }

    /**
     * Depends on <a href="https://magnolia-cms.atlassian.net/browse/WARPFORM-1022">WARPFORM-1022</a>
     * TODO doesn't really work, only the first field of the composite gets saved.
     */
    public PrefixFormNameExceptFieldPropertyNameDecorator(SwitchableFormDefinitionWrapper<Node> definition) {
        this.formDefinition = definition.getFormDefinition();
        this.definition = definition.getSwitchableFormDefinition();
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
