package com.merkle.oss.magnolia.definition.custom.videoset;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.AbstractImageSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinitionBuilder;
import info.magnolia.ui.field.FieldValidatorDefinition;

import javax.annotation.Nullable;
import javax.jcr.Node;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractVideoSetDefinitionBuilder<B extends AbstractVideoSetDefinitionBuilder<B>> extends AbstractConfiguredComplexPropertyDefinitionBuilder<Node, VideoSetDefinition, B> {
	public static final String SELECTION_SUFFIX = "_selection";
	public static final String PREVIEW_IMAGE_SUFFIX = "_previewImage";
	private final AbstractImageSetDefinitionBuilder<?> imageSetDefinitionBuilder;
	private final String labelPrefix;
	@Nullable
	private List<VideoType> videoOptions;
	@Nullable
	private Boolean readOnly;
	@Nullable
	private Boolean required;
	@Nullable
	private List<FieldValidatorDefinition> validators;

	protected AbstractVideoSetDefinitionBuilder(
			final AbstractImageSetDefinitionBuilder<?> imageSetDefinitionBuilder,
			final String labelPrefix
	) {
		this.labelPrefix = labelPrefix;
		this.imageSetDefinitionBuilder = imageSetDefinitionBuilder;
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
				Stream.ofNullable(validators).flatMap(Collection::stream),
				Stream.of(validator)
		).collect(Collectors.toList()));
	}

	public B validators(final List<FieldValidatorDefinition> validators) {
		this.validators = validators;
		return self();
	}

	public VideoSetDefinition build(final String name) {
		final VideoSetDefinition definition = new VideoSetDefinition(
				new SwitchableDefinitionBuilder<VideoType>(labelPrefix)
						.optionPropertyNameDecorator(source -> source + SELECTION_SUFFIX)
						.fieldOptions(Stream.ofNullable(videoOptions).flatMap(Collection::stream).map(this::createFieldOption).collect(Collectors.toList()))
						.label(labelPrefix + "video.label")
						.build(name),
				imageSetDefinitionBuilder
						.label(labelPrefix + "previewImage.label")
						.build(name + PREVIEW_IMAGE_SUFFIX)
		);
		super.populate(definition, name);
		Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
		Optional.ofNullable(required).ifPresent(definition::setRequired);
		Optional.ofNullable(validators).ifPresent(definition::setValidators);
		return definition;
	}
}
