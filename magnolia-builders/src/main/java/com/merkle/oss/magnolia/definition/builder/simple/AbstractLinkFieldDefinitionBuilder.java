package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.editor.ItemPreviewDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

import java.util.Optional;

import jakarta.annotation.Nullable;

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

	protected AbstractLinkFieldDefinitionBuilder() {}
	protected AbstractLinkFieldDefinitionBuilder(final D definition) {
		super(definition);
		chooserId(definition.getChooserId());
		buttonSelectNewLabel(definition.getButtonSelectNewLabel());
		buttonSelectOtherLabel(definition.getButtonSelectNewLabel());
		preview(definition.getPreview());
		editable(definition.isEditable());
		showOptions(definition.isShowOptions());
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

	@Override
	protected void populate(final D definition, final String name, final DatasourceDefinition datasourceDefinition) {
		super.populate(definition, name, datasourceDefinition);
		Optional.ofNullable(chooserId).ifPresent(definition::setChooserId);
		Optional.ofNullable(buttonSelectNewLabel).ifPresent(definition::setButtonSelectNewLabel);
		Optional.ofNullable(buttonSelectOtherLabel).ifPresent(definition::setButtonSelectOtherLabel);
		Optional.ofNullable(preview).ifPresent(definition::setPreview);
		Optional.ofNullable(editable).ifPresent(definition::setEditable);
		Optional.ofNullable(showOptions).ifPresent(definition::setShowOptions);
	}
}
