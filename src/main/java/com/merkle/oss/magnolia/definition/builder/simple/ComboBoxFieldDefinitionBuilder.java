package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;

public class ComboBoxFieldDefinitionBuilder<T> extends AbstractComboBoxFieldDefinitionBuilder<T, ComboBoxFieldDefinition<T>, ComboBoxFieldDefinitionBuilder<T>> {

	public ComboBoxFieldDefinitionBuilder() {
		super(ComboBoxFieldDefinition::new);
	}

	public ComboBoxFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		return super.build(name, datasourceDefinition);
	}
}
