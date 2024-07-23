package com.merkle.oss.magnolia.definition.custom.validator.template;

import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.jcr.util.PropertyUtil;

import java.util.Set;

import javax.jcr.Node;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class TemplateValidator extends AbstractValidator<Node> {
	private final Set<String> templateIds;

	public TemplateValidator(
			final Set<String> templateIds,
			final String errorMessage) {
			super(errorMessage);
		this.templateIds = templateIds;
	}

	@Override
	public ValidationResult apply(final Node value, final ValueContext context) {
		return toResult(value, isValid(value));
	}

	private boolean isValid(final Node value) {
		return value == null || templateIds.contains(PropertyUtil.getString(value, NodeTypes.Renderable.TEMPLATE));
	}
}