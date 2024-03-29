package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.pages.app.field.PageLinkFieldDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

import javax.jcr.Node;

public class InternalLinkDefinitionBuilder extends AbstractLinkFieldDefinitionBuilder<Node, LinkFieldDefinition<Node>, InternalLinkDefinitionBuilder> {

	public LinkFieldDefinition<Node> build(final String name) {
		final PageLinkFieldDefinition definition = new PageLinkFieldDefinition();
		super.populate(definition, name, definition.getDatasource());
		return definition;
	}
}
