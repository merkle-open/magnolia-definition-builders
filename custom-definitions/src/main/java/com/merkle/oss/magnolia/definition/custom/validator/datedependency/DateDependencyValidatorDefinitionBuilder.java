package com.merkle.oss.magnolia.definition.custom.validator.datedependency;

import info.magnolia.ui.field.DateFieldDefinition;

import java.util.function.Predicate;

import com.merkle.oss.magnolia.definition.builder.validator.AbstractConfiguredFieldValidatorDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.validator.datedependency.DateDependencyValidatorDefinition.State;

public class DateDependencyValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<DateDependencyValidatorDefinition, DateDependencyValidatorDefinitionBuilder> {
	private final Predicate<State> validator;
	private final State state = new State();

	public DateDependencyValidatorDefinitionBuilder(final Predicate<State> validator) {
		this.validator = validator;
	}

	public DateDependencyValidatorDefinition build(final String propertyName) {
		return build(propertyName, false);
	}
	public DateDependencyValidatorDefinition build(final String propertyName, final boolean i18n) {
		return build("dateDependencyValidator", propertyName, i18n);
	}
	public DateDependencyValidatorDefinition build(final String name, final String propertyName) {
		return build(name, propertyName, false);
	}
	public DateDependencyValidatorDefinition build(final String name, final String propertyName, final boolean i18n) {
		final DateDependencyValidatorDefinition definition = new DateDependencyValidatorDefinition(state, propertyName, i18n, validator);
		super.populate(definition, name);
		return definition;
	}

	public DateFieldDefinition buildAndAdd(final DateFieldDefinition dateField) {
		final DateDependencyValidatorDefinition validator = build(dateField.getName(), dateField.isI18n());
		dateField.getValidators().add(validator);
		return dateField;
	}
}
