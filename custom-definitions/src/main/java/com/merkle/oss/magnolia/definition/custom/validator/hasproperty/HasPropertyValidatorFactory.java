package com.merkle.oss.magnolia.definition.custom.validator.hasproperty;

import info.magnolia.module.site.SiteManager;
import info.magnolia.ui.field.AbstractFieldValidatorFactory;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.jcr.Node;

import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.vaadin.data.Validator;

public class HasPropertyValidatorFactory extends AbstractFieldValidatorFactory<HasPropertyValidatorDefinition, Node> {
	private final PowerNodeService powerNodeService;
    private final SiteManager siteManager;

	@Inject
	public HasPropertyValidatorFactory(
		final HasPropertyValidatorDefinition definition,
		final PowerNodeService powerNodeService,
		final SiteManager siteManager
	) {
		super(definition);
		this.powerNodeService = powerNodeService;
        this.siteManager = siteManager;
	}

	@Override
	public Validator<Node> createValidator() {
		return new HasPropertyValidator(
			powerNodeService,
            siteManager,
			getI18nErrorMessage(),
			definition.getPropertyName(),
			definition.isI18n()
		);
	}

	@Override
	public String getI18nErrorMessage() {
		final MessageFormat fmt = new MessageFormat(super.getI18nErrorMessage());
		final String[] args = { definition.getPropertyName() };
		return fmt.format(args);
	}
}
