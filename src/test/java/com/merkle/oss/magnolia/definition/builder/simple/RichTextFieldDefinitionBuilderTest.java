package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.RichTextFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RichTextFieldDefinitionBuilderTest extends AbstractConfiguredFieldDefinitionBuilderTest {

	private static final String FONTS = "Arial/Arial,sans-serif;Times New Roman/Times New Roman,serif";
	private static final String FONTSIZES = "16/16px;24/24px;48/48px";
	private static final String COLORS = "00923E,F8C100,28166F";
	private static final String CONFIG_JS = "config-magnolia.js";
	private static final long HEIGHT = 1234L;
	private static final String FIELDNAME = "richtextfield";
	private RichTextFieldDefinitionBuilder builder;

	@BeforeEach
	public void setup() {
		builder = new RichTextFieldDefinitionBuilder();
		builder = (RichTextFieldDefinitionBuilder) super.setup(builder);
		builder = builder
				.alignment(true)
				.images(true)
				.lists(true)
				.source(true)
				.tables(true)
				.height(HEIGHT)
				.colors(COLORS)
				.fonts(FONTS)
				.fontSizes(FONTSIZES)
				.configJsFile(CONFIG_JS);
	}

	@Test
	public void testRichTextFieldDefinition() {
		RichTextFieldDefinition fieldDefinition = builder.build(FIELDNAME);
		super.testAbstractConfiguredFieldDefinitionBuilder(fieldDefinition);
		assertEquals(FIELDNAME, fieldDefinition.getName());
		assertTrue(fieldDefinition.isAlignment());
		assertTrue(fieldDefinition.isImages());
		assertTrue(fieldDefinition.isLists());
		assertTrue(fieldDefinition.isSource());
		assertTrue(fieldDefinition.isTables());
		assertEquals(HEIGHT, fieldDefinition.getHeight());
		assertEquals(FONTS, fieldDefinition.getFonts());
		assertEquals(FONTSIZES, fieldDefinition.getFontSizes());
		assertEquals(COLORS, fieldDefinition.getColors());
		assertEquals(CONFIG_JS, fieldDefinition.getConfigJsFile());
		// TODO LinkFieldDefinitions
	}


}
