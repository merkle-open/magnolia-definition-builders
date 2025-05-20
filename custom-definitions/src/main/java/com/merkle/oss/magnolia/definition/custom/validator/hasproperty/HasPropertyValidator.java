package com.merkle.oss.magnolia.definition.custom.validator.hasproperty;

import info.magnolia.ui.editor.LocaleContext;

import javax.jcr.Node;

import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class HasPropertyValidator extends AbstractValidator<Node> {
    private final PowerNodeService powerNodeService;
    private final LocaleContext localeContext;
    private final String propertyName;
    private final boolean i18n;

    public HasPropertyValidator(
            final PowerNodeService powerNodeService,
            final LocaleContext localeContext,
            final String errorMessage,
            final String propertyName,
            final boolean i18n
    ) {
        super(errorMessage);
        this.powerNodeService = powerNodeService;
        this.localeContext = localeContext;
        this.propertyName = propertyName;
        this.i18n = i18n;
    }

    @Override
    public ValidationResult apply(final Node value, final ValueContext valueContext) {
        return toResult(value, isValid(powerNodeService.convertToPowerNode(value)));
    }

    private boolean isValid(final PowerNode node) {
        if (i18n) {
            return localeContext.getAvailableLocales().allMatch(locale -> node.hasProperty(propertyName, locale));
        }
        return node.hasProperty(propertyName);
    }
}
