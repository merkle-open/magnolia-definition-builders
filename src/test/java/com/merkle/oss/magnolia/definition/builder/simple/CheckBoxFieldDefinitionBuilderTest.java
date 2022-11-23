package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.field.CheckBoxFieldDefinition;
import info.magnolia.ui.field.factory.CheckBoxFieldFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckBoxFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	void testBuilder() {
		final CheckBoxFieldDefinition definition = super.assertField(new CheckBoxFieldDefinitionBuilder(), (name, builder) -> builder.build(name), false)
				.buttonLabel("someLabel")
				.build("checkbox");
		assertEquals("someLabel", definition.getButtonLabel());

		final CheckBoxFieldDefinition emptyDefinition = new CheckBoxFieldDefinitionBuilder().build("checkbox");
		assertEquals(Boolean.class, emptyDefinition.getType());
		assertEquals(CheckBoxFieldFactory.class, emptyDefinition.getFactoryClass());
	}
}
