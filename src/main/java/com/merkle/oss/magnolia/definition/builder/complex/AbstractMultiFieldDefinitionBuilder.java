package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.editor.MultiFormDefinition;
import info.magnolia.ui.editor.MultiFormView;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.MultiFieldDefinition;

import javax.annotation.Nullable;
import javax.inject.Provider;
import java.util.Optional;

public class AbstractMultiFieldDefinitionBuilder<T, D extends MultiFieldDefinition<T>, B extends AbstractMultiFieldDefinitionBuilder<T, D, B>> extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, D, B> {
	@Nullable
	private MultiFormView.EntryResolution.Definition<T> entryResolution;
	@Nullable
	private MultiFormDefinition.OrderHandlerDefinition<T> orderHandler;
	@Nullable
	private Boolean canRemoveItems;
	@Nullable
	private String buttonSelectAddLabel;
	@Nullable
	private String buttonSelectRemoveLabel;
	@Nullable
	private Boolean required;
	@Nullable
	private String requiredErrorMessage;
	@Nullable
	private String itemCountErrorMessage;
	@Nullable
	private Integer minItems;
	@Nullable
	private Integer maxItems;

	protected AbstractMultiFieldDefinitionBuilder(final Provider<D> factory) {
		super(factory);
	}

	public B entryResolution(final MultiFormView.EntryResolution.Definition<T> entryResolution) {
		this.entryResolution = entryResolution;
		return self();
	}

	public B orderHandler(final MultiFormDefinition.OrderHandlerDefinition<T> orderHandler) {
		this.orderHandler = orderHandler;
		return self();
	}

	public B canRemoveItems() {
		return canRemoveItems(true);
	}

	public B canRemoveItems(final boolean canRemoveItems) {
		this.canRemoveItems = canRemoveItems;
		return self();
	}

	public B buttonSelectAddLabel(final String buttonSelectAddLabel) {
		this.buttonSelectAddLabel = buttonSelectAddLabel;
		return self();
	}

	public B buttonSelectRemoveLabel(final String buttonSelectRemoveLabel) {
		this.buttonSelectRemoveLabel = buttonSelectRemoveLabel;
		return self();
	}

	public B required() {
		return required(true);
	}

	public B required(final boolean required) {
		this.required = required;
		return self();
	}

	public B requiredErrorMessage(final String requiredErrorMessage) {
		this.requiredErrorMessage = requiredErrorMessage;
		return self();
	}

	public B itemCountErrorMessage(final String itemCountErrorMessage) {
		this.itemCountErrorMessage = itemCountErrorMessage;
		return self();
	}

	public B minItems(final int minItems) {
		this.minItems = minItems;
		return self();
	}

	public B maxItems(final int maxItems) {
		this.maxItems = maxItems;
		return self();
	}

	protected D build(final String name, final EditorPropertyDefinition field) {
		final D definition = super.build(name);
		definition.setField(field);
		Optional.ofNullable(entryResolution).ifPresent(definition::setEntryResolution);
		Optional.ofNullable(orderHandler).ifPresent(definition::setOrderHandler);
		Optional.ofNullable(canRemoveItems).ifPresent(definition::setCanRemoveItems);
		Optional.ofNullable(buttonSelectAddLabel).ifPresent(definition::setButtonSelectAddLabel);
		Optional.ofNullable(buttonSelectRemoveLabel).ifPresent(definition::setButtonSelectRemoveLabel);
		Optional.ofNullable(required).ifPresent(definition::setRequired);
		Optional.ofNullable(requiredErrorMessage).ifPresent(definition::setRequiredErrorMessage);
		Optional.ofNullable(itemCountErrorMessage).ifPresent(definition::setItemCountErrorMessage);
		Optional.ofNullable(minItems).ifPresent(definition::setMinItems);
		Optional.ofNullable(maxItems).ifPresent(definition::setMaxItems);
		return definition;
	}
}
