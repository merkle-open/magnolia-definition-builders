package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.AbstractMultiselectFieldDefinition;

import java.util.Set;

public abstract class AbstractMultiselectFieldDefinitionBuilder<T, S extends DatasourceDefinition, D extends AbstractMultiselectFieldDefinition<T, S> , B extends AbstractMultiselectFieldDefinitionBuilder<T, S, D, B>> extends AbstractSelectFieldDefinitionBuilder<Set<T>, S, D, B> {

	@Override
	protected void populate(final D definition, final String name, final S datasourceDefinition) {
		super.populate(definition, name);
		definition.setDatasource(datasourceDefinition);
	}
}
