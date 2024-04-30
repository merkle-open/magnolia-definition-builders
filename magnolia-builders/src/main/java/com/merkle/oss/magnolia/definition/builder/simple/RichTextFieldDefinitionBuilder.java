package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.dam.app.field.DamRichTextFieldDefinition;
import info.magnolia.ui.field.RichTextFieldDefinition;

/**
 * builds a {@link RichTextFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Rich-text-field.html">magnolia Docs - Rich text field </a>
 * @author Merkle DACH
 */
public class RichTextFieldDefinitionBuilder extends AbstractRichTextFieldDefinitionBuilder<DamRichTextFieldDefinition, RichTextFieldDefinitionBuilder> {

	public DamRichTextFieldDefinition build(final String name) {
		final DamRichTextFieldDefinition definition = new DamRichTextFieldDefinition();
		super.populate(definition, name);
		return definition;
	}
}
