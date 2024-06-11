package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.contentapp.FilteringMode;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.AbstractSelectFieldDefinition;

import javax.annotation.Nullable;
import javax.inject.Provider;
import java.util.Optional;

public abstract class AbstractSelectFieldDefinitionBuilder<T, S extends DatasourceDefinition, D extends AbstractSelectFieldDefinition<T, S>, B extends AbstractSelectFieldDefinitionBuilder<T, S, D, B>> extends AbstractConfiguredFieldDefinitionBuilder<T, D, B> {
	@Nullable
	private FilteringMode filteringMode;

	protected AbstractSelectFieldDefinitionBuilder() {}
	protected AbstractSelectFieldDefinitionBuilder(final D definition) {
		super(definition);
		filteringMode(definition.getFilteringMode());
	}

	public B filteringMode(final FilteringMode filteringMode) {
		this.filteringMode = filteringMode;
		return self();
	}

	protected void populate(final D definition, final String name, final S datasourceDefinition) {
		super.populate(definition, name);
		definition.setDatasource(datasourceDefinition);
		Optional.ofNullable(filteringMode).ifPresent(definition::setFilteringMode);
	}
}
