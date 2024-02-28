package com.merkle.oss.magnolia.definition.custom.validator.template;

import com.merkle.oss.magnolia.definition.builder.validator.AbstractConfiguredFieldValidatorDefinitionBuilder;

public class TemplateValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<TemplateValidatorDefinition, TemplateValidatorDefinitionBuilder> {

	public TemplateValidatorDefinition build(final String templateId) {
		return build("templateValidator", templateId);
	}
	public TemplateValidatorDefinition build(final String name, final String templateId) {
		final TemplateValidatorDefinition definition = new TemplateValidatorDefinition(templateId);
		super.populate(definition, name);
		return definition;
	}
}
