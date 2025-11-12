package com.merkle.oss.magnolia.definition.builder.simple;

import com.vaadin.ui.ComboBox;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;

import jakarta.annotation.Nullable;
import java.util.Optional;

public abstract class AbstractComboBoxFieldDefinitionBuilder<T, D extends ComboBoxFieldDefinition<T>, B extends AbstractComboBoxFieldDefinitionBuilder<T, D, B>> extends AbstractSelectFieldDefinitionBuilder<T, DatasourceDefinition, D, B> {
	@Nullable
	private Boolean textInputAllowed;
	@Nullable
	private Integer pageLength;
	@Nullable
	private String emptySelectionCaption;
	@Nullable
	private Boolean emptySelectionAllowed;
	@Nullable
	private Boolean scrollToSelectedItem;
	@Nullable
	private String popWidth;
	@Nullable
	private String placeholder;
	@Nullable
	private Class<? extends ComboBox.NewItemProvider<T>> newItemProviderClass;

	protected AbstractComboBoxFieldDefinitionBuilder() {}
	protected AbstractComboBoxFieldDefinitionBuilder(final D definition) {
		super(definition);
		textInputAllowed(definition.isTextInputAllowed());
		pageLength(definition.getPageLength());
		emptySelectionCaption(definition.getEmptySelectionCaption());
		emptySelectionAllowed(definition.isEmptySelectionAllowed());
		scrollToSelectedItem(definition.isScrollToSelectedItem());
		popWidth(definition.getPopWidth());
		placeholder(definition.getPlaceholder());
		newItemProviderClass(definition.getNewItemProviderClass());
	}

	public B textInputAllowed() {
		return textInputAllowed(true);
	}

	public B textInputAllowed(final Boolean textInputAllowed) {
		this.textInputAllowed = textInputAllowed;
		return self();
	}

	public B pageLength(final int pageLength) {
		this.pageLength = pageLength;
		return self();
	}

	public B emptySelectionCaption(final String emptySelectionCaption) {
		this.emptySelectionCaption = emptySelectionCaption;
		return self();
	}

	public B emptySelectionAllowed() {
		return emptySelectionAllowed(true);
	}

	public B emptySelectionAllowed(final Boolean emptySelectionAllowed) {
		this.emptySelectionAllowed = emptySelectionAllowed;
		return self();
	}

	public B scrollToSelectedItem() {
		return scrollToSelectedItem(true);
	}

	public B scrollToSelectedItem(final Boolean scrollToSelectedItem) {
		this.scrollToSelectedItem = scrollToSelectedItem;
		return self();
	}

	public B popWidth(final String popWidth) {
		this.popWidth = popWidth;
		return self();
	}

	public B placeholder(final String placeholder) {
		this.placeholder = placeholder;
		return self();
	}

	public B newItemProviderClass(final Class<? extends ComboBox.NewItemProvider<T>> newItemProviderClass) {
		this.newItemProviderClass = newItemProviderClass;
		return self();
	}

	@Override
	protected void populate(final D definition, final String name, final DatasourceDefinition datasourceDefinition) {
		super.populate(definition, name, datasourceDefinition);
		Optional.ofNullable(textInputAllowed).ifPresent(definition::setTextInputAllowed);
		Optional.ofNullable(pageLength).ifPresent(definition::setPageLength);
		Optional.ofNullable(emptySelectionCaption).ifPresent(definition::setEmptySelectionCaption);
		Optional.ofNullable(emptySelectionAllowed).ifPresent(definition::setEmptySelectionAllowed);
		Optional.ofNullable(scrollToSelectedItem).ifPresent(definition::setScrollToSelectedItem);
		Optional.ofNullable(popWidth).ifPresent(definition::setPopWidth);
		Optional.ofNullable(placeholder).ifPresent(definition::setPlaceholder);
		Optional.ofNullable(newItemProviderClass).ifPresent(definition::setNewItemProviderClass);
	}
}
