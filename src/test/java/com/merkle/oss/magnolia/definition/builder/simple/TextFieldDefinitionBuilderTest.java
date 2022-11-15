package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.TextFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextFieldDefinitionBuilderTest extends AbstractConfiguredFieldDefinitionBuilderTest {

	private TextFieldDefinitionBuilder builder;
	private TextFieldDefinition fieldDefinition;

	@BeforeEach
	public void setup() {
		builder = new TextFieldDefinitionBuilder();
		builder = (TextFieldDefinitionBuilder) super.setup(builder);
		builder = builder
				.maxLength(100)
				.rows(2)
				.placeholder("placeholder");
	}

	@Test
	public void testTextFieldDefinition() {
		fieldDefinition = builder.build("textfield");
		super.testAbstractConfiguredFieldDefinitionBuilder(fieldDefinition);
		assertEquals(100, fieldDefinition.getMaxLength());
		assertEquals(2, fieldDefinition.getRows());
		assertEquals("placeholder", fieldDefinition.getPlaceholder());
		assertEquals("textfield", fieldDefinition.getName());
	}

	@Test
	public void testTextFieldDefinitionBooleanValuesTrue() {
		builder = (TextFieldDefinitionBuilder) super.setupBooleanValues(builder, true, true, true);
		fieldDefinition = builder.build("textfield");
		super.testBooleanValues(fieldDefinition, true, true, true);
	}

	@Test
	public void testTextFieldDefinitionBooleanValuesFalse() {
		builder = (TextFieldDefinitionBuilder) super.setupBooleanValues(builder, false, false, false);
		fieldDefinition = builder.build("textfield");
		super.testBooleanValues(fieldDefinition, false, false, false);
	}
}
