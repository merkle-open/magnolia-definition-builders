package com.merkle.oss.magnolia.definition.custom.validator.mimetype;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import info.magnolia.dam.api.Asset;

import java.util.Set;

public class MimeTypeValidator extends AbstractValidator<Asset> {
	private final Set<String> mimeTypes;

	public MimeTypeValidator(
			final Set<String> mimeTypes,
			final String errorMessage) {
		super(errorMessage);
		this.mimeTypes = mimeTypes;
	}

	@Override
	public ValidationResult apply(final Asset value, final ValueContext context) {
		return toResult(value, isValid(value));
	}

	private boolean isValid(final Asset value) {
		return value == null || mimeTypes.contains(value.getMimeType());
	}
}