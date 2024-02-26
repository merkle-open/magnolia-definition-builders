package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.EmailValidatorDefinition;

public class EmailValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<EmailValidatorDefinition, EmailValidatorDefinitionBuilder>{

	public EmailValidatorDefinition build() {
		return build("emailValidator");
	}
	public EmailValidatorDefinition build(final String name) {
		final EmailValidatorDefinition definition = new EmailValidatorDefinition();
		super.populate(definition, name);
		return definition;
	}
}
