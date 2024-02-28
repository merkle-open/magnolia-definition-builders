package com.merkle.oss.magnolia.definition.custom.validator.nodetype;

import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;
import info.magnolia.i18nsystem.I18nable;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;
import info.magnolia.ui.field.ValidatorType;

@I18nable(keyGenerator = FieldValidatorDefinitionKeyGenerator.class)
@ValidatorType("nodeTypeValidator")
public class NodeTypeValidatorDefinition extends ConfiguredFieldValidatorDefinition {
	private final String nodeType;

	public NodeTypeValidatorDefinition(final String nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public Class<? extends FieldValidatorFactory> getFactoryClass() {
		return NodeTypeValidatorFactory.class;
	}

	public String getNodeType() {
		return nodeType;
	}
}