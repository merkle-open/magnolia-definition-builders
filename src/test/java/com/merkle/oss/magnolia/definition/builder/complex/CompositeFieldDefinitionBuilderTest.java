package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.*;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;
import info.magnolia.ui.framework.layout.TabbedLayoutDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CompositeFieldDefinitionBuilderTest extends AbstractConfiguredComplexPropertyDefinitionBuilderTest {
	private static final String FIELDNAME = "fieldname";
	private CompositeFieldDefinitionBuilder<String> builder;
	private CompositeFieldDefinition<String> fieldDefinition;
	private FieldLayoutDefinition layout;
	private TextFieldDefinition textFieldDefinition;
	private CodeFieldDefinition codeFieldDefinition;

	@BeforeEach
	public void setup() {
		layout = new TabbedLayoutDefinition();
		textFieldDefinition = new TextFieldDefinition();
		codeFieldDefinition = new CodeFieldDefinition();
		builder = new CompositeFieldDefinitionBuilder<>();
		builder = (CompositeFieldDefinitionBuilder) super.setup(builder);
		builder = builder
				.propertyNameDecorator(PrefixNameDecorator.class)
				.layout(layout);
	}

	@Test
	public void testFieldDefinitionBuilder() {
		fieldDefinition = builder.build(FIELDNAME);
		super.testAbstractConfiguredComplexPropertyDefinitionBuilder(fieldDefinition);
		assertEquals(FIELDNAME, fieldDefinition.getName());
		assertEquals(PrefixNameDecorator.class, fieldDefinition.getPropertyNameDecorator());
		assertEquals(layout, fieldDefinition.getLayout());
	}

	@Test
	public void testFieldDefinitionBuilderProperty() {
		builder = builder.property(textFieldDefinition);
		fieldDefinition = builder.build(FIELDNAME);
		assertFalse(fieldDefinition.getProperties().isEmpty());
		assertEquals(textFieldDefinition, fieldDefinition.getProperties().get(0));
		builder = builder.property(codeFieldDefinition);
		fieldDefinition = builder.build(FIELDNAME);
		assertEquals(2, fieldDefinition.getProperties().size());
		assertTrue(fieldDefinition.getProperties().contains(textFieldDefinition));
		assertTrue(fieldDefinition.getProperties().contains(codeFieldDefinition));
	}

	@Test
	public void testFieldDefinitionBuilderProperties() {
		List<EditorPropertyDefinition> properties = List.of(textFieldDefinition, codeFieldDefinition);
		builder = builder.properties(properties);
		fieldDefinition = builder.build(FIELDNAME);
		assertEquals(2, fieldDefinition.getProperties().size());
		assertTrue(fieldDefinition.getProperties().contains(textFieldDefinition));
		assertTrue(fieldDefinition.getProperties().contains(codeFieldDefinition));
	}

}
