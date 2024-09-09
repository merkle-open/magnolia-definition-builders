package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import com.vaadin.ui.ComboBox;
import info.magnolia.ui.contentapp.FilteringMode;
import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;
import info.magnolia.ui.field.LinkFieldBinder;
import info.magnolia.ui.field.LinkFieldDefinition;
import info.magnolia.ui.field.factory.ComboBoxFieldFactory;
import info.magnolia.ui.field.factory.LinkFieldFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		final DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		super.assertLinkField(new LinkFieldDefinitionBuilder<T>(), (name, builder) -> builder.build(name, dataSourceDefinition));

		final LinkFieldDefinition<T> emptyDefinition = new LinkFieldDefinitionBuilder<T>().build("comboBox", dataSourceDefinition);
		assertEquals("ui-framework-core:chooser", emptyDefinition.getChooserId());
		assertTrue(emptyDefinition.isEditable());
		assertEquals(LinkFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(LinkFieldBinder.class, emptyDefinition.getFieldBinderClass());
		assertEquals(FilteringMode.CONTAINS, emptyDefinition.getFilteringMode());
		assertEquals(dataSourceDefinition, emptyDefinition.getDatasource());
	}
}
