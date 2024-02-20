package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.pages.app.field.PageLinkFieldDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

import javax.jcr.Node;

public class InternalLinkDefinitionBuilder extends AbstractLinkFieldDefinitionBuilder<Node, LinkFieldDefinition<Node>, InternalLinkDefinitionBuilder> {

	public InternalLinkDefinitionBuilder() {
		super(PageLinkFieldDefinition::new);
	}

	public LinkFieldDefinition<Node> build(final String name) {
		return super.build(name, new PageLinkFieldDefinition().getDatasource());
	}
}
