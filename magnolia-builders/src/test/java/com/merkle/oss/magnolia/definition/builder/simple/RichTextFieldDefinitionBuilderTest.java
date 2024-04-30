package com.merkle.oss.magnolia.definition.builder.simple;

import static org.junit.jupiter.api.Assertions.*;

import info.magnolia.dam.app.field.DamRichTextFieldDefinition;
import info.magnolia.dam.app.field.factory.DamRichTextFieldFactory;
import info.magnolia.repository.RepositoryConstants;

import org.junit.jupiter.api.Test;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;

class RichTextFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	void testBuilder() {
		final DamRichTextFieldDefinition definition = super.assertField(new RichTextFieldDefinitionBuilder(), (name, builder) -> builder.build(name))
				.alignment(true)
				.images(true)
				.lists(false)
				.source(true)
				.tables(true)
				.height(42)
				.colors("someColors")
				.fonts("someFonts")
				.fontSizes("someFontSizes")
				.configJsFile("someConfigJsFile")
				.build("richText");

		assertTrue(definition.isAlignment());
		assertTrue(definition.isImages());
		assertFalse(definition.isLists());
		assertTrue(definition.isSource());
		assertTrue(definition.isTables());
		assertEquals(42, definition.getHeight());
		assertEquals("someColors", definition.getColors());
		assertEquals("someFonts", definition.getFonts());
		assertEquals("someFontSizes", definition.getFontSizes());
		assertEquals("someConfigJsFile", definition.getConfigJsFile());

		final DamRichTextFieldDefinition emptyDefinition = new RichTextFieldDefinitionBuilder().build("richText");
		assertEquals(String.class, emptyDefinition.getType());
		assertEquals(DamRichTextFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(0, emptyDefinition.getValidators().size());
		assertEquals(2, emptyDefinition.getLinkFieldDefinitions().size());
		assertNotNull(emptyDefinition.getLinkFieldDefinitions().get(RepositoryConstants.WEBSITE));
	}
}
