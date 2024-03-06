package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.field.HiddenFieldDefinition;
import info.magnolia.ui.field.TextFieldDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HiddenFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final HiddenFieldDefinition<Integer> definition = super.assertField(new HiddenFieldDefinitionBuilder<>(), (name, builder) -> builder.build(name, Integer.class), 42)
				.build("hidden", Integer.class);

		final HiddenFieldDefinition<String> emptyDefinition = new HiddenFieldDefinitionBuilder<String>().build("hidden", String.class);
		assertEquals(String.class, emptyDefinition.getType());
	}
}
