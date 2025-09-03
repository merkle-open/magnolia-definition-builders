package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.Layout;
import info.magnolia.ui.field.NoopNameDecorator;
import info.magnolia.ui.field.PrefixNameDecorator;
import info.magnolia.ui.field.WithPropertyNameDecorator.PropertyNameDecorator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;
import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.datasource.OptionBuilder;
import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;
import com.merkle.oss.magnolia.definition.builder.simple.RadioButtonGroupFieldDefinitionBuilder;

public abstract class AbstractSwitchableDefinitionBuilder<T extends OptionEnum, B extends AbstractSwitchableDefinitionBuilder<T, B>> extends AbstractConfiguredComplexPropertyDefinitionBuilder<Node, SwitchableDefinition, B> {
	private final String labelPrefix;
	private final boolean fieldI18n;

	@Nullable
	private Class<? extends PropertyNameDecorator> propertyNameDecorator;
	@Nullable
	private PropertyNameDecorator optionPropertyNameDecorator;
	@Nullable
	private T selected;
	@Nullable
	private List<FieldOption<T>> fieldOptions;
	@Nullable
	private Boolean readOnly;
	@Nullable
	private Boolean required;
	@Nullable
	private List<FieldValidatorDefinition> allTypeValidators;
	@Nullable
	private Map<T, List<FieldValidatorDefinition>> typeValidatorsMapping;
	@Nullable
	private Map<T, List<EditorPropertyDefinition>> additionalPropertiesMapping;

	protected AbstractSwitchableDefinitionBuilder(final String labelPrefix, final boolean fieldI18n) {
		this.labelPrefix = labelPrefix;
		this.fieldI18n = fieldI18n;
	}

	public B propertyNameDecorator(@Nullable final Class<? extends PropertyNameDecorator> propertyNameDecorator) {
		this.propertyNameDecorator = propertyNameDecorator;
		return self();
	}

	public B optionPropertyNameDecorator(@Nullable final PropertyNameDecorator optionPropertyNameDecorator) {
		this.optionPropertyNameDecorator = optionPropertyNameDecorator;
		return self();
	}

	public B withSelected(@Nullable final T selected) {
		this.selected = selected;
		return self();
	}

	public B fieldOption(final FieldOption<T> fieldOption) {
		return fieldOptions(Stream.concat(
				Stream.ofNullable(fieldOptions).flatMap(Collection::stream),
				Stream.of(fieldOption)
		).collect(Collectors.toList()));
	}

	public B fieldOptions(final List<FieldOption<T>> fieldOptions) {
		this.fieldOptions = fieldOptions;
		return self();
	}

	public B readOnly() {
		return readOnly(true);
	}

	public B readOnly(final boolean readOnly) {
		this.readOnly = readOnly;
		return self();
	}

	public B required() {
		return required(true);
	}

	public B required(final boolean required) {
		this.required = required;
		return self();
	}

	public B validator(final FieldValidatorDefinition validator) {
		return validators(Stream.concat(
				Stream.ofNullable(allTypeValidators).flatMap(Collection::stream),
				Stream.of(validator)
		).collect(Collectors.toList()));
	}
	public B validators(final List<FieldValidatorDefinition> validators) {
		this.allTypeValidators = validators;
		return self();
	}
	public B validator(final T type, final FieldValidatorDefinition validator) {
		return validators(type, Stream.concat(
				Optional.ofNullable(typeValidatorsMapping).map(mapping -> mapping.get(type)).stream().flatMap(Collection::stream),
				Stream.of(validator)
		).collect(Collectors.toList()));
	}
	public B validators(final T type, final List<FieldValidatorDefinition> validators) {
		return validatorsMapping(Stream.concat(
				Stream.ofNullable(this.typeValidatorsMapping).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(type, validators))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}
	public B validatorsMapping(final Map<T, List<FieldValidatorDefinition>> validators) {
		this.typeValidatorsMapping = validators;
		return self();
	}

	public B additionalProperty(final T type, final EditorPropertyDefinition additionalProperty){
		return additionalProperties(type, Stream.concat(
				Optional.ofNullable(additionalPropertiesMapping).map(mapping -> mapping.get(type)).stream().flatMap(Collection::stream),
				Stream.of(additionalProperty)
		).collect(Collectors.toList()));
	}
	public B additionalProperties(final T type, final List<EditorPropertyDefinition> additionalProperties){
		return additionalPropertiesMapping(Stream.concat(
				Stream.ofNullable(additionalPropertiesMapping).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(type, additionalProperties))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}
	public B additionalPropertiesMapping(final Map<T, List<EditorPropertyDefinition>> additionalPropertiesMapping){
		this.additionalPropertiesMapping = additionalPropertiesMapping;
		return self();
	}

	public SwitchableDefinition build(final String name) {
		final SwitchableDefinition definition =new SwitchableDefinition(
				propertyNameDecorator != null ? propertyNameDecorator : PrefixNameDecorator.class,
				new RadioButtonGroupFieldDefinitionBuilder<Option>()
						.defaultValue(Optional.ofNullable(fieldOptions).flatMap(options -> getDefault(options, selected)).map(OptionEnum::getValue).orElse(null))
						.layout(Layout.horizontal)
						.build(
								Optional.ofNullable(optionPropertyNameDecorator).orElseGet(NoopNameDecorator::new).apply(name),
								new OptionListDefinitionBuilder().options(
										fieldOptions.stream().map(option -> new OptionBuilder()
												.label(labelPrefix + option.getType().getLabel())
												.build(
														option.getField().getName(),
														option.getType().getValue()
												)
										).collect(Collectors.toList())
								).build()
						),
				Optional.ofNullable(fieldOptions).stream()
						.flatMap(Collection::stream)
						.peek(this::applyAdditionalProperties)
						.map(FieldOption::getField)
						.collect(Collectors.toList()),
				fieldI18n
		);
		super.populate(definition, name);
		Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
		Optional.ofNullable(required).ifPresent(definition::setRequired);
		Optional.ofNullable(allTypeValidators).ifPresent(definition::setValidators);
		Optional.ofNullable(typeValidatorsMapping).map(Map::entrySet).stream().flatMap(Collection::stream).forEach(entry ->
				definition.setValidators(
						entry.getKey().getValue(),
						Stream.concat(
								Stream.ofNullable(allTypeValidators).flatMap(Collection::stream),
								entry.getValue().stream()
						).collect(Collectors.toList())
				)
		);
		return definition;
	}

	private void applyAdditionalProperties(final FieldOption<T> option) {
		Optional.ofNullable(additionalPropertiesMapping).map(mapping -> mapping.get(option.getType())).ifPresent(additionalProperties ->
				option.getField().setAdditionalProperties(additionalProperties)
		);
	}

	private Optional<T> getDefault(final List<FieldOption<T>> fieldOptions, @Nullable final T selected) {
		final Set<T> options = fieldOptions.stream().map(FieldOption::getType).collect(Collectors.toSet());
		if (options.size() == 1) {
			return options.stream().findFirst();
		}
		return Optional.ofNullable(selected).filter(options::contains);
	}
}
