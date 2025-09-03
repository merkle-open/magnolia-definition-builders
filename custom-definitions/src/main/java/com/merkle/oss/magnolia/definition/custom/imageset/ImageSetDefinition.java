package com.merkle.oss.magnolia.definition.custom.imageset;

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

import jakarta.annotation.Nullable;
import javax.jcr.Node;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageSetDefinition extends ConfiguredComplexPropertyDefinition<Node> implements FormDefinition<Node> {
	private final SwitchableDefinition image;
    @Nullable
    private final TextFieldDefinition altText;
    private final boolean imageFieldI18n;
    private boolean readOnly;
	private boolean required;

	public ImageSetDefinition(
			final SwitchableDefinition image,
			@Nullable final TextFieldDefinition altText,
			final boolean imageFieldI18n
	) {
		this.image = image;
		this.altText = altText;
        this.imageFieldI18n = imageFieldI18n;
        setImplementationClass((Class) FormView.class);
		setItemProvider(new CurrentItemProviderDefinition<>());
	}

	public SwitchableDefinition getImageField() {
		return image;
	}

	public Optional<TextFieldDefinition> getAltTextField() {
		return Optional.ofNullable(altText);
	}

	@Override
	public List<EditorPropertyDefinition> getProperties() {
		return Stream.concat(
                Stream.of(image),
                Stream.ofNullable(altText)
        ).collect(Collectors.toList());
	}

	@Override
	public FieldLayoutDefinition<?> getLayout() {
		return new StackedLayoutProducer.Definition();
	}

	@Override
	public void setI18n(final boolean i18n) {
		super.setI18n(i18n);
		image.setI18n(imageFieldI18n && i18n);
		if(altText != null) {
            altText.setI18n(i18n);
        }
	}

	public void setReadOnly(final boolean readOnly) {
		this.readOnly = readOnly;
		image.setReadOnly(readOnly);
        if(altText != null) {
            altText.setReadOnly(readOnly);
        }
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setRequired(final boolean required) {
		this.required = required;
		image.setRequired(required);
	}

	public boolean isRequired() {
		return required;
	}

	public void setValidators(final List<FieldValidatorDefinition> validators){
		image.setValidators(validators);
	}
	public List<FieldValidatorDefinition> getValidators() {
		return image.getValidators();
	}
	public void setValidators(final String field, final List<FieldValidatorDefinition> validators) {
		image.setValidators(field, validators);
	}
	public List<FieldValidatorDefinition> getValidators(final String field) {
		return image.getValidators(field);
	}
}
