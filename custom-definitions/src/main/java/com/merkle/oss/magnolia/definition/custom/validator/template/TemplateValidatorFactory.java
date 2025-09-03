package com.merkle.oss.magnolia.definition.custom.validator.template;

import info.magnolia.ui.field.AbstractFieldValidatorFactory;

import java.text.MessageFormat;

import javax.jcr.Node;

import com.vaadin.data.Validator;

import jakarta.inject.Inject;

public class TemplateValidatorFactory extends AbstractFieldValidatorFactory<TemplateValidatorDefinition, Node> {

	@Inject
	public TemplateValidatorFactory(final TemplateValidatorDefinition definition) {
		super(definition);
	}

	@Override
	public Validator<Node> createValidator() {
		return new TemplateValidator(definition.getTemplateIds(), getI18nErrorMessage());
	}

	@Override
	public String getI18nErrorMessage() {
		final MessageFormat fmt = new MessageFormat(super.getI18nErrorMessage());
		final String[] args = new String[]{
                String.join("|", definition.getTemplateIds())
		};
		return fmt.format(args);
	}
}
