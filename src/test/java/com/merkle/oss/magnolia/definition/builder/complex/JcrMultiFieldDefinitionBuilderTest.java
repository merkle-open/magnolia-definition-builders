package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.JcrMultiFieldDefinition;
import info.magnolia.ui.field.TextFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JcrMultiFieldDefinitionBuilderTest extends AbstractMultiFieldDefinitionBuilderTest {

	private static final String FIELDNAME = "fieldname";

	private JcrMultiFieldDefinitionBuilder builder;
	private JcrMultiFieldDefinition fieldDefinition;
	private TextFieldDefinition textFieldDefinition;


	@BeforeEach
	public void setup() {
		builder = new JcrMultiFieldDefinitionBuilder();
		textFieldDefinition = new TextFieldDefinition();
		builder = (JcrMultiFieldDefinitionBuilder) super.setup(builder);
	}

	@Test
	public void testFieldDefinitionBuilder() {
		fieldDefinition = builder.build(FIELDNAME, textFieldDefinition);
		super.testAbstractMultiFieldDefinitionBuilder(fieldDefinition);
		assertEquals(FIELDNAME, fieldDefinition.getName());
	}

	@Test
	public void testBooleanTrue() {
		fieldDefinition = ((JcrMultiFieldDefinitionBuilder)super.setupBoolean(builder, true, true))
				.build(FIELDNAME, textFieldDefinition);
		super.testBoolean(fieldDefinition, true, true);
	}

	@Test
	public void testBooleanFalse() {
		fieldDefinition = ((JcrMultiFieldDefinitionBuilder)super.setupBoolean(builder, false, false))
				.build(FIELDNAME, textFieldDefinition);
		super.testBoolean(fieldDefinition, false, false);
	}

}
