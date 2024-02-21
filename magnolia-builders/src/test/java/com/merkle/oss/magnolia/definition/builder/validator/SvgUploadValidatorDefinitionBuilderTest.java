package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.EmailValidatorDefinition;
import info.magnolia.ui.field.SvgUploadValidatorDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SvgUploadValidatorDefinitionBuilderTest extends AbstractConfiguredFieldValidatorDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final SvgUploadValidatorDefinition definition = super.assertValidator(new SvgUploadValidatorDefinitionBuilder(), (name, builder) -> builder.build(name))
				.build("someSvgUploadValidator");
		assertEquals("someSvgUploadValidator", definition.getName());


		final SvgUploadValidatorDefinition emptyDefinition = new SvgUploadValidatorDefinitionBuilder().build();
		assertEquals("svgUploadValidator", emptyDefinition.getName());
	}
}
