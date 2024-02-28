package com.merkle.oss.magnolia.definition.custom.validator.mimetype;

import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;
import info.magnolia.i18nsystem.I18nable;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;
import info.magnolia.ui.field.ValidatorType;

import java.util.Set;

@I18nable(keyGenerator = FieldValidatorDefinitionKeyGenerator.class)
@ValidatorType("mimeTypeValidator")
public class MimeTypeValidatorDefinition extends ConfiguredFieldValidatorDefinition {
	private final Set<String> mimeTypes;

	public MimeTypeValidatorDefinition(final Set<String> mimeTypes) {
		this.mimeTypes = mimeTypes;
	}

	@Override
	public Class<? extends FieldValidatorFactory> getFactoryClass() {
		return MimeTypeValidatorFactory.class;
	}

	public Set<String> getMimeTypes() {
		return mimeTypes;
	}
}