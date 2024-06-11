package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.AbstractOptionGroupFieldDefinition;
import info.magnolia.ui.field.Layout;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class AbstractOptionGroupFieldDefinitionBuilder<T, D extends AbstractOptionGroupFieldDefinition<T>, B extends AbstractOptionGroupFieldDefinitionBuilder<T, D, B>> extends AbstractSelectFieldDefinitionBuilder<T, DatasourceDefinition, D, B> {
	@Nullable
	private Layout layout;

	protected AbstractOptionGroupFieldDefinitionBuilder() {}
	protected AbstractOptionGroupFieldDefinitionBuilder(final D definition) {
		super(definition);
		layout(definition.getLayout());
	}

	public B layout(final Layout layout) {
		this.layout = layout;
		return self();
	}

	@Override
	protected void populate(final D definition, final String name, final DatasourceDefinition datasourceDefinition) {
		super.populate(definition, name, datasourceDefinition);
		Optional.ofNullable(layout).ifPresent(definition::setLayout);
	}
}
