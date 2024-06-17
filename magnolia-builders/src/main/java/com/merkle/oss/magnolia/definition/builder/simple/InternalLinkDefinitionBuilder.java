package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.pages.app.field.PageLinkFieldDefinition;
import info.magnolia.ui.datasource.jcr.JcrDatasourceDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.jcr.Node;

public class InternalLinkDefinitionBuilder extends AbstractLinkFieldDefinitionBuilder<Node, LinkFieldDefinition<Node>, InternalLinkDefinitionBuilder> {

	@Nullable
	private String rootPath;

	public InternalLinkDefinitionBuilder() {}
	public InternalLinkDefinitionBuilder(final LinkFieldDefinition<Node> definition) {
		super(definition);
	}

	public LinkFieldDefinition<Node> build(final String name) {
		final PageLinkFieldDefinition definition = new PageLinkFieldDefinition();
		final JcrDatasourceDefinition datasource = (JcrDatasourceDefinition)definition.getDatasource();
		Optional.ofNullable(rootPath).ifPresent(datasource::setRootPath);
		super.populate(definition, name, datasource);
		return definition;
	}

	public InternalLinkDefinitionBuilder rootPath(final String rootPath) {
		this.rootPath = rootPath;
		return self();
	}
}
