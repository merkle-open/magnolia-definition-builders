package com.merkle.oss.magnolia.definition.custom.videoset;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinition;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.editor.FormView;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.TextFieldDefinition;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;
import info.magnolia.ui.framework.layout.StackedLayoutProducer;

import javax.annotation.Nullable;
import javax.jcr.Node;
import java.util.List;

public class VideoSetDefinition extends ConfiguredComplexPropertyDefinition<Node> implements FormDefinition<Node> {
	private final SwitchableDefinition video;
	private final TextFieldDefinition altText;
	private final ImageSetDefinition previewImage;
	private boolean readOnly;
	private boolean required;
	@Nullable
	private List<FieldValidatorDefinition> validators;

	public VideoSetDefinition(
			final SwitchableDefinition video,
			final TextFieldDefinition altText,
			final ImageSetDefinition previewImage
	) {
		this.video = video;
		this.altText = altText;
		this.previewImage = previewImage;
		setImplementationClass((Class) FormView.class);
		setItemProvider(new CurrentItemProviderDefinition<>());
	}

	public SwitchableDefinition getVideo() {
		return video;
	}

	public TextFieldDefinition getAltTextField() {
		return altText;
	}

	public ImageSetDefinition getPreviewImage() {
		return previewImage;
	}

	@Override
	public List<EditorPropertyDefinition> getProperties() {
		return List.of(video, altText, previewImage);
	}

	@Override
	public FieldLayoutDefinition<?> getLayout() {
		return new StackedLayoutProducer.Definition();
	}

	@Override
	public void setI18n(final boolean i18n) {
		super.setI18n(i18n);
		video.setI18n(i18n);
		altText.setI18n(i18n);
		previewImage.setI18n(i18n);
	}

	public void setReadOnly(final boolean readOnly) {
		this.readOnly = readOnly;
		video.setReadOnly(readOnly);
		altText.setReadOnly(readOnly);
		previewImage.setReadOnly(readOnly);
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setRequired(final boolean required) {
		this.required = required;
		video.setRequired(required);
	}

	public boolean isRequired() {
		return required;
	}

	public void setValidators(final List<FieldValidatorDefinition> validators){
		this.validators = validators;
		video.setValidators(validators);
	}

	public List<FieldValidatorDefinition> getValidators() {
		return validators;
	}
}
