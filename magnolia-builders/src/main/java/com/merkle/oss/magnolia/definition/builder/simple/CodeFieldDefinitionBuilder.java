package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.CodeFieldDefinition;

import jakarta.annotation.Nullable;
import java.util.Optional;

/**
 * builds a {@link CodeFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Code-field.html">magnolia Docs - Code field </a>
 * @author Merkle DACH
 */
public class CodeFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<String, CodeFieldDefinition, CodeFieldDefinitionBuilder> {
	@Nullable
	private String language;
	@Nullable
	private Integer height;

	public CodeFieldDefinitionBuilder() {}
	public CodeFieldDefinitionBuilder(final CodeFieldDefinition definition) {
		super(definition);
		language(definition.getLanguage());
		height(definition.getHeight());
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
		final CodeFieldDefinition definition = new CodeFieldDefinition();
		super.populate(definition, name);
		Optional.ofNullable(height).ifPresent(definition::setHeight);
		Optional.ofNullable(language).ifPresent(definition::setLanguage);
		return definition;
	}
}
