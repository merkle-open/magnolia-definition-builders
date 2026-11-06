package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ai.automations.config.ExtensionConfiguration;
import info.magnolia.ai.automations.ui.fields.AiAssistedTextFieldDefinition;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AiAssistedTextFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final CurrentItemProviderDefinition itemProvider = new CurrentItemProviderDefinition<>();
		final AiAssistedTextFieldDefinition definition = super.assertComplexField(new AiAssistedTextFieldDefinitionBuilder(), (name, builder) -> builder.build(name), itemProvider)
				.rows(42)
				.maxLength(10)
				.placeholder("somePlaceholder")
				.extension("someExtension")
				.taskId("someTaskId")
				.build("text");
		assertEquals(42, definition.getRows());
		assertEquals(10, definition.getMaxLength());
		assertEquals("somePlaceholder", definition.getPlaceholder());
		assertEquals("someTaskId", definition.getTaskId());
		assertTrue(definition.getExtensions().containsKey("someExtension"));
		final ExtensionConfiguration extensionConfiguration = new ExtensionConfiguration();
		extensionConfiguration.setExtension("someExtension");
		assertEquals(extensionConfiguration, definition.getExtensions().get("someExtension"));

		final AiAssistedTextFieldDefinition emptyDefinition = new AiAssistedTextFieldDefinitionBuilder().build("text");
		assertEquals(FormView.class, emptyDefinition.getImplementationClass());
		assertEquals(CurrentItemProviderDefinition.class, emptyDefinition.getItemProvider().getClass());
	}
}
