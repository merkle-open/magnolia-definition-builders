package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.CheckBoxGroupFieldDefinition;
import info.magnolia.ui.field.factory.CheckBoxGroupFieldFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckBoxGroupFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		assertOptionGroupField(new CheckBoxGroupFieldDefinitionBuilder<T>(), (name, builder) -> builder.build(name, dataSourceDefinition));


		final CheckBoxGroupFieldDefinition<T> emptyDefinition = new CheckBoxGroupFieldDefinitionBuilder<T>().build("checkBoxGroup", dataSourceDefinition);
		assertEquals(CheckBoxGroupFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(dataSourceDefinition, emptyDefinition.getDatasource());
	}
}
