package com.merkle.oss.magnolia.definition.builder.simple;

import com.vaadin.data.Converter;
import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;

import javax.annotation.Nullable;
import javax.inject.Provider;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractConfiguredFieldDefinitionBuilder<T, D extends ConfiguredFieldDefinition<T>, B extends AbstractConfiguredFieldDefinitionBuilder<T, D, B>> {
	private final Provider<D> factory;

	@Nullable
	private String label;
	@Nullable
	private Boolean i18n;
	@Nullable
	private String description;
	@Nullable
	private Boolean required;
	@Nullable
	private String requiredErrorMessage;
	@Nullable
	private String conversionErrorMessage;
	@Nullable
	private Boolean readOnly;
	@Nullable
	private Object defaultValue;
	@Nullable
	private String styleName;
	@Nullable
	private Class<? extends Converter<T, ?>> converterClass;
	@Nullable
	private List<FieldValidatorDefinition> validators;

	protected AbstractConfiguredFieldDefinitionBuilder(final Provider<D> factory) {
		this.factory = factory;
	}

	public B label(final String label) {
		this.label = label;
		return self();
	}

	public B i18n() {
		return i18n(true);
	}

	public B i18n(final boolean i18n) {
		this.i18n = i18n;
		return self();
	}

	public B description(final String description) {
		this.description = description;
		return self();
	}

	public B required() {
		return required(true);
	}

	public B required(final boolean required) {
		this.required = required;
		return self();
	}

	public B requiredErrorMessage(final String requiredErrorMessage) {
		this.requiredErrorMessage = requiredErrorMessage;
		return self();
	}

	public B conversionErrorMessage(final String conversionErrorMessage) {
		this.conversionErrorMessage = conversionErrorMessage;
		return self();
	}

	public B readOnly() {
		return readOnly(true);
	}

	public B readOnly(final boolean readOnly) {
		this.readOnly = readOnly;
		return self();
	}

	public B defaultValue(final Object defaultValue) {
		this.defaultValue = defaultValue;
		return self();
	}

	public B styleName(final String styleName) {
		this.styleName = styleName;
		return self();
	}

	public B converterClass(final Class<? extends Converter<T, ?>> converterClass) {
		this.converterClass = converterClass;
		return self();
	}

	public B validator(final FieldValidatorDefinition validator) {
		return validators(Stream.concat(
				Stream.ofNullable(validators).flatMap(Collection::stream),
				Stream.of(validator)
		).collect(Collectors.toList()));
	}

	public B validators(final List<FieldValidatorDefinition> validators) {
		this.validators = validators;
		return self();
	}

	@SuppressWarnings("unchecked")
	protected B self() {
		return (B) this;
	}

	protected D build(final String name) {
		final D definition = factory.get();
		definition.setName(name);
		Optional.ofNullable(label).ifPresent(definition::setLabel);
		Optional.ofNullable(i18n).ifPresent(definition::setI18n);
		Optional.ofNullable(description).ifPresent(definition::setDescription);
		Optional.ofNullable(required).ifPresent(definition::setRequired);
		Optional.ofNullable(requiredErrorMessage).ifPresent(definition::setRequiredErrorMessage);
		Optional.ofNullable(conversionErrorMessage).ifPresent(definition::setConversionErrorMessage);
		Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
		Optional.ofNullable(defaultValue).ifPresent(definition::setDefaultValue);
		Optional.ofNullable(styleName).ifPresent(definition::setStyleName);
		Optional.ofNullable(converterClass).ifPresent(definition::setConverterClass);
		Stream.ofNullable(validators).flatMap(Collection::stream).forEach(definition.getValidators()::add);
		return definition;
	}
}
