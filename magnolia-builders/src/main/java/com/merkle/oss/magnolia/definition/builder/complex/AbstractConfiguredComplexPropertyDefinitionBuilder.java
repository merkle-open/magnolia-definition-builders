package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.editor.ItemProviderDefinition;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;

import jakarta.annotation.Nullable;
import java.util.Optional;

public abstract class AbstractConfiguredComplexPropertyDefinitionBuilder<T, D extends ConfiguredComplexPropertyDefinition<T>, B extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, D, B>> {

	@Nullable
	private String label;
	@Nullable
	private String description;
	@Nullable
	private Boolean i18n;
	@Nullable
	private String styleName;
	@Nullable
	private ItemProviderDefinition<T, T> itemProvider;

	protected AbstractConfiguredComplexPropertyDefinitionBuilder() {}
	protected AbstractConfiguredComplexPropertyDefinitionBuilder(final D definition) {
		label(definition.getLabel());
		description(definition.getDescription());
		i18n(definition.isI18n());
		styleName(definition.getStyleName());
		itemProvider(definition.getItemProvider());
	}

	public B label(final String label) {
		this.label = label;
		return self();
	}

	public B i18n() {
		return i18n(true);
	}

	public B i18n(final boolean i18n) {
		this.i18n = i18n;
		return self();
	}

	public B description(final String description) {
		this.description = description;
		return self();
	}

	public B styleName(final String styleName) {
		this.styleName = styleName;
		return self();
	}

	public B itemProvider(final ItemProviderDefinition<T, T> itemProvider) {
		this.itemProvider = itemProvider;
		return self();
	}

	@SuppressWarnings("unchecked")
	protected B self() {
		return (B) this;
	}

	protected void populate(final D definition, final String name) {
		definition.setName(name);
		Optional.ofNullable(label).ifPresent(definition::setLabel);
		Optional.ofNullable(i18n).ifPresent(definition::setI18n);
		Optional.ofNullable(description).ifPresent(definition::setDescription);
		Optional.ofNullable(styleName).ifPresent(definition::setStyleName);
		Optional.ofNullable(itemProvider).ifPresent(definition::setItemProvider);
	}
}
