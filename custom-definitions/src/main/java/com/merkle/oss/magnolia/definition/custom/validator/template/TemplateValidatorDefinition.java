package com.merkle.oss.magnolia.definition.custom.validator.template;

import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;
import info.magnolia.i18nsystem.I18nable;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;
import info.magnolia.ui.field.ValidatorType;

@I18nable(keyGenerator = FieldValidatorDefinitionKeyGenerator.class)
@ValidatorType("templateValidator")
public class TemplateValidatorDefinition extends ConfiguredFieldValidatorDefinition {
	private final String templateId;

	public TemplateValidatorDefinition(final String templateId) {
		this.templateId = templateId;
	}

	@Override
	public Class<? extends FieldValidatorFactory> getFactoryClass() {
		return TemplateValidatorFactory.class;
	}

	public String getTemplateId() {
		return templateId;
	}
}