package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.HiddenFieldDefinition;

/**
 * builds a {@link HiddenFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Hidden-field.html">magnolia Docs - Hidden field </a>
 * @author Merkle DACH
 */
public class HiddenFieldDefinitionBuilder<T> extends AbstractConfiguredFieldDefinitionBuilder<T, HiddenFieldDefinition<T>, HiddenFieldDefinitionBuilder<T>> {

	public HiddenFieldDefinitionBuilder() {}
	public HiddenFieldDefinitionBuilder(final HiddenFieldDefinition<T> definition) {
		super(definition);
	}

	public HiddenFieldDefinition<T> build(final String name, final Class<T> type) {
		final HiddenFieldDefinition<T> definition = new HiddenFieldDefinition<>();
		definition.setType(type);
		super.populate(definition, name);
		return definition;
	}
}
