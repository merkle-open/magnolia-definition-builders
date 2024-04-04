package com.merkle.oss.magnolia.definition.custom.validator.datedependency;

import info.magnolia.i18nsystem.I18nable;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;
import info.magnolia.ui.field.ValidatorType;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;

@I18nable(keyGenerator = FieldValidatorDefinitionKeyGenerator.class)
@ValidatorType("dateDependency")
public class DateDependencyValidatorDefinition extends ConfiguredFieldValidatorDefinition {
	private final State state;
	private final String propertyName;
	private final Predicate<State> validator;

	DateDependencyValidatorDefinition(final State state, final String propertyName, final boolean i18n, final Predicate<State> validator) {
		this.state = state;
		this.propertyName = propertyName;
		this.validator = validator;
		state.propertyNameI18nMapping.put(propertyName, i18n);
	}

	@Override
	public Class<? extends FieldValidatorFactory<Temporal>> getFactoryClass() {
		return DateDependencyValidatorFactory.class;
	}

	public State getState() {
		return state;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Predicate<State> getValidator() {
		return validator;
	}

	public static class State {
		private final Map<String, Boolean> propertyNameI18nMapping = new HashMap<>();
		private final Map<String, LocalDateTime> dates = new HashMap<>();

		void update(final String propertyName, @Nullable final LocalDateTime LocalDateTime) {
			dates.put(propertyName, LocalDateTime);
		}

		Map<String, Boolean> getPropertyNameI18nMapping() {
			return propertyNameI18nMapping;
		}

		public Optional<LocalDateTime> get(final String propertyName) {
			return Optional.ofNullable(dates.get(propertyName));
		}
	}
}