package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.RegexpValidatorDefinition;

import javax.annotation.Nullable;
import java.util.Optional;

public class RegexpValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<RegexpValidatorDefinition, RegexpValidatorDefinitionBuilder>{
	@Nullable
	private String pattern;

	public RegexpValidatorDefinitionBuilder() {}
	public RegexpValidatorDefinitionBuilder(final RegexpValidatorDefinition definition) {
		super(definition);
		pattern(definition.getPattern());
	}

	public RegexpValidatorDefinitionBuilder pattern(final String pattern) {
		this.pattern = pattern;
		return self();
	}

	public RegexpValidatorDefinition build() {
		return build("regexpValidator");
	}
	public RegexpValidatorDefinition build(final String name) {
		final RegexpValidatorDefinition definition = new RegexpValidatorDefinition();
		super.populate(definition, name);
		Optional.ofNullable(pattern).ifPresent(definition::setPattern);
		return definition;
	}
}
