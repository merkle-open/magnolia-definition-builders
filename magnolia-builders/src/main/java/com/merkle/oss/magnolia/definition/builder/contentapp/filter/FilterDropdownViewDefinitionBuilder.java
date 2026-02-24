package com.merkle.oss.magnolia.definition.builder.contentapp.filter;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.filter.FilterOperator;
import info.magnolia.ui.filteringapp.filter.field.FilterDropdownView;
import info.magnolia.ui.filteringapp.filter.field.FilterDropdownViewDefinition;
import info.magnolia.ui.filteringapp.filter.field.SearchView;
import info.magnolia.ui.filteringapp.filter.field.SearchViewDefinition;

import java.util.Optional;

import jakarta.annotation.Nullable;

public class FilterDropdownViewDefinitionBuilder<T> extends AbstractConfiguredFilterViewDefinitionBuilder<FilterDropdownView<T>, FilterDropdownViewDefinition<T>, FilterDropdownViewDefinitionBuilder<T>> {
	@Nullable
	private DatasourceDefinition datasource;
	@Nullable
	private FilterOperator filterOperator;
	@Nullable
	private Boolean textInputAllowed;
	@Nullable
	private Integer pageLength;
	@Nullable
	private String popupWidth;

	public FilterDropdownViewDefinitionBuilder() {}
	public FilterDropdownViewDefinitionBuilder(final FilterDropdownViewDefinition<T> definition) {
		super(definition);
		Optional.ofNullable(definition.getDatasource()).ifPresent(this::datasource);
		Optional.ofNullable(definition.getFilterOperator()).ifPresent(this::filterOperator);
		textInputAllowed(definition.isTextInputAllowed());
		pageLength(definition.getPageLength());
		Optional.ofNullable(definition.getPopupWidth()).ifPresent(this::popupWidth);
	}

	public FilterDropdownViewDefinitionBuilder<T> datasource(final DatasourceDefinition datasource) {
		this.datasource = datasource;
		return self();
	}

	public FilterDropdownViewDefinitionBuilder<T> filterOperator(final FilterOperator filterOperator) {
		this.filterOperator = filterOperator;
		return self();
	}

	public FilterDropdownViewDefinitionBuilder<T> textInputAllowed(final boolean textInputAllowed) {
		this.textInputAllowed = textInputAllowed;
		return self();
	}

	public FilterDropdownViewDefinitionBuilder<T> pageLength(final int pageLength) {
		this.pageLength = pageLength;
		return self();
	}

	public FilterDropdownViewDefinitionBuilder<T> popupWidth(final String popupWidth) {
		this.popupWidth = popupWidth;
		return self();
	}

	public FilterDropdownViewDefinition<T> build(final String name) {
		final FilterDropdownViewDefinition<T> definition = new FilterDropdownViewDefinition<>();
		super.populate(definition, name);
		Optional.ofNullable(datasource).ifPresent(definition::setDatasource);
		Optional.ofNullable(filterOperator).ifPresent(definition::setFilterOperator);
		Optional.ofNullable(textInputAllowed).ifPresent(definition::setTextInputAllowed);
		Optional.ofNullable(pageLength).ifPresent(definition::setPageLength);
		Optional.ofNullable(popupWidth).ifPresent(definition::setPopupWidth);
		return definition;
	}
}
