package com.merkle.oss.magnolia.definition.custom.validator.hasproperty;

import info.magnolia.ui.editor.LocaleContext;
import info.magnolia.ui.field.AbstractFieldValidatorFactory;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.jcr.Node;

import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.vaadin.data.Validator;

public class HasPropertyValidatorFactory extends AbstractFieldValidatorFactory<HasPropertyValidatorDefinition, Node> {
	private final PowerNodeService powerNodeService;
	private final LocaleContext localeContext;

	@Inject
	public HasPropertyValidatorFactory(
		final HasPropertyValidatorDefinition definition,
		final PowerNodeService powerNodeService,
		final LocaleContext localeContext
	) {
		super(definition);
		this.powerNodeService = powerNodeService;
		this.localeContext = localeContext;
	}

	@Override
	public Validator<Node> createValidator() {
		return new HasPropertyValidator(
			powerNodeService,
			localeContext,
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
