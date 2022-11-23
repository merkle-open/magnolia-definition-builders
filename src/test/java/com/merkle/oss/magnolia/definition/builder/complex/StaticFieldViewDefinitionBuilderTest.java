package com.merkle.oss.magnolia.definition.builder.complex;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.field.StaticFieldView;
import info.magnolia.ui.field.StaticFieldViewDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticFieldViewDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final StaticFieldViewDefinition<T> definition = super.assertComplexField(new StaticFieldViewDefinitionBuilder<T>(), (name, builder) -> builder.build(name))
				.value("someValue")
				.build("static");
		assertEquals("someValue", definition.getValue());

		final StaticFieldViewDefinition<T> emptyDefinition = new StaticFieldViewDefinitionBuilder<T>().build("static");
		assertEquals(StaticFieldView.class, emptyDefinition.getImplementationClass());
		assertEquals(CurrentItemProviderDefinition.class, emptyDefinition.getItemProvider().getClass());
	}
}