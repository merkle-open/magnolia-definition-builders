package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.StaticFieldViewDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticFieldViewDefinitionBuilderTest extends AbstractConfiguredComplexPropertyDefinitionBuilderTest {

	private static final String STATIC_FIELD_VIEW = "staticFieldView";
	private static final String VALUE = "value";

	private StaticFieldViewDefinitionBuilder builder;
	private StaticFieldViewDefinition fieldViewDefinition;

	@BeforeEach
	public void setup() {
		builder = new StaticFieldViewDefinitionBuilder();
		builder = (StaticFieldViewDefinitionBuilder) super.setup(builder);
		builder.value(VALUE);
	}

	@Test
	public void testStaticFieldViewDefinitionBuilder() {
		fieldViewDefinition = builder.build(STATIC_FIELD_VIEW);
		super.testAbstractConfiguredComplexPropertyDefinitionBuilder(fieldViewDefinition);
		assertEquals(STATIC_FIELD_VIEW, fieldViewDefinition.getName());
		assertEquals(VALUE, fieldViewDefinition.getValue());
	}

	@Test
	public void testBooleanValuesTrue() {
		builder = (StaticFieldViewDefinitionBuilder) super.setupBooleanValues(builder, true);
		fieldViewDefinition = builder.build(STATIC_FIELD_VIEW);
		super.testBooleanValues(fieldViewDefinition, true);
	}

	@Test
	public void testBooleanValuesFalse() {
		builder = (StaticFieldViewDefinitionBuilder) super.setupBooleanValues(builder, false);
		fieldViewDefinition = builder.build(STATIC_FIELD_VIEW);
		super.testBooleanValues(fieldViewDefinition, false);
	}

}
