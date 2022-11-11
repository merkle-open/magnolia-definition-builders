package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;

import javax.inject.Provider;

public abstract class AbstractBaseDatasourceDefinitionBuilder<D extends BaseDatasourceDefinition, B extends AbstractBaseDatasourceDefinitionBuilder<D, B>> {
	private final Provider<D> factory;

	protected AbstractBaseDatasourceDefinitionBuilder(final Provider<D> factory) {
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	protected B self() {
		return (B) this;
	}

	protected D build() {
		return factory.get();
	}
}
