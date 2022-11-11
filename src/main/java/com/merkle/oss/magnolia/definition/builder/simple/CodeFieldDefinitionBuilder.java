package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.CodeFieldDefinition;

import javax.annotation.Nullable;
import java.util.Optional;

public class CodeFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<String, CodeFieldDefinition, CodeFieldDefinitionBuilder> {
	@Nullable
	private String language;
	@Nullable
	private Integer height;

	public CodeFieldDefinitionBuilder() {
		super(CodeFieldDefinition::new);
	}

	public CodeFieldDefinitionBuilder height(final int height) {
		this.height = height;
		return self();
	}

	public CodeFieldDefinitionBuilder language(final String language) {
		this.language = language;
		return self();
	}

	public CodeFieldDefinition build(final String name) {
		final CodeFieldDefinition definition = super.build(name);
		Optional.ofNullable(height).ifPresent(definition::setHeight);
		Optional.ofNullable(language).ifPresent(definition::setLanguage);
		return definition;
	}
}
