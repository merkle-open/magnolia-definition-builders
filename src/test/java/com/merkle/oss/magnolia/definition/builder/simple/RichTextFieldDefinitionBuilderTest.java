package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.repository.RepositoryConstants;
import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;
import info.magnolia.ui.field.RichTextFieldDefinition;
import info.magnolia.ui.field.SafeHtmlValidatorDefinition;
import info.magnolia.ui.field.factory.ComboBoxFieldFactory;
import info.magnolia.ui.field.factory.RichTextFieldFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RichTextFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	void testBuilder() {
		final RichTextFieldDefinition definition = super.assertField(new RichTextFieldDefinitionBuilder(), (name, builder) -> builder.build(name))
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

		final RichTextFieldDefinition emptyDefinition = new RichTextFieldDefinitionBuilder().build("richText");
		assertEquals(String.class, emptyDefinition.getType());
		assertEquals(RichTextFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(0, emptyDefinition.getValidators().size());
		assertEquals(1, emptyDefinition.getLinkFieldDefinitions().size());
		assertNotNull(emptyDefinition.getLinkFieldDefinitions().get(RepositoryConstants.WEBSITE));
	}
}
