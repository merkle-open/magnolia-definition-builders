package com.merkle.oss.magnolia.definition.custom.videoset;

import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.editor.FormView;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.TextFieldDefinition;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;
import info.magnolia.ui.framework.layout.StackedLayoutProducer;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinition;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;

import jakarta.annotation.Nullable;

public class VideoSetDefinition extends ConfiguredComplexPropertyDefinition<Node> implements FormDefinition<Node> {
	private final SwitchableDefinition video;
	@Nullable
	private final TextFieldDefinition altText;
	private final ImageSetDefinition previewImage;
    private final boolean videoFieldI18n;
    private final boolean previewImageRequired;
    private boolean readOnly;
	private boolean required;

	public VideoSetDefinition(
			final SwitchableDefinition video,
			@Nullable final TextFieldDefinition altText,
			final ImageSetDefinition previewImage,
			final boolean videoFieldI18n,
			final boolean previewImageRequired
	) {
		this.video = video;
		this.altText = altText;
		this.previewImage = previewImage;
        this.videoFieldI18n = videoFieldI18n;
        this.previewImageRequired = previewImageRequired;
        setImplementationClass((Class) FormView.class);
		setItemProvider(new CurrentItemProviderDefinition<>());
	}

	public SwitchableDefinition getVideo() {
		return video;
	}

	public Optional<TextFieldDefinition> getAltTextField() {
		return Optional.ofNullable(altText);
	}

	public ImageSetDefinition getPreviewImage() {
		return previewImage;
	}

	@Override
	public List<EditorPropertyDefinition> getProperties() {
		return Stream.of(
				Stream.of(video),
				Stream.ofNullable(altText),
				Stream.of(previewImage)
		).flatMap(field -> field).collect(Collectors.toList());
	}

	@Override
	public FieldLayoutDefinition<?> getLayout() {
		return new StackedLayoutProducer.Definition();
	}

	@Override
	public void setI18n(final boolean i18n) {
		super.setI18n(i18n);
		video.setI18n(videoFieldI18n && i18n);
		previewImage.setI18n(i18n);
		if(altText != null) {
			altText.setI18n(i18n);
		}
	}

	public void setReadOnly(final boolean readOnly) {
		this.readOnly = readOnly;
		video.setReadOnly(readOnly);
		previewImage.setReadOnly(readOnly);
		if(altText != null) {
			altText.setReadOnly(readOnly);
		}
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setRequired(final boolean required) {
		this.required = required;
		video.setRequired(required);
		previewImage.setRequired(previewImageRequired && required);
	}

	public boolean isRequired() {
		return required;
	}

	public void setValidators(final List<FieldValidatorDefinition> validators){
		video.setValidators(validators);
	}
	public List<FieldValidatorDefinition> getValidators() {
		return video.getValidators();
	}
	public void setValidators(final String field, final List<FieldValidatorDefinition> validators) {
		video.setValidators(field, validators);
	}
	public List<FieldValidatorDefinition> getValidators(final String field) {
		return video.getValidators(field);
	}
}
