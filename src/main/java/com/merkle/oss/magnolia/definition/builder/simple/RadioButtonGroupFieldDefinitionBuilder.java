package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.RadioButtonGroupFieldDefinition;

/**
 * builds a {@link RadioButtonGroupFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Radio-button-group-field.html">magnolia Docs - Radio button group field</a>
 * @author Merkle DACH
 */
public class RadioButtonGroupFieldDefinitionBuilder<T> extends AbstractOptionGroupFieldDefinitionBuilder<T, RadioButtonGroupFieldDefinition<T>, RadioButtonGroupFieldDefinitionBuilder<T>> {

	public RadioButtonGroupFieldDefinitionBuilder() {
		super(RadioButtonGroupFieldDefinition::new);
	}

	public RadioButtonGroupFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		return super.build(name, datasourceDefinition);
	}
}
