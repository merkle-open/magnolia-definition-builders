package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.CodeFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeFieldDefinitionBuilderTest extends AbstractConfiguredFieldDefinitionBuilderTest {

	private static final String LANGUAGE = "Javascript";
	private static final int HEIGHT = 123;
	private static final String FIELDNAME = "codefield";

	private CodeFieldDefinitionBuilder builder;

	@BeforeEach
	public void setup() {
		builder = new CodeFieldDefinitionBuilder();
		builder = (CodeFieldDefinitionBuilder) super.setup(builder);
		builder = builder
				.height(HEIGHT)
				.language(LANGUAGE);
	}

	@Test
	public void testRichTextFieldDefinition() {
		CodeFieldDefinition fieldDefinition = builder.build(FIELDNAME);
		super.testAbstractConfiguredFieldDefinitionBuilder(fieldDefinition);
		assertEquals(FIELDNAME, fieldDefinition.getName());
		assertEquals(LANGUAGE, fieldDefinition.getLanguage());
		assertEquals(HEIGHT, fieldDefinition.getHeight());
	}


}
