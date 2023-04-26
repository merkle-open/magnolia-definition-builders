package com.merkle.oss.magnolia.definition.builder.complex;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.editor.ByMultiValueProperties;
import info.magnolia.ui.editor.JcrPropertyProvider;
import info.magnolia.ui.editor.MultiValuePropertyOrderHandler;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.JcrMultiValueFieldDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class JcrMultiValueFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	void testBuilder() {
		final EditorPropertyDefinition field = mock(EditorPropertyDefinition.class);
		super.assertMultiField(new JcrMultiValueFieldDefinitionBuilder(), (name, builder) -> builder.build(name, field));

		final JcrMultiValueFieldDefinition emptyDefinition = new JcrMultiValueFieldDefinitionBuilder().build("multi", field);
		assertEquals(ByMultiValueProperties.Definition.class, emptyDefinition.getEntryResolution().getClass());
		assertEquals(MultiValuePropertyOrderHandler.Definition.class, emptyDefinition.getOrderHandler().getClass());
		assertEquals(JcrPropertyProvider.Definition.class, emptyDefinition.getItemProvider().getClass());
		assertTrue(emptyDefinition.isCanRemoveItems());
	}
}
