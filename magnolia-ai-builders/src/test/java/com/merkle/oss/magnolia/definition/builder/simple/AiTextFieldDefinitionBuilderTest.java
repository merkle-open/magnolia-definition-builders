package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ai.automations.ui.fields.AiTextFieldDefinition;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AiTextFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final AiTextFieldDefinition definition = super.assertComplexField(new AiTextFieldDefinitionBuilder(), (name, prompt,builder) -> builder.build(name, prompt))
				.rows(42)
				.words(10)
				.taskId("someTaskId")
				.promptProperty("testText", "testTextFieldName")
				.build("text", "somePrompt");
		assertEquals("42", definition.getRows());
		assertEquals("10", definition.getWords());
		assertEquals("somePrompt", definition.getPrompt());
		assertEquals("someTaskId", definition.getTaskId());
		assertTrue(definition.getPromptProperties().containsKey("testText"));
		assertEquals("testTextFieldName", definition.getPromptProperties().get("testText"));

		final AiTextFieldDefinition emptyDefinition = new AiTextFieldDefinitionBuilder().build("text", "somePrompt");
		assertEquals(FormView.class, emptyDefinition.getImplementationClass());
		assertEquals(CurrentItemProviderDefinition.class, emptyDefinition.getItemProvider().getClass());
	}
}
