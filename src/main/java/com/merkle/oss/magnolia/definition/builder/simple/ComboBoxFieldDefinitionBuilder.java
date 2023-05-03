package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;

/**
 * builds a {@link ComboBoxFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Combobox-field.html">magnolia Docs - Combobox field </a>
 * @author Merkle DACH
 */
public class ComboBoxFieldDefinitionBuilder<T> extends AbstractComboBoxFieldDefinitionBuilder<T, ComboBoxFieldDefinition<T>, ComboBoxFieldDefinitionBuilder<T>> {

	public ComboBoxFieldDefinitionBuilder() {
		super(ComboBoxFieldDefinition::new);
	}

	public <O extends OptionListDefinitionBuilder.OptionEnum> ComboBoxFieldDefinition<T> build(final String name, final Class<O> optionsClass, final O... excludeOptions) {
		return build(name, new OptionListDefinitionBuilder().options(optionsClass, excludeOptions).build());
	}

	public ComboBoxFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		return super.build(name, datasourceDefinition);
	}
}
