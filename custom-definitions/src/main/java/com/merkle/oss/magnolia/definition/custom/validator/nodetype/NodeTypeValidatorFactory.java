package com.merkle.oss.magnolia.definition.custom.validator.nodetype;

import com.vaadin.data.Validator;
import info.magnolia.ui.field.AbstractFieldValidatorFactory;

import javax.inject.Inject;
import javax.jcr.Node;
import java.text.MessageFormat;

public class NodeTypeValidatorFactory extends AbstractFieldValidatorFactory<NodeTypeValidatorDefinition, Node> {

	@Inject
	public NodeTypeValidatorFactory(final NodeTypeValidatorDefinition definition) {
		super(definition);
	}

	@Override
	public Validator<Node> createValidator() {
		return new NodeTypeValidator(definition.getNodeType(), getI18nErrorMessage());
	}

	@Override
	public String getI18nErrorMessage() {
		final MessageFormat fmt = new MessageFormat(super.getI18nErrorMessage());
		final String[] args = new String[]{
				definition.getNodeType()
		};
		return fmt.format(args);
	}
}
