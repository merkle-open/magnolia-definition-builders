package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.editor.ItemPreviewDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

import javax.annotation.Nullable;
import javax.inject.Provider;
import java.util.Optional;

public abstract class AbstractLinkFieldDefinitionBuilder<T, D extends LinkFieldDefinition<T>, B extends AbstractLinkFieldDefinitionBuilder<T, D, B>> extends AbstractComboBoxFieldDefinitionBuilder<T, D, B> {
	@Nullable
	private String chooserId;
	@Nullable
	private String buttonSelectNewLabel;
	@Nullable
	private String buttonSelectOtherLabel;
	@Nullable
	private ItemPreviewDefinition<T> preview;
	@Nullable
	private Boolean editable;
	@Nullable
	private Boolean showOptions;

	protected AbstractLinkFieldDefinitionBuilder(final Provider<D> factory) {
		super(factory);
	}

	public B chooserId(final String chooserId) {
		this.chooserId = chooserId;
		return self();
	}

	public B buttonSelectNewLabel(final String buttonSelectNewLabel) {
		this.buttonSelectNewLabel = buttonSelectNewLabel;
		return self();
	}

	public B buttonSelectOtherLabel(final String buttonSelectOtherLabel) {
		this.buttonSelectOtherLabel = buttonSelectOtherLabel;
		return self();
	}

	public B preview(final ItemPreviewDefinition<T> preview) {
		this.preview = preview;
		return self();
	}

	public B editable() {
		return editable(true);
	}

	public B editable(final Boolean editable) {
		this.editable = editable;
		return self();
	}

	public B showOptions() {
		return showOptions(true);
	}

	public B showOptions(final Boolean showOptions) {
		this.showOptions = showOptions;
		return self();
	}

	public D build(final String name, final DatasourceDefinition datasourceDefinition) {
		final D definition = super.build(name, datasourceDefinition);
		Optional.ofNullable(chooserId).ifPresent(definition::setChooserId);
		Optional.ofNullable(buttonSelectNewLabel).ifPresent(definition::setButtonSelectNewLabel);
		Optional.ofNullable(buttonSelectOtherLabel).ifPresent(definition::setButtonSelectOtherLabel);
		Optional.ofNullable(preview).ifPresent(definition::setPreview);
		Optional.ofNullable(editable).ifPresent(definition::setEditable);
		Optional.ofNullable(showOptions).ifPresent(definition::setShowOptions);
		return definition;
	}
}
