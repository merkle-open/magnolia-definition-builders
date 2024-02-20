package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.JcrMultiFieldDefinition;

import javax.jcr.Node;

/**
 * builds a {@link JcrMultiFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Multi-field.html">magnolia Docs - Multi field </a>
 * @author Merkle DACH
 */
public class JcrMultiFieldDefinitionBuilder extends AbstractMultiFieldDefinitionBuilder<Node, JcrMultiFieldDefinition, JcrMultiFieldDefinitionBuilder> {

	public JcrMultiFieldDefinitionBuilder() {
		super(JcrMultiFieldDefinition::new);
	}

	public JcrMultiFieldDefinition build(final String name, final EditorPropertyDefinition field) {
		return super.build(name, field);
	}
}
