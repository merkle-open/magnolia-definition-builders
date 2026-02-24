package com.merkle.oss.magnolia.definition.builder.contentapp.filter;

import info.magnolia.ui.filteringapp.filter.field.FilterDropdownMultiselectViewDefinition;
import info.magnolia.ui.filteringapp.filter.field.FilterDropdownView;

public class FilterDropdownMultiselectViewDefinitionBuilder<T> extends AbstractConfiguredFilterViewDefinitionBuilder<FilterDropdownView<T>, FilterDropdownMultiselectViewDefinition<T>, FilterDropdownMultiselectViewDefinitionBuilder<T>> {

	public FilterDropdownMultiselectViewDefinitionBuilder() {}
	public FilterDropdownMultiselectViewDefinitionBuilder(final FilterDropdownMultiselectViewDefinition<T> definition) {
		super(definition);
	}

	public FilterDropdownMultiselectViewDefinition<T> build(final String name) {
		final FilterDropdownMultiselectViewDefinition<T> definition = new FilterDropdownMultiselectViewDefinition<>();
		super.populate(definition, name);
		return definition;
	}
}
