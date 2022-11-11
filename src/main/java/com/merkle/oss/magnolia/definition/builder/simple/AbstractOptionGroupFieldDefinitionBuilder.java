package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.AbstractOptionGroupFieldDefinition;
import info.magnolia.ui.field.Layout;

import javax.annotation.Nullable;
import javax.inject.Provider;
import java.util.Optional;

public abstract class AbstractOptionGroupFieldDefinitionBuilder<T, D extends AbstractOptionGroupFieldDefinition<T>, B extends AbstractOptionGroupFieldDefinitionBuilder<T, D, B>> extends AbstractSelectFieldDefinitionBuilder<T, DatasourceDefinition, D, B> {
	@Nullable
	private Layout layout;

	protected AbstractOptionGroupFieldDefinitionBuilder(final Provider<D> factory) {
		super(factory);
	}

	public B layout(final Layout layout) {
		this.layout = layout;
		return self();
	}

	protected D build(final String name, final DatasourceDefinition datasourceDefinition) {
		final D definition = super.build(name, datasourceDefinition);
		Optional.ofNullable(layout).ifPresent(definition::setLayout);
		return definition;
	}
}
