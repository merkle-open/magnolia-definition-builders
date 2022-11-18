package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.StaticFieldViewDefinition;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * builds a {@link StaticFieldViewDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Static-field.html">magnolia Docs - Static field </a>
 * @author Merkle DACH
 */
public class StaticFieldViewDefinitionBuilder<T> extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, StaticFieldViewDefinition<T>, StaticFieldViewDefinitionBuilder<T>> {
	@Nullable
	private String value;

	public StaticFieldViewDefinitionBuilder() {
		super(StaticFieldViewDefinition::new);
	}

	public StaticFieldViewDefinitionBuilder<T> value(final String value) {
		this.value = value;
		return self();
	}

	public StaticFieldViewDefinition<T> build(final String name) {
		final StaticFieldViewDefinition<T> definition = super.build(name);
		Optional.ofNullable(value).ifPresent(definition::setValue);
		return definition;
	}
}
