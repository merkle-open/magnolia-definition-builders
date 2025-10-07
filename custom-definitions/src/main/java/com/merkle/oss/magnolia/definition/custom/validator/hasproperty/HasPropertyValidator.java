package com.merkle.oss.magnolia.definition.custom.validator.hasproperty;

import info.magnolia.cms.i18n.I18nContentSupport;
import info.magnolia.module.site.Site;
import info.magnolia.module.site.SiteManager;

import java.util.Collection;
import java.util.Optional;

import javax.jcr.Node;

import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class HasPropertyValidator extends AbstractValidator<Node> {
    private final PowerNodeService powerNodeService;
    private final SiteManager siteManager;
    private final String propertyName;
    private final boolean i18n;

    public HasPropertyValidator(
            final PowerNodeService powerNodeService,
            final SiteManager siteManager,
            final String errorMessage,
            final String propertyName,
            final boolean i18n
    ) {
        super(errorMessage);
        this.powerNodeService = powerNodeService;
        this.siteManager = siteManager;
        this.propertyName = propertyName;
        this.i18n = i18n;
    }

    @Override
    public ValidationResult apply(final Node value, final ValueContext valueContext) {
        return toResult(value, isValid(powerNodeService.convertToPowerNode(value)));
    }

    private boolean isValid(final PowerNode node) {
        if (i18n) {
            return Optional
                    .ofNullable(siteManager.getAssignedSite(node))
                    .stream()
                    .map(Site::getI18n)
                    .map(I18nContentSupport::getLocales)
                    .flatMap(Collection::stream)
                    .allMatch(locale -> node.hasProperty(propertyName, locale));
        }
        return node.hasProperty(propertyName);
    }
}
