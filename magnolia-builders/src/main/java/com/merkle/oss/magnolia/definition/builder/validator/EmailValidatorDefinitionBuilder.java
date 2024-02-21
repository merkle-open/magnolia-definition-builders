package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.EmailValidatorDefinition;

public class EmailValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<EmailValidatorDefinition, EmailValidatorDefinitionBuilder>{

	public EmailValidatorDefinitionBuilder() {
		super(EmailValidatorDefinition::new);
	}

	public EmailValidatorDefinition build() {
		return build("emailValidator");
	}
	public EmailValidatorDefinition build(final String name) {
		return super.build(name);
	}
}
