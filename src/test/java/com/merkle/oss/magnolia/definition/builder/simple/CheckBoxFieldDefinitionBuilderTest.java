package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.CheckBoxFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckBoxFieldDefinitionBuilderTest extends AbstractConfiguredFieldDefinitionBuilderTest {
	private static final String FIELDNAME = "checkbox";
	private static final String LABEL = "Button Label";

	private CheckBoxFieldDefinitionBuilder builder;

	@BeforeEach
	public void setup() {
		builder = new CheckBoxFieldDefinitionBuilder();
		builder = (CheckBoxFieldDefinitionBuilder) super.setup(builder);
		builder = builder
				.buttonLabel(LABEL);
	}

	@Test
	public void testTextFieldDefinition() {
		CheckBoxFieldDefinition fieldDefinition = builder.build(FIELDNAME);
		super.testAbstractConfiguredFieldDefinitionBuilder(fieldDefinition);
		assertEquals(LABEL, fieldDefinition.getButtonLabel());
		assertEquals(FIELDNAME, fieldDefinition.getName());
	}


}
