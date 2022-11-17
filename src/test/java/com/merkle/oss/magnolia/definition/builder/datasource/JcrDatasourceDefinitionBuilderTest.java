package com.merkle.oss.magnolia.definition.builder.datasource;

import com.vaadin.shared.data.sort.SortDirection;
import info.magnolia.ui.contentapp.preview.JcrPreviewDefinition;
import info.magnolia.ui.datasource.jcr.JcrDatasourceDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JcrDatasourceDefinitionBuilderTest {

	private static final String ROOT_PATH = "rootPath";
	private static final String WORKSPACE = "workspace";
	private static final String DESCRIBE_BY_PROPERTY = "describeByProperty";
	private static final String NODE_NAME_PROPERTY = "nodeNameProperty";
	private JcrDatasourceDefinitionBuilder builder;
	private JcrPreviewDefinition jcrPreviewDefinition;

	@BeforeEach
	public void setup() {
		jcrPreviewDefinition = new JcrPreviewDefinition();
		builder = new JcrDatasourceDefinitionBuilder()
				.rootPath(ROOT_PATH)
				.workspace(WORKSPACE)
				.describeByProperty(DESCRIBE_BY_PROPERTY)
				.preview(jcrPreviewDefinition)
				.nodeNameProperty(NODE_NAME_PROPERTY)
		;
	}

	@Test
	public void testBuilder() {
		JcrDatasourceDefinition definition = builder.build();
		assertEquals(ROOT_PATH, definition.getRootPath());
		assertEquals(WORKSPACE, definition.getWorkspace());
		assertEquals(DESCRIBE_BY_PROPERTY, definition.getDescribeByProperty());
		assertEquals(NODE_NAME_PROPERTY, definition.getNodeNameProperty());
		assertEquals(jcrPreviewDefinition, definition.getPreview());
		assertNotNull(definition.getSortBy());
		assertTrue(definition.getSortBy().isEmpty());
		assertNotNull(definition.getAllowedNodeTypes());
		assertTrue(definition.getAllowedNodeTypes().isEmpty());
	}

	@Test
	public void testBuilderBoolean() {
		JcrDatasourceDefinition definition = builder
				.includeProperties(true)
				.build();
		assertTrue(definition.isIncludeProperties());
		assertFalse(definition.isIncludeSystemNodes());
		definition = builder
				.includeProperties(false)
				.includeSystemProperties(null)
				.includeSystemNodes(true)
				.build();
		assertFalse(definition.isIncludeProperties());
		assertFalse(definition.isIncludeSystemProperties());
		assertTrue(definition.isIncludeSystemNodes());
	}

	@Test
	public void testBuilderAllowedNodeTypes() {
		JcrDatasourceDefinition definition = builder
				.allowedNodeType(ROOT_PATH)
				.allowedNodeType(WORKSPACE)
				.allowedNodeType(ROOT_PATH)
				.build();
		assertEquals(2, definition.getAllowedNodeTypes().size());
		assertTrue(definition.getAllowedNodeTypes().contains(ROOT_PATH));
		assertTrue(definition.getAllowedNodeTypes().contains(WORKSPACE));
		assertFalse(definition.getAllowedNodeTypes().contains(NODE_NAME_PROPERTY));
		definition = builder
				.allowedNodeTypes(Set.of(ROOT_PATH, WORKSPACE))
				.allowedNodeType(DESCRIBE_BY_PROPERTY)
				.build();
		assertEquals(3, definition.getAllowedNodeTypes().size());
		assertTrue(definition.getAllowedNodeTypes().contains(ROOT_PATH));
		assertTrue(definition.getAllowedNodeTypes().contains(WORKSPACE));
		assertTrue(definition.getAllowedNodeTypes().contains(DESCRIBE_BY_PROPERTY));
		assertFalse(definition.getAllowedNodeTypes().contains(NODE_NAME_PROPERTY));
	}

	@Test
	public void testBuilderSortBy() {
		JcrDatasourceDefinition definition = builder
				.sortBy(ROOT_PATH, SortDirection.ASCENDING)
				.sortBy(WORKSPACE, SortDirection.DESCENDING)
				.build();
		assertEquals(2, definition.getSortBy().size());
		assertEquals(SortDirection.ASCENDING, definition.getSortBy().get(ROOT_PATH));
		assertNull(definition.getSortBy().get(DESCRIBE_BY_PROPERTY));
		definition = builder
				.sortBy(Map.of(ROOT_PATH, SortDirection.ASCENDING, WORKSPACE, SortDirection.DESCENDING, NODE_NAME_PROPERTY, SortDirection.ASCENDING))
				.build();
		assertEquals(3, definition.getSortBy().size());
		assertEquals(SortDirection.ASCENDING, definition.getSortBy().get(ROOT_PATH));
		assertEquals(SortDirection.ASCENDING, definition.getSortBy().get(NODE_NAME_PROPERTY));
		assertEquals(SortDirection.DESCENDING, definition.getSortBy().get(WORKSPACE));
		assertNull(definition.getSortBy().get(DESCRIBE_BY_PROPERTY));
	}
}
