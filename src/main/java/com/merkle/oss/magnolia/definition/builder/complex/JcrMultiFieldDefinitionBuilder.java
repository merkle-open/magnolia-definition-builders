package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.JcrMultiFieldDefinition;

import javax.jcr.Node;

public class JcrMultiFieldDefinitionBuilder extends AbstractMultiFieldDefinitionBuilder<Node, JcrMultiFieldDefinition, JcrMultiFieldDefinitionBuilder> {

	public JcrMultiFieldDefinitionBuilder() {
		super(JcrMultiFieldDefinition::new);
	}

	public JcrMultiFieldDefinition build(final String name, final EditorPropertyDefinition field) {
		return super.build(name, field);
	}
}
