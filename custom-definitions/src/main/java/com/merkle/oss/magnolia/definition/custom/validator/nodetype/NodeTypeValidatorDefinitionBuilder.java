package com.merkle.oss.magnolia.definition.custom.validator.nodetype;

import com.merkle.oss.magnolia.definition.builder.validator.AbstractConfiguredFieldValidatorDefinitionBuilder;

public class NodeTypeValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<NodeTypeValidatorDefinition, NodeTypeValidatorDefinitionBuilder> {

	public NodeTypeValidatorDefinition build(final String nodeType) {
		return build("nodeTypeValidator", nodeType);
	}
	public NodeTypeValidatorDefinition build(final String name, final String nodeType) {
		final NodeTypeValidatorDefinition definition = new NodeTypeValidatorDefinition(nodeType);
		super.populate(definition, name);
		return definition;
	}
}
