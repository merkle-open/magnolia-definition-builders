package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.pages.app.field.PageLinkFieldDefinition;
import info.magnolia.ui.contentapp.FilteringMode;
import info.magnolia.ui.datasource.jcr.JcrDatasourceDefinition;
import info.magnolia.ui.field.LinkFieldBinder;
import info.magnolia.ui.field.LinkFieldDefinition;
import info.magnolia.ui.field.factory.LinkFieldFactory;
import org.junit.jupiter.api.Test;

import javax.jcr.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InternalLinkDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		super.assertLinkField(new InternalLinkDefinitionBuilder(), (name, builder) -> builder.build(name));

		final LinkFieldDefinition<Node>emptyDefinition = new InternalLinkDefinitionBuilder().build("comboBox");
		assertEquals("pages-app:chooser", emptyDefinition.getChooserId());
		assertTrue(emptyDefinition.isEditable());
		assertEquals(LinkFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(LinkFieldBinder.class, emptyDefinition.getFieldBinderClass());
		assertEquals(FilteringMode.CONTAINS, emptyDefinition.getFilteringMode());
		JcrDatasourceDefinition jcrDatasource = (JcrDatasourceDefinition) new PageLinkFieldDefinition().getDatasource();
		JcrDatasourceDefinition testDatasource =  (JcrDatasourceDefinition)emptyDefinition.getDatasource();
		assertEquals(jcrDatasource.getWorkspace(), testDatasource.getWorkspace());
		assertEquals(jcrDatasource.getAllowedNodeTypes(), testDatasource.getAllowedNodeTypes());
	}
}
