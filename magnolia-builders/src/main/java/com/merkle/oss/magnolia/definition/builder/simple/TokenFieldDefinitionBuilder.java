package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.ComboBoxFieldDefinition;
import info.magnolia.ui.field.TokenFieldDefinition;

import java.util.Collection;
import java.util.Optional;

import jakarta.annotation.Nullable;

public class TokenFieldDefinitionBuilder<T> extends AbstractConfiguredFieldDefinitionBuilder<Collection<String>, TokenFieldDefinition<T>, TokenFieldDefinitionBuilder<T>> {

	@Nullable
	private ComboBoxFieldDefinition<T> comboBox;

	public TokenFieldDefinitionBuilder() {}
	public TokenFieldDefinitionBuilder(final TokenFieldDefinition<T> definition) {
		super(definition);
		comboBox(definition.getComboBox());
	}

	public TokenFieldDefinitionBuilder<T> comboBox(final ComboBoxFieldDefinition<T> comboBox) {
		this.comboBox = comboBox;
		return self();
	}

	public TokenFieldDefinition<T> build(final String name) {
		final TokenFieldDefinition<T> definition = new TokenFieldDefinition<>();
		super.populate(definition, name);
		Optional.ofNullable(comboBox).ifPresent(definition::setComboBox);
		return definition;
	}
}
