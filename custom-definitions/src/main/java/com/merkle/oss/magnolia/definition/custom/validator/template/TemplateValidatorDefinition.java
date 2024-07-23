package com.merkle.oss.magnolia.definition.custom.validator.template;

import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;
import info.magnolia.i18nsystem.I18nable;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;
import info.magnolia.ui.field.ValidatorType;

import java.util.Set;

@I18nable(keyGenerator = FieldValidatorDefinitionKeyGenerator.class)
@ValidatorType("templateValidator")
public class TemplateValidatorDefinition extends ConfiguredFieldValidatorDefinition {
	private final Set<String> templateIds;

	public TemplateValidatorDefinition(final Set<String> templateIds) {
		this.templateIds = templateIds;
	}

	@Override
	public Class<? extends FieldValidatorFactory> getFactoryClass() {
		return TemplateValidatorFactory.class;
	}

	public Set<String> getTemplateIds() {
		return templateIds;
	}
}