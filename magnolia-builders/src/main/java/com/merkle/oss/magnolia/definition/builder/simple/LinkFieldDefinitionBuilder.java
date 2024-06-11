package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

/**
 * builds a {@link LinkFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Link-field.html">magnolia Docs - Link field </a>
 * @author Merkle DACH
 */
public class LinkFieldDefinitionBuilder<T> extends AbstractLinkFieldDefinitionBuilder<T, LinkFieldDefinition<T>, LinkFieldDefinitionBuilder<T>> {

	public LinkFieldDefinitionBuilder() {}
	public LinkFieldDefinitionBuilder(final LinkFieldDefinition<T> definition) {
		super(definition);
	}

	public LinkFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		final LinkFieldDefinition<T> definition = new LinkFieldDefinition<>();
		super.populate(definition, name, datasourceDefinition);
		return definition;
	}
}
