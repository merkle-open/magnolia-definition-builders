package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.field.TextFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Provider;
import javax.xml.namespace.QName;

import static org.junit.jupiter.api.Assertions.*;

class TextFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final TextFieldDefinition definition = super.assertField(new TextFieldDefinitionBuilder(), (name, builder) -> builder.build(name))
				.rows(42)
				.maxLength(10)
				.placeholder("somePlaceholder")
				.build("text");
		assertEquals(42, definition.getRows());
		assertEquals(10, definition.getMaxLength());
		assertEquals("somePlaceholder", definition.getPlaceholder());

		final TextFieldDefinition emptyDefinition = new TextFieldDefinitionBuilder().build("text");
		assertEquals(String.class, emptyDefinition.getType());
	}
}
