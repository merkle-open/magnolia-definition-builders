package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.field.CodeFieldDefinition;
import info.magnolia.ui.field.factory.CodeFieldFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	void testBuilder() {
		final CodeFieldDefinition definition = super.assertField(new CodeFieldDefinitionBuilder(), (name, builder) -> builder.build(name))
				.language("someLanguage")
				.height(42)
				.build("code");
		assertEquals("someLanguage", definition.getLanguage());
		assertEquals(42, definition.getHeight());

		final CodeFieldDefinition emptyDefinition = new CodeFieldDefinitionBuilder().build("code");
		assertEquals(300, emptyDefinition.getHeight());
		assertEquals(String.class, emptyDefinition.getType());
		assertEquals(CodeFieldFactory.class, emptyDefinition.getFactoryClass());
	}
}
