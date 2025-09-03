package com.merkle.oss.magnolia.definition.custom.validator.mimetype;

import com.google.common.base.Joiner;
import com.vaadin.data.Validator;
import info.magnolia.dam.api.Asset;
import info.magnolia.ui.field.AbstractFieldValidatorFactory;

import jakarta.inject.Inject;
import java.text.MessageFormat;

public class MimeTypeValidatorFactory extends AbstractFieldValidatorFactory<MimeTypeValidatorDefinition, Asset> {

	@Inject
	public MimeTypeValidatorFactory(final MimeTypeValidatorDefinition definition) {
		super(definition);
	}

	@Override
	public Validator<Asset> createValidator() {
		return new MimeTypeValidator(definition.getMimeTypes(), getI18nErrorMessage());
	}

	@Override
	public String getI18nErrorMessage() {
		final MessageFormat fmt = new MessageFormat(super.getI18nErrorMessage());
		final String[] args = new String[]{
				Joiner.on(",").join(definition.getMimeTypes())
		};
		return fmt.format(args);
	}
}
