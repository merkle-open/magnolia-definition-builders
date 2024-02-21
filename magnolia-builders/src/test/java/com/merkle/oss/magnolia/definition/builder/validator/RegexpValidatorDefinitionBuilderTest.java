package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.RegexpValidatorDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RegexpValidatorDefinitionBuilderTest extends AbstractConfiguredFieldValidatorDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final RegexpValidatorDefinition definition = super.assertValidator(new RegexpValidatorDefinitionBuilder(), (name, builder) -> builder.build(name))
				.pattern("somePattern")
				.build("someRegexpValidator");
		assertEquals("someRegexpValidator", definition.getName());
		assertEquals("somePattern", definition.getPattern());

		final RegexpValidatorDefinition emptyDefinition = new RegexpValidatorDefinitionBuilder().build();
		assertEquals("regexpValidator", emptyDefinition.getName());
		assertNull(emptyDefinition.getPattern());
	}
}
