package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.CheckBoxGroupFieldDefinition;

import java.util.Set;

/**
 * builds a {@link CheckBoxGroupFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Checkbox-group-field.html">magnolia Docs - Checkbox group field</a>
 * @author Merkle DACH
 */
public class CheckBoxGroupFieldDefinitionBuilder<T> extends AbstractOptionGroupFieldDefinitionBuilder<Set<T>, CheckBoxGroupFieldDefinition<T>, CheckBoxGroupFieldDefinitionBuilder<T>> {

	public <O extends OptionEnum> CheckBoxGroupFieldDefinition<T> build(final String name, final Class<O> optionsClass, final O... excludeOptions) {
		return build(name, new OptionListDefinitionBuilder().options(optionsClass, excludeOptions).build());
	}

	public CheckBoxGroupFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		final CheckBoxGroupFieldDefinition<T> definition = new CheckBoxGroupFieldDefinition<>();
		super.populate(definition, name, datasourceDefinition);
		return definition;
	}
}
