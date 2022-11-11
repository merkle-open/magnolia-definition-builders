package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.RadioButtonGroupFieldDefinition;

public class RadioButtonGroupFieldDefinitionBuilder<T> extends AbstractOptionGroupFieldDefinitionBuilder<T, RadioButtonGroupFieldDefinition<T>, RadioButtonGroupFieldDefinitionBuilder<T>> {

	public RadioButtonGroupFieldDefinitionBuilder() {
		super(RadioButtonGroupFieldDefinition::new);
	}

	public RadioButtonGroupFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		return super.build(name, datasourceDefinition);
	}
}
