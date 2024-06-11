package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.editor.validator.NodeNameValidatorDefinition;
import info.magnolia.ui.editor.validator.NodeNameValidatorDefinition.Mode;
import info.magnolia.ui.field.RegexpValidatorDefinition;

import javax.annotation.Nullable;
import java.util.Optional;

public class NodeNameValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<NodeNameValidatorDefinition, NodeNameValidatorDefinitionBuilder>{
	@Nullable
	private String pattern;
	@Nullable
	private Mode mode;

	public NodeNameValidatorDefinitionBuilder() {}
	public NodeNameValidatorDefinitionBuilder(final NodeNameValidatorDefinition definition) {
		super(definition);
		pattern(definition.getPattern());
		mode(definition.getMode());
	}

	public NodeNameValidatorDefinitionBuilder pattern(final String pattern) {
		this.pattern = pattern;
		return self();
	}

	public NodeNameValidatorDefinitionBuilder mode(final Mode mode) {
		this.mode = mode;
		return self();
	}

	public NodeNameValidatorDefinition build() {
		return build("nodeNameValidator");
	}
	public NodeNameValidatorDefinition build(final String name) {
		final NodeNameValidatorDefinition definition = new NodeNameValidatorDefinition();
		super.populate(definition, name);
		Optional.ofNullable(mode).ifPresent(definition::setMode);
		Optional.ofNullable(pattern).ifPresent(definition::setPattern);
		return definition;
	}
}