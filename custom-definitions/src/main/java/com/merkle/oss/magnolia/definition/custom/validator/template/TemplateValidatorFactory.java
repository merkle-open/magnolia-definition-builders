package com.merkle.oss.magnolia.definition.custom.validator.template;

import com.vaadin.data.Validator;
import info.magnolia.ui.field.AbstractFieldValidatorFactory;

import javax.inject.Inject;
import javax.jcr.Node;
import java.text.MessageFormat;

public class TemplateValidatorFactory extends AbstractFieldValidatorFactory<TemplateValidatorDefinition, Node> {

	@Inject
	public TemplateValidatorFactory(final TemplateValidatorDefinition definition) {
		super(definition);
	}

	@Override
	public Validator<Node> createValidator() {
		return new TemplateValidator(definition.getTemplateId(), getI18nErrorMessage());
	}

	@Override
	public String getI18nErrorMessage() {
		final MessageFormat fmt = new MessageFormat(super.getI18nErrorMessage());
		final String[] args = new String[]{
				definition.getTemplateId()
		};
		return fmt.format(args);
	}
}
