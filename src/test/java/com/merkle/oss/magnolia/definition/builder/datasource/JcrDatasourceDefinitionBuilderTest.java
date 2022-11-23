package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.contentapp.preview.JcrPreviewDefinition;
import info.magnolia.ui.datasource.jcr.JcrDatasourceDefinition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class JcrDatasourceDefinitionBuilderTest {

	@Test
	void testBuilder() {
		final Set<String> allowedNodeTypes = Set.of("1", "2");
		final JcrPreviewDefinition preview = mock(JcrPreviewDefinition.class);

		final JcrDatasourceDefinition definition = new JcrDatasourceDefinitionBuilder()
				.rootPath("rootPath")
				.workspace("workspace")
				.includeProperties(true)
				.includeSystemNodes(true)
				.allowedNodeTypes(allowedNodeTypes)
				.allowedNodeType("3")
				.describeByProperty("describeByProperty")
				.preview(preview)
				.nodeNameProperty("nodeNameProperty")
				.build();

		assertEquals("rootPath", definition.getRootPath());
		assertEquals("workspace", definition.getWorkspace());
		assertTrue(definition.isIncludeProperties());
		assertTrue(definition.isIncludeSystemNodes());
		assertEquals(Set.of("1", "2", "3"), definition.getAllowedNodeTypes());
		assertEquals("describeByProperty", definition.getDescribeByProperty());
		assertEquals(preview, definition.getPreview());
		assertEquals("nodeNameProperty", definition.getNodeNameProperty());

		final JcrDatasourceDefinition emptyDefinition = new JcrDatasourceDefinitionBuilder().build();
		assertEquals("jcr", emptyDefinition.getName());
		assertEquals("/", emptyDefinition.getRootPath());
	}
}
