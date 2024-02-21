package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;

import javax.annotation.Nullable;
import javax.inject.Provider;
import java.util.Optional;

public abstract class AbstractConfiguredFieldValidatorDefinitionBuilder<D extends ConfiguredFieldValidatorDefinition, B extends AbstractConfiguredFieldValidatorDefinitionBuilder<D, B>> {
	private final Provider<D> factory;

	@Nullable
	private String errorMessage;

	protected AbstractConfiguredFieldValidatorDefinitionBuilder(final Provider<D> factory) {
		this.factory = factory;
	}

	public B errorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
		return self();
	}

	@SuppressWarnings("unchecked")
	protected B self() {
		return (B) this;
	}

	protected D build(final String name) {
		final D definition = factory.get();
		definition.setName(name);
		Optional.ofNullable(errorMessage).ifPresent(definition::setErrorMessage);
		return definition;
	}
}
