package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;

import jakarta.annotation.Nullable;
import java.util.Optional;

public abstract class AbstractConfiguredFieldValidatorDefinitionBuilder<D extends ConfiguredFieldValidatorDefinition, B extends AbstractConfiguredFieldValidatorDefinitionBuilder<D, B>> {
	@Nullable
	private String errorMessage;

	protected AbstractConfiguredFieldValidatorDefinitionBuilder() {}
	protected AbstractConfiguredFieldValidatorDefinitionBuilder(final D definition) {
		errorMessage(definition.getErrorMessage());
	}

	public B errorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
		return self();
	}

	@SuppressWarnings("unchecked")
	protected B self() {
		return (B) this;
	}

	protected void populate(final D definition, final String name) {
		definition.setName(name);
		Optional.ofNullable(errorMessage).ifPresent(definition::setErrorMessage);
	}
}
