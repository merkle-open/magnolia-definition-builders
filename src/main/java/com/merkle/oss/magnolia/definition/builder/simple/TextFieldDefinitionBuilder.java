package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.TextFieldDefinition;

import javax.annotation.Nullable;
import java.util.Optional;

public class TextFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<String, TextFieldDefinition, TextFieldDefinitionBuilder> {
	@Nullable
	private Integer rows;
	@Nullable
	private Integer maxLength;
	@Nullable
	private String placeholder;

	public TextFieldDefinitionBuilder() {
		super(TextFieldDefinition::new);
	}

	public TextFieldDefinitionBuilder rows(final int rows) {
		this.rows = rows;
		return self();
	}

	public TextFieldDefinitionBuilder maxLength(final int maxLength) {
		this.maxLength = maxLength;
		return self();
	}

	public TextFieldDefinitionBuilder placeholder(final String placeholder) {
		this.placeholder = placeholder;
		return self();
	}

	public TextFieldDefinition build(final String name) {
		final TextFieldDefinition definition = super.build(name);
		Optional.ofNullable(rows).ifPresent(definition::setRows);
		Optional.ofNullable(maxLength).ifPresent(definition::setMaxLength);
		Optional.ofNullable(placeholder).ifPresent(definition::setPlaceholder);
		return definition;
	}
}
