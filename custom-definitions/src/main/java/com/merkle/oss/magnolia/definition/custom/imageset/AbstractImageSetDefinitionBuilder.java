package com.merkle.oss.magnolia.definition.custom.imageset;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.TextFieldDefinition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;
import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinitionBuilder;

public abstract class AbstractImageSetDefinitionBuilder<B extends AbstractImageSetDefinitionBuilder<B>> extends AbstractConfiguredComplexPropertyDefinitionBuilder<Node, ImageSetDefinition, B> {
	private static final String SELECTION = "selection";
	private static final String ALT_TEXT = "alt";
	public static final UnaryOperator<String> SELECTION_PROPERTY_NAME_PROVIDER = name -> name + "_" + SELECTION;
	public static final UnaryOperator<String> ALT_TEXT_PROPERTY_NAME_PROVIDER = name -> name + "_" + ALT_TEXT;
	private final String labelPrefix;
    private final boolean imageFieldI18n;
	private final boolean removePreviouslySelected;

    @Nullable
	private List<ImageType> imageOptions;
	@Nullable
	private Boolean readOnly;
	@Nullable
	private Boolean required;
	@Nullable
	private List<FieldValidatorDefinition> allTypeValidators;
	@Nullable
	private Map<ImageType, List<FieldValidatorDefinition>> typeValidatorsMapping;
	@Nullable
	private Map<ImageType, List<EditorPropertyDefinition>> additionalPropertiesMapping;

	protected AbstractImageSetDefinitionBuilder(final String labelPrefix, final boolean imageFieldI18n, final boolean removePreviouslySelected) {
		this.labelPrefix = labelPrefix;
        this.imageFieldI18n = imageFieldI18n;
		this.removePreviouslySelected = removePreviouslySelected;
        imageOptions(Arrays.stream(ImageTypes.values()).collect(Collectors.toList()));
	}

	protected abstract FieldOption<ImageType> createFieldOption(ImageType imageType);

	public B imageOption(final ImageType imageOption) {
		return imageOptions(Stream.concat(
				Stream.ofNullable(imageOptions).flatMap(Collection::stream),
				Stream.of(imageOption)
		).collect(Collectors.toList()));
	}

	public B imageOptions(final ImageType... imageOptions) {
		return imageOptions(List.of(imageOptions));
	}

	public B imageOptions(final List<ImageType> imageOptions) {
		this.imageOptions = imageOptions;
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
	public B validator(final ImageType type, final FieldValidatorDefinition validator) {
		return validators(type, Stream.concat(
				Optional.ofNullable(typeValidatorsMapping).map(mapping -> mapping.get(type)).stream().flatMap(Collection::stream),
				Stream.of(validator)
		).collect(Collectors.toList()));
	}
	public B validators(final ImageType type, final List<FieldValidatorDefinition> validators) {
		return validatorsMapping(Stream.concat(
				Stream.ofNullable(this.typeValidatorsMapping).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(type, validators))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}
	public B validatorsMapping(final Map<ImageType, List<FieldValidatorDefinition>> validators) {
		this.typeValidatorsMapping = validators;
		return self();
	}

	public B additionalProperty(final ImageType type, final EditorPropertyDefinition additionalProperty){
		return additionalProperties(type, Stream.concat(
				Optional.ofNullable(additionalPropertiesMapping).map(mapping -> mapping.get(type)).stream().flatMap(Collection::stream),
				Stream.of(additionalProperty)
		).collect(Collectors.toList()));
	}
	public B additionalProperties(final ImageType type, final List<EditorPropertyDefinition> additionalProperties){
		return additionalPropertiesMapping(Stream.concat(
				Stream.ofNullable(additionalPropertiesMapping).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(type, additionalProperties))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}
	public B additionalPropertiesMapping(final Map<ImageType, List<EditorPropertyDefinition>> additionalPropertiesMapping){
		this.additionalPropertiesMapping = additionalPropertiesMapping;
		return self();
	}

	public ImageSetDefinition build(final String name) {
		final ImageSetDefinition definition = new ImageSetDefinition(
				new SwitchableDefinitionBuilder<ImageType>(labelPrefix)
						.additionalPropertiesMapping(additionalPropertiesMapping)
						.propertyNameDecorator(PrefixExceptImageFieldPropertyNameDecorator.class)
						.optionPropertyNameDecorator(SELECTION_PROPERTY_NAME_PROVIDER::apply)
						.fieldOptions(Stream.ofNullable(imageOptions).flatMap(Collection::stream).map(this::createFieldOption).collect(Collectors.toList()))
						.label(labelPrefix + "image.label")
						.removePreviouslySelected(removePreviouslySelected)
						.build(name),
				createAltTextField(name).orElse(null),
				imageFieldI18n
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

    protected Optional<TextFieldDefinition> createAltTextField(final String name) {
        return Optional.of(new TextFieldDefinitionBuilder()
                .label(labelPrefix + "altText.label")
                .build(ALT_TEXT_PROPERTY_NAME_PROVIDER.apply(name)));
    }
}
