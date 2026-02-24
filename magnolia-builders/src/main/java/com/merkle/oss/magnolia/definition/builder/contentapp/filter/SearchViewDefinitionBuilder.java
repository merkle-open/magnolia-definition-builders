package com.merkle.oss.magnolia.definition.builder.contentapp.filter;

import info.magnolia.ui.filteringapp.filter.field.SearchView;
import info.magnolia.ui.filteringapp.filter.field.SearchViewDefinition;

import java.util.Optional;

import jakarta.annotation.Nullable;

public class SearchViewDefinitionBuilder extends AbstractConfiguredFilterViewDefinitionBuilder<SearchView, SearchViewDefinition, SearchViewDefinitionBuilder> {
	@Nullable
	private String placeholder;

	public SearchViewDefinitionBuilder() {}
	public SearchViewDefinitionBuilder(final SearchViewDefinition definition) {
		super(definition);
		Optional.ofNullable(definition.getPlaceholder()).ifPresent(this::placeholder);
	}

	public SearchViewDefinitionBuilder placeholder(final String placeholder) {
		this.placeholder = placeholder;
		return self();
	}

	public SearchViewDefinition build(final String name) {
		final SearchViewDefinition definition = new SearchViewDefinition();
		super.populate(definition, name);
		Optional.ofNullable(placeholder).ifPresent(definition::setPlaceholder);
		return definition;
	}
}
