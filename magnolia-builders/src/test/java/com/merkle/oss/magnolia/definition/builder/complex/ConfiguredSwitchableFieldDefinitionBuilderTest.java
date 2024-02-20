package com.merkle.oss.magnolia.definition.builder.complex;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;
import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.editor.SwitchableFormView;
import info.magnolia.ui.field.AbstractSelectFieldDefinition;
import info.magnolia.ui.field.ConfiguredSwitchableFieldDefinition;
import info.magnolia.ui.field.PrefixNameDecorator;
import info.magnolia.ui.field.WithPropertyNameDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ConfiguredSwitchableFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final AbstractSelectFieldDefinition<Option, OptionListDefinition> field = mock(AbstractSelectFieldDefinition.class);
		final FormDefinition<T> form1 = mock(FormDefinition.class);
		final FormDefinition<T> form2 = mock(FormDefinition.class);
		final Class<WithPropertyNameDecorator.PropertyNameDecorator> propertyNameDecorator = WithPropertyNameDecorator.PropertyNameDecorator.class;

		final ConfiguredSwitchableFieldDefinition<T> definition = super.assertComplexField(new ConfiguredSwitchableFieldDefinitionBuilder<T>(), (name, builder) -> builder.build(name, field))
				.form(form1)
				.form(form2)
				.propertyNameDecorator(propertyNameDecorator)
				.build("switchable", field);

		assertEquals(field, definition.getField());
		assertEquals(Arrays.asList(form1, form2), definition.getForms());
		assertEquals(propertyNameDecorator, definition.getPropertyNameDecorator());

		final ConfiguredSwitchableFieldDefinition<T> emptyDefinition = new ConfiguredSwitchableFieldDefinitionBuilder<T>().build("switchable", field);
		assertEquals(SwitchableFormView.class, emptyDefinition.getImplementationClass());
	}
}