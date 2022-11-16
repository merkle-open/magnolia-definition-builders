package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.LinkFieldDefinition;
import info.magnolia.ui.field.RichTextFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, LinkFieldDefinition<?>> linkFieldDefinitions;
	private LinkFieldDefinition<String> linkFieldDefinition1, linkFieldDefinition2;

	@BeforeEach
	public void setup() {
		linkFieldDefinition1 = new LinkFieldDefinition<>();
		linkFieldDefinition2 = new LinkFieldDefinition<>();
		linkFieldDefinitions = new HashMap<>();
		linkFieldDefinitions.put("1", linkFieldDefinition1);
		linkFieldDefinitions.put("2", linkFieldDefinition2);
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
	}

	@Test
	public void testLinkFieldDefinitions1 (){
		RichTextFieldDefinition fieldDefinition = builder
				.linkFieldDefinition("1", linkFieldDefinition1)
				.linkFieldDefinition("2", linkFieldDefinition2)
				.build(FIELDNAME);
		assertEquals(linkFieldDefinition1, fieldDefinition.getLinkFieldDefinitions().get("1"));
		assertEquals(linkFieldDefinition2, fieldDefinition.getLinkFieldDefinitions().get("2"));
	}

	@Test
	public void testLinkFieldDefinitions2 (){
		RichTextFieldDefinition fieldDefinition = builder
				.linkFieldDefinitions(linkFieldDefinitions)
				.build(FIELDNAME);
		assertEquals(linkFieldDefinition1, fieldDefinition.getLinkFieldDefinitions().get("1"));
		assertEquals(linkFieldDefinition2, fieldDefinition.getLinkFieldDefinitions().get("2"));
	}

}
