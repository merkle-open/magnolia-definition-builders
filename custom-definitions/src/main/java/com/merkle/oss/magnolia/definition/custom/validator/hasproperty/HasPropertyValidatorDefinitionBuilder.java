package com.merkle.oss.magnolia.definition.custom.validator.hasproperty;

import java.util.Optional;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.validator.AbstractConfiguredFieldValidatorDefinitionBuilder;

public class HasPropertyValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<HasPropertyValidatorDefinition, HasPropertyValidatorDefinitionBuilder> {
	@Nullable
	private Boolean i18n;

	public HasPropertyValidatorDefinitionBuilder() {}
	public HasPropertyValidatorDefinitionBuilder(final HasPropertyValidatorDefinition definition) {
		super(definition);
		i18n(definition.isI18n());
	}

	public HasPropertyValidatorDefinitionBuilder i18n(final boolean i18n) {
		this.i18n = i18n;
		return self();
	}

	public HasPropertyValidatorDefinition build(final String propertyName) {
		return build("HasPropertyValidator", propertyName);
	}

	public HasPropertyValidatorDefinition build(final String name, final String propertyName) {
		final HasPropertyValidatorDefinition definition = new HasPropertyValidatorDefinition(
			propertyName,
			Optional.ofNullable(i18n).orElse(false)
		);
		super.populate(definition, name);
		return definition;
	}

}
