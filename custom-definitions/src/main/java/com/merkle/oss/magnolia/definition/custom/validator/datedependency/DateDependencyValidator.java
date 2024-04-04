package com.merkle.oss.magnolia.definition.custom.validator.datedependency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class DateDependencyValidator extends AbstractValidator<Temporal> {
	private final DateDependencyValidatorDefinition definition;

	public DateDependencyValidator(
			final DateDependencyValidatorDefinition definition,
			final String errorMessage
	) {
		super(errorMessage);
		this.definition = definition;
	}

	@Override
	public ValidationResult apply(final Temporal value, final ValueContext context) {
		return toResult(value, isValid(value));
	}

	private boolean isValid(final Temporal value) {
		definition.getState().update(definition.getPropertyName(), convert(value));
		return definition.getValidator().test(definition.getState());
	}

	private LocalDateTime convert(final Temporal value) {
		if(value instanceof LocalDate) {
			return ((LocalDate)value).atStartOfDay();
		}
		return LocalDateTime.from(value);
	}
}