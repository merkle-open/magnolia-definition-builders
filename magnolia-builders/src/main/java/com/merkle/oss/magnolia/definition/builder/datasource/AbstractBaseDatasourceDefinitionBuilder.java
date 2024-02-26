package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;

import javax.inject.Provider;

public abstract class AbstractBaseDatasourceDefinitionBuilder<D extends BaseDatasourceDefinition, B extends AbstractBaseDatasourceDefinitionBuilder<D, B>> {

	@SuppressWarnings("unchecked")
	protected B self() {
		return (B) this;
	}

	protected void populate(final D definition) {}
}
