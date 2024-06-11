package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.TwinColSelectFieldDefinition;
import info.magnolia.ui.field.factory.TwinColSelectFieldFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwinColSelectFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		final TwinColSelectFieldDefinition<T> definition = assertSelectField(new TwinColSelectFieldDefinitionBuilder<T>(), (name, builder) -> builder.build(name, dataSourceDefinition))
				.leftColumnCaption("someLeftColumnCaption")
				.rightColumnCaption("someRightColumnCaption")
				.build("someName", dataSourceDefinition);
		assertEquals("someLeftColumnCaption", definition.getLeftColumnCaption());
		assertEquals("someRightColumnCaption", definition.getRightColumnCaption());

		final TwinColSelectFieldDefinition<T> emptyDefinition = new TwinColSelectFieldDefinitionBuilder<T>().build("twinColSelection", dataSourceDefinition);
		assertEquals(TwinColSelectFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(dataSourceDefinition, emptyDefinition.getDatasource());
	}
}
