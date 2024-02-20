package com.merkle.oss.magnolia.definition.builder.complex;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.editor.FormView;
import info.magnolia.ui.field.CompositeFieldDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.NoopNameDecorator;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class CompositeFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final FieldLayoutDefinition<?> layout = mock(FieldLayoutDefinition.class);
		final EditorPropertyDefinition property1 = mock(EditorPropertyDefinition.class);
		final EditorPropertyDefinition property2 = mock(EditorPropertyDefinition.class);

		final CompositeFieldDefinition<T> definition = super.assertComplexField(new CompositeFieldDefinitionBuilder<T>(), (name, builder) -> builder.build(name))
				.layout(layout)
				.propertyNameDecorator(NoopNameDecorator.class)
				.property(property1)
				.property(property2)
				.build("composite");

		assertEquals(layout, definition.getLayout());
		assertEquals(NoopNameDecorator.class, definition.getPropertyNameDecorator());
		assertEquals(Arrays.asList(property1, property2), definition.getProperties());

		final CompositeFieldDefinition<T> emptyDefinition = new CompositeFieldDefinitionBuilder<T>().build("composite");
		assertEquals(FormView.class, emptyDefinition.getImplementationClass());
	}
}