package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.EmailValidatorDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailValidatorDefinitionBuilderTest extends AbstractConfiguredFieldValidatorDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final EmailValidatorDefinition definition = super.assertValidator(new EmailValidatorDefinitionBuilder(), (name, builder) -> builder.build(name))
				.build("somEmailValidator");
		assertEquals("somEmailValidator", definition.getName());

		final EmailValidatorDefinition emptyDefinition = new EmailValidatorDefinitionBuilder().build();
		assertEquals("emailValidator", emptyDefinition.getName());
	}
}
