package com.merkle.oss.magnolia.definition.custom.validator.nodetype;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import info.magnolia.jcr.predicate.NodeTypePredicate;

import jakarta.annotation.Nullable;
import javax.jcr.Node;

public class NodeTypeValidator extends AbstractValidator<Node> {
	private final NodeTypePredicate nodeTypePredicate;

	public NodeTypeValidator(
			final String nodeType,
			final String errorMessage) {
			super(errorMessage);
		this.nodeTypePredicate = new NodeTypePredicate(nodeType);
	}

	@Override
	public ValidationResult apply(@Nullable final Node value, final ValueContext context) {
		return toResult(value, isValid(value));
	}

	private boolean isValid(@Nullable final Node value) {
		return value == null || nodeTypePredicate.evaluateTyped(value);
	}
}