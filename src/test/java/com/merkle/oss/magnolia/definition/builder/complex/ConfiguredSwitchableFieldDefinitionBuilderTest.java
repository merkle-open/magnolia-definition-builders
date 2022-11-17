package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.field.ConfiguredSwitchableFieldDefinition;
import info.magnolia.ui.field.PrefixNameDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ConfiguredSwitchableFieldDefinitionBuilderTest extends AbstractConfiguredComplexPropertyDefinitionBuilderTest {
	private static final String FIELDNAME = "fieldname";
	private ConfiguredSwitchableFieldDefinitionBuilder<String> builder;
	private ConfiguredSwitchableFieldDefinition<String> fieldDefinition;
	private FormDefinition<String> formDefinition1, formDefinition2;

	@BeforeEach
	public void setup() {
		formDefinition1 = new ConfiguredFormDefinition<>();
		formDefinition2 = new ConfiguredFormDefinition<>();
		builder = new ConfiguredSwitchableFieldDefinitionBuilder<>();
		builder = (ConfiguredSwitchableFieldDefinitionBuilder) super.setup(builder);
		builder = builder.propertyNameDecorator(PrefixNameDecorator.class);
	}

	@Test
	public void testFieldDefinitionBuilder() {
		fieldDefinition = builder.build(FIELDNAME, null);
		super.testAbstractConfiguredComplexPropertyDefinitionBuilder(fieldDefinition);
		assertEquals(FIELDNAME, fieldDefinition.getName());
		assertEquals(PrefixNameDecorator.class, fieldDefinition.getPropertyNameDecorator());
	}

	@Test
	public void testFieldDefinitionBuilderForm() {
		builder = builder.form(formDefinition1);
		fieldDefinition = builder.build(FIELDNAME, null);
		assertEquals(FIELDNAME, fieldDefinition.getName());
		assertFalse(fieldDefinition.getForms().isEmpty());
		assertEquals(formDefinition1, fieldDefinition.getForms().get(0));
		builder = builder.form(formDefinition2);
		fieldDefinition = builder.build(FIELDNAME, null);
		assertEquals(2, fieldDefinition.getForms().size());
		assertTrue(fieldDefinition.getForms().contains(formDefinition1));
		assertTrue(fieldDefinition.getForms().contains(formDefinition2));
	}

	@Test
	public void testFieldDefinitionBuilderForms() {
		List<FormDefinition<String>> formsList = List.of(formDefinition1, formDefinition2);
		builder = builder.forms(formsList);
		fieldDefinition = builder.build(FIELDNAME, null);
		assertEquals(2, fieldDefinition.getForms().size());
		assertTrue(fieldDefinition.getForms().contains(formDefinition1));
		assertTrue(fieldDefinition.getForms().contains(formDefinition2));
	}

}
