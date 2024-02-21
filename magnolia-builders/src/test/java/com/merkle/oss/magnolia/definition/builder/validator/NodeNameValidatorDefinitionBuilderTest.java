package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.editor.validator.NodeNameValidatorDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeNameValidatorDefinitionBuilderTest extends AbstractConfiguredFieldValidatorDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final NodeNameValidatorDefinition definition = super.assertValidator(new NodeNameValidatorDefinitionBuilder(), (name, builder) -> builder.build(name))
				.mode(NodeNameValidatorDefinition.Mode.ADD)
				.pattern("somePattern")
				.build("someNodeNameValidator");
		assertEquals("someNodeNameValidator", definition.getName());
		assertEquals("somePattern", definition.getPattern());
		assertEquals(NodeNameValidatorDefinition.Mode.ADD, definition.getMode());

		final NodeNameValidatorDefinition emptyDefinition = new NodeNameValidatorDefinitionBuilder().build();
		assertEquals("nodeNameValidator", emptyDefinition.getName());
		assertEquals(".+", emptyDefinition.getPattern());
		assertEquals(NodeNameValidatorDefinition.Mode.EDIT, emptyDefinition.getMode());
	}
}
