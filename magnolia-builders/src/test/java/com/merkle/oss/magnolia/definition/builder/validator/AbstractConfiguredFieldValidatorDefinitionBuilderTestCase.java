package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractConfiguredFieldValidatorDefinitionBuilderTestCase {

	public <D extends ConfiguredFieldValidatorDefinition, B extends AbstractConfiguredFieldValidatorDefinitionBuilder<D, B>> B assertValidator(final B builder, final BiFunction<String, B, D> buildFunction) {
		final D definition = buildFunction.apply(
				"someName",
				builder
						.errorMessage("someErrorMessage")
		);
		assertEquals("someErrorMessage", definition.getErrorMessage());
		return builder;
	}
}
