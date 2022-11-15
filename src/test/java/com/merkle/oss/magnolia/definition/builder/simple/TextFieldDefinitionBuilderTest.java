package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.TextFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextFieldDefinitionBuilderTest extends AbstractConfiguredFieldDefinitionBuilderTest {
	private static final int MAX_LENGTH = 100;
	private static final String FIELDNAME = "textfield";
	private static final String PLACEHOLDER = "placeholder";
	private static final int ROWS = 42;

	private TextFieldDefinitionBuilder builder;
	private TextFieldDefinition fieldDefinition;

	@BeforeEach
	public void setup() {
		builder = new TextFieldDefinitionBuilder();
		builder = (TextFieldDefinitionBuilder) super.setup(builder);
		builder = builder
				.maxLength(MAX_LENGTH)
				.rows(ROWS)
				.placeholder(PLACEHOLDER);
	}

	@Test
	public void testTextFieldDefinition() {
		fieldDefinition = builder.build(FIELDNAME);
		super.testAbstractConfiguredFieldDefinitionBuilder(fieldDefinition);
		assertEquals(MAX_LENGTH, fieldDefinition.getMaxLength());
		assertEquals(ROWS, fieldDefinition.getRows());
		assertEquals(PLACEHOLDER, fieldDefinition.getPlaceholder());
		assertEquals(FIELDNAME, fieldDefinition.getName());
	}

	@Test
	public void testTextFieldDefinitionBooleanValuesTrue() {
		builder = (TextFieldDefinitionBuilder) super.setupBooleanValues(builder, true, true, true);
		fieldDefinition = builder.build(FIELDNAME);
		super.testBooleanValues(fieldDefinition, true, true, true);
	}

	@Test
	public void testTextFieldDefinitionBooleanValuesFalse() {
		builder = (TextFieldDefinitionBuilder) super.setupBooleanValues(builder, false, false, false);
		fieldDefinition = builder.build(FIELDNAME);
		super.testBooleanValues(fieldDefinition, false, false, false);
	}
}
