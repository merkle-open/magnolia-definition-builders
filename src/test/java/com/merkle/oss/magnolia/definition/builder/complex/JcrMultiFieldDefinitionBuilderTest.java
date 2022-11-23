package com.merkle.oss.magnolia.definition.builder.complex;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.editor.DefaultJcrNodeOrderHandler;
import info.magnolia.ui.editor.JcrChildNodeProviderDefinition;
import info.magnolia.ui.editor.MultiFieldEntryResolution;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.JcrMultiFieldDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class JcrMultiFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	void testBuilder() {
		final EditorPropertyDefinition field = mock(EditorPropertyDefinition.class);
		super.assertMultiField(new JcrMultiFieldDefinitionBuilder(), (name, builder) -> builder.build(name, field));

		final JcrMultiFieldDefinition emptyDefinition = new JcrMultiFieldDefinitionBuilder().build("multi", field);
		assertEquals(MultiFieldEntryResolution.Definition.class, emptyDefinition.getEntryResolution().getClass());
		assertEquals(DefaultJcrNodeOrderHandler.Definition.class, emptyDefinition.getOrderHandler().getClass());
		assertEquals(JcrChildNodeProviderDefinition.class, emptyDefinition.getItemProvider().getClass());
		assertTrue(emptyDefinition.isCanRemoveItems());
	}
}
