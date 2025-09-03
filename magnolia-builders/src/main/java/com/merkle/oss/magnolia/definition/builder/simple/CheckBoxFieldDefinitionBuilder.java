package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.CheckBoxFieldDefinition;

import jakarta.annotation.Nullable;
import java.util.Optional;

/**
 * builds a {@link CheckBoxFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Checkbox-field.html">magnolia Docs - Checkbox field </a>
 * @author Merkle DACH
 */
public class CheckBoxFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<Boolean, CheckBoxFieldDefinition, CheckBoxFieldDefinitionBuilder> {
	@Nullable
	private String buttonLabel;

	public CheckBoxFieldDefinitionBuilder() {}
	public CheckBoxFieldDefinitionBuilder(final CheckBoxFieldDefinition definition) {
		super(definition);
		buttonLabel(definition.getButtonLabel());
	}

	public CheckBoxFieldDefinitionBuilder buttonLabel(final String buttonLabel) {
		this.buttonLabel = buttonLabel;
		return self();
	}

	public CheckBoxFieldDefinition build(final String name) {
		final CheckBoxFieldDefinition definition = new CheckBoxFieldDefinition();
		super.populate(definition, name);
		Optional.ofNullable(buttonLabel).ifPresent(definition::setButtonLabel);
		return definition;
	}
}
