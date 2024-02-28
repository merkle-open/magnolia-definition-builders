package com.merkle.oss.magnolia.definition.custom.validator.template;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.jcr.util.PropertyUtil;

import javax.jcr.Node;
import java.util.Objects;

public class TemplateValidator extends AbstractValidator<Node> {
	private final String templateId;

	public TemplateValidator(
			final String templateId,
			final String errorMessage) {
			super(errorMessage);
		this.templateId = templateId;
	}

	@Override
	public ValidationResult apply(final Node value, final ValueContext context) {
		return toResult(value, isValid(value));
	}

	private boolean isValid(final Node value) {
		return value == null || Objects.equals(templateId, PropertyUtil.getString(value, NodeTypes.Renderable.TEMPLATE));
	}
}