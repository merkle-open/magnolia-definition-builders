package com.merkle.oss.magnolia.definition.builder.datasource;

import com.vaadin.shared.data.sort.SortDirection;
import info.magnolia.ui.contentapp.preview.JcrPreviewDefinition;
import info.magnolia.ui.datasource.jcr.JcrDatasourceDefinition;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JcrDatasourceDefinitionBuilder extends AbstractBaseDatasourceDefinitionBuilder<JcrDatasourceDefinition, JcrDatasourceDefinitionBuilder>{
	@Nullable
	private String rootPath;
	@Nullable
	private String workspace;
	@Nullable
	private Boolean includeProperties;
	@Nullable
	private Boolean includeSystemProperties;
	@Nullable
	private Boolean includeSystemNodes;
	@Nullable
	private Set<String> allowedNodeTypes;
	@Nullable
	private String describeByProperty;
	@Nullable
	private JcrPreviewDefinition preview;
	@Nullable
	private String nodeNameProperty;
	@Nullable
	private Map<String, SortDirection> sortBy;

	public JcrDatasourceDefinitionBuilder() {
		super(JcrDatasourceDefinition::new);
	}

	public JcrDatasourceDefinitionBuilder rootPath(final String rootPath) {
		this.rootPath = rootPath;
		return self();
	}

	public JcrDatasourceDefinitionBuilder workspace(final String workspace) {
		this.workspace = workspace;
		return self();
	}

	public JcrDatasourceDefinitionBuilder includeProperties(final Boolean includeProperties) {
		this.includeProperties = includeProperties;
		return self();
	}

	public JcrDatasourceDefinitionBuilder includeSystemProperties(final Boolean includeSystemProperties) {
		this.includeSystemProperties = includeSystemProperties;
		return self();
	}

	public JcrDatasourceDefinitionBuilder includeSystemNodes(final Boolean includeSystemNodes) {
		this.includeSystemNodes = includeSystemNodes;
		return self();
	}

	public JcrDatasourceDefinitionBuilder allowedNodeType(final String allowedNodeType) {
		return allowedNodeTypes(Stream.concat(
				Stream.ofNullable(allowedNodeTypes).flatMap(Collection::stream),
				Stream.of(allowedNodeType)
		).collect(Collectors.toSet()));
	}

	public JcrDatasourceDefinitionBuilder allowedNodeTypes(final Set<String> allowedNodeTypes) {
		this.allowedNodeTypes = allowedNodeTypes;
		return self();
	}

	public JcrDatasourceDefinitionBuilder describeByProperty(final String describeByProperty) {
		this.describeByProperty = describeByProperty;
		return self();
	}

	public JcrDatasourceDefinitionBuilder preview(final JcrPreviewDefinition preview) {
		this.preview = preview;
		return self();
	}

	public JcrDatasourceDefinitionBuilder nodeNameProperty(final String nodeNameProperty) {
		this.nodeNameProperty = nodeNameProperty;
		return self();
	}

	public JcrDatasourceDefinitionBuilder sortBy(final String key, final SortDirection value) {
		return sortBy(Stream.concat(
				Stream.ofNullable(sortBy).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(key, value))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}

	public JcrDatasourceDefinitionBuilder sortBy(final Map<String, SortDirection> sortBy) {
		this.sortBy = sortBy;
		return self();
	}

	@Override
	public JcrDatasourceDefinition build() {
		final JcrDatasourceDefinition definition = super.build();
		Optional.ofNullable(rootPath).ifPresent(definition::setRootPath);
		Optional.ofNullable(workspace).ifPresent(definition::setWorkspace);
		Optional.ofNullable(includeProperties).ifPresent(definition::setIncludeProperties);
		Optional.ofNullable(includeSystemProperties).ifPresent(definition::setIncludeSystemProperties);
		Optional.ofNullable(includeSystemNodes).ifPresent(definition::setIncludeSystemNodes);
		Stream.ofNullable(allowedNodeTypes).flatMap(Collection::stream).forEach(definition.getAllowedNodeTypes()::add);
		Optional.ofNullable(describeByProperty).ifPresent(definition::setDescribeByProperty);
		Optional.ofNullable(preview).ifPresent(definition::setPreview);
		Optional.ofNullable(nodeNameProperty).ifPresent(definition::setNodeNameProperty);
		Stream.ofNullable(sortBy).map(Map::entrySet).flatMap(Collection::stream).forEach(entry ->
				definition.getSortBy().put(entry.getKey(), entry.getValue())
		);
		return definition;
	}
}
