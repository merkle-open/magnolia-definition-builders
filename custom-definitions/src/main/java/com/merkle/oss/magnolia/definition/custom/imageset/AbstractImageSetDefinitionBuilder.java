package com.merkle.oss.magnolia.definition.custom.imageset;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
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

public abstract class AbstractImageSetDefinitionBuilder<B extends AbstractImageSetDefinitionBuilder<B>> extends AbstractConfiguredComplexPropertyDefinitionBuilder<Node, ImageSetDefinition, B> {
	public static final String SELECTION_SUFFIX = "_selection";
	public static final String ALT_TEXT_SUFFIX = "_alt";
	private final String labelPrefix;

	@Nullable
	private List<ImageType> imageOptions;
	@Nullable
	private Boolean readOnly;
	@Nullable
	private Boolean required;
	@Nullable
	private List<FieldValidatorDefinition> validators;

	protected AbstractImageSetDefinitionBuilder(final String labelPrefix) {
		this.labelPrefix = labelPrefix;
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
				Stream.ofNullable(validators).flatMap(Collection::stream),
				Stream.of(validator)
		).collect(Collectors.toList()));
	}

	public B validators(final List<FieldValidatorDefinition> validators) {
		this.validators = validators;
		return self();
	}

	public ImageSetDefinition build(final String name) {
		final ImageSetDefinition definition = new ImageSetDefinition(
				new SwitchableDefinitionBuilder<ImageType>(labelPrefix)
						.optionPropertyNameDecorator(source -> source + SELECTION_SUFFIX)
						.fieldOptions(Stream.ofNullable(imageOptions).flatMap(Collection::stream).map(this::createFieldOption).collect(Collectors.toList()))
						.label(labelPrefix + "image.label")
						.build(name),
				new TextFieldDefinitionBuilder()
						.label(labelPrefix + "altText.label")
						.build(name + ALT_TEXT_SUFFIX)
		);
		super.populate(definition, name);
		Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
		Optional.ofNullable(required).ifPresent(definition::setRequired);
		Optional.ofNullable(validators).ifPresent(definition::setValidators);
		return definition;
	}
}
