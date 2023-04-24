package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.CheckBoxGroupFieldDefinition;

import java.util.Set;

/**
 * builds a {@link CheckBoxGroupFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Checkbox-group-field.html">magnolia Docs - Checkbox group field</a>
 * @author Merkle DACH
 */
public class CheckBoxGroupFieldDefinitionBuilder<T> extends AbstractOptionGroupFieldDefinitionBuilder<Set<T>, CheckBoxGroupFieldDefinition<T>, CheckBoxGroupFieldDefinitionBuilder<T>> {
	public CheckBoxGroupFieldDefinitionBuilder() {
		super(CheckBoxGroupFieldDefinition::new);
	}

	public CheckBoxGroupFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		return super.build(name, datasourceDefinition);
	}
}
