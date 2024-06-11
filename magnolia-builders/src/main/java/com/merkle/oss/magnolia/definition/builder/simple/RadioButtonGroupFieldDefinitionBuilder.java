package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.RadioButtonGroupFieldDefinition;

/**
 * builds a {@link RadioButtonGroupFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Radio-button-group-field.html">magnolia Docs - Radio button group field</a>
 * @author Merkle DACH
 */
public class RadioButtonGroupFieldDefinitionBuilder<T> extends AbstractOptionGroupFieldDefinitionBuilder<T, RadioButtonGroupFieldDefinition<T>, RadioButtonGroupFieldDefinitionBuilder<T>> {

	public RadioButtonGroupFieldDefinitionBuilder() {}
	public RadioButtonGroupFieldDefinitionBuilder(final RadioButtonGroupFieldDefinition<T> definition) {
		super(definition);
	}

	public <O extends OptionEnum> RadioButtonGroupFieldDefinition<T> build(final String name, final Class<O> optionsClass, final O... excludeOptions) {
		return build(name, new OptionListDefinitionBuilder().options(optionsClass, excludeOptions).build());
	}

	public RadioButtonGroupFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		final RadioButtonGroupFieldDefinition<T> definition = new RadioButtonGroupFieldDefinition<>();
		super.populate(definition, name, datasourceDefinition);
		return definition;
	}
}
