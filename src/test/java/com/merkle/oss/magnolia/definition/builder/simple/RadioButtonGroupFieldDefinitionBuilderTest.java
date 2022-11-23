package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.RadioButtonGroupFieldDefinition;
import info.magnolia.ui.field.factory.RadioButtonGroupFieldFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RadioButtonGroupFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		assertOptionGroupField(new RadioButtonGroupFieldDefinitionBuilder<T>(), (name, builder) -> builder.build(name, dataSourceDefinition));


		final RadioButtonGroupFieldDefinition<T> emptyDefinition = new RadioButtonGroupFieldDefinitionBuilder<T>().build("radioButton", dataSourceDefinition);
		assertEquals(RadioButtonGroupFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(dataSourceDefinition, emptyDefinition.getDatasource());
	}
}
