package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;

public abstract class AbstractBaseDatasourceDefinitionBuilder<D extends BaseDatasourceDefinition, B extends AbstractBaseDatasourceDefinitionBuilder<D, B>> {

	protected AbstractBaseDatasourceDefinitionBuilder() {}
	protected AbstractBaseDatasourceDefinitionBuilder(final D definition) {}

	@SuppressWarnings("unchecked")
	protected B self() {
		return (B) this;
	}

	protected void populate(final D definition) {}
}
