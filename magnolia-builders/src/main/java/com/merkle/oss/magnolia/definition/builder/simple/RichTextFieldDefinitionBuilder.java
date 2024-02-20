package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.RichTextFieldDefinition;

/**
 * builds a {@link RichTextFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Rich-text-field.html">magnolia Docs - Rich text field </a>
 * @author Merkle DACH
 */
public class RichTextFieldDefinitionBuilder extends AbstractRichTextFieldDefinitionBuilder<RichTextFieldDefinition, RichTextFieldDefinitionBuilder> {

	public RichTextFieldDefinitionBuilder() {
		super(RichTextFieldDefinition::new);
	}

	public RichTextFieldDefinition build(final String name) {
		return super.build(name);
	}
}
