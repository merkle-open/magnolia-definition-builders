package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

public class LinkFieldDefinitionBuilder<T> extends AbstractComboBoxFieldDefinitionBuilder<T, LinkFieldDefinition<T>, LinkFieldDefinitionBuilder<T>> {

	public LinkFieldDefinitionBuilder() {
		super(LinkFieldDefinition::new);
	}

	public LinkFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		return super.build(name, datasourceDefinition);
	}
}
