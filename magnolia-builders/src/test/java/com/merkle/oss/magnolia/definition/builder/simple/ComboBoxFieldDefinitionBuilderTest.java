package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;
import info.magnolia.ui.field.factory.ComboBoxFieldFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComboBoxFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		super.assertComboBoxField(new ComboBoxFieldDefinitionBuilder<T>(), (name, builder) -> builder.build(name, dataSourceDefinition));

		final ComboBoxFieldDefinition<T> emptyDefinition = new ComboBoxFieldDefinitionBuilder<T>().build("comboBox", dataSourceDefinition);
		assertEquals(ComboBoxFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(dataSourceDefinition, emptyDefinition.getDatasource());
	}
}
