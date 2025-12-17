package com.merkle.oss.magnolia.definition.custom.videoset;

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

import jakarta.annotation.Nullable;
import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.AbstractImageSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinitionBuilder;

public abstract class AbstractVideoSetDefinitionBuilder<B extends AbstractVideoSetDefinitionBuilder<B>> extends AbstractConfiguredComplexPropertyDefinitionBuilder<Node, VideoSetDefinition, B> {
	private static final String SELECTION = "selection";
	private static final String ALT_TEXT = "alt";
	private static final String PREVIEW_IMAGE = "previewImage";
	public static final UnaryOperator<String> SELECTION_PROPERTY_NAME_PROVIDER = name -> name + "_" + SELECTION;
	public static final UnaryOperator<String> ALT_TEXT_PROPERTY_NAME_PROVIDER = name -> name + "_" + ALT_TEXT;
	public static final UnaryOperator<String> PREVIEW_IMAGE_PROPERTY_NAME_PROVIDER = name -> name + "_" + PREVIEW_IMAGE;

	private final AbstractImageSetDefinitionBuilder<?> imageSetDefinitionBuilder;
    private final boolean videoFieldI18n;
    private final boolean previewImageRequired;
    private final String labelPrefix;
	private final boolean removePreviouslySelected;

	@Nullable
	private List<VideoType> videoOptions;
	@Nullable
	private Boolean readOnly;
	@Nullable
	private Boolean required;
	@Nullable
	private List<FieldValidatorDefinition> allTypeValidators;
	@Nullable
	private Map<ImageType, List<FieldValidatorDefinition>> typeValidatorsMapping;
	@Nullable
	private Map<VideoType, List<EditorPropertyDefinition>> additionalPropertiesMapping;

	protected AbstractVideoSetDefinitionBuilder(
			final AbstractImageSetDefinitionBuilder<?> imageSetDefinitionBuilder,
			final String labelPrefix,
			final boolean videoFieldI18n,
			final boolean previewImageRequired,
			final boolean removePreviouslySelected
	) {
		this.labelPrefix = labelPrefix;
		this.imageSetDefinitionBuilder = imageSetDefinitionBuilder;
        this.videoFieldI18n = videoFieldI18n;
        this.previewImageRequired = previewImageRequired;
		this.removePreviouslySelected = removePreviouslySelected;
        videoOptions(Arrays.stream(VideoTypes.values()).collect(Collectors.toList()));
	}

	protected abstract FieldOption<VideoType> createFieldOption(VideoType videoType);

	public B videoOption(final VideoType videoOption) {
		return videoOptions(Stream.concat(
				Stream.ofNullable(videoOptions).flatMap(Collection::stream),
				Stream.of(videoOption)
		).collect(Collectors.toList()));
	}

	public B videoOptions(final VideoType... videoOptions) {
		return videoOptions(List.of(videoOptions));
	}

	public B videoOptions(final List<VideoType> videoOptions) {
		this.videoOptions = videoOptions;
		return self();
	}

	public B previewImageOption(final ImageType imageOption) {
		imageSetDefinitionBuilder.imageOption(imageOption);
		return self();
	}

	public B previewImageOptions(final ImageType... imageOptions) {
		return previewImageOptions(List.of(imageOptions));
	}

	public B previewImageOptions(final List<ImageType> imageOptions) {
		imageSetDefinitionBuilder.imageOptions(imageOptions);
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

	public B additionalProperty(final VideoType type, final EditorPropertyDefinition additionalProperty){
		return additionalProperties(type, Stream.concat(
				Optional.ofNullable(additionalPropertiesMapping).map(mapping -> mapping.get(type)).stream().flatMap(Collection::stream),
				Stream.of(additionalProperty)
		).collect(Collectors.toList()));
	}
	public B additionalProperties(final VideoType type, final List<EditorPropertyDefinition> additionalProperties){
		return additionalPropertiesMapping(Stream.concat(
				Stream.ofNullable(additionalPropertiesMapping).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(type, additionalProperties))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}
	public B additionalPropertiesMapping(final Map<VideoType, List<EditorPropertyDefinition>> additionalPropertiesMapping){
		this.additionalPropertiesMapping = additionalPropertiesMapping;
		return self();
	}

	public VideoSetDefinition build(final String name) {
		final VideoSetDefinition definition = new VideoSetDefinition(
				new SwitchableDefinitionBuilder<VideoType>(labelPrefix)
						.additionalPropertiesMapping(additionalPropertiesMapping)
						.propertyNameDecorator(PrefixExceptVideoFieldPropertyNameDecorator.class)
						.optionPropertyNameDecorator(SELECTION_PROPERTY_NAME_PROVIDER::apply)
						.fieldOptions(Stream.ofNullable(videoOptions).flatMap(Collection::stream).map(this::createFieldOption).collect(Collectors.toList()))
						.label(labelPrefix + "video.label")
						.removePreviouslySelected(removePreviouslySelected)
						.build(name),
				createAltTextField(name).orElse(null),
				imageSetDefinitionBuilder
						.label(labelPrefix + "previewImage.label")
						.build(PREVIEW_IMAGE_PROPERTY_NAME_PROVIDER.apply(name)),
				videoFieldI18n,
				previewImageRequired
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
