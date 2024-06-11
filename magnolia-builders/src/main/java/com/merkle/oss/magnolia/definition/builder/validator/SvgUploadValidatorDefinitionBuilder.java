package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.SvgUploadValidatorDefinition;

public class SvgUploadValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<SvgUploadValidatorDefinition, SvgUploadValidatorDefinitionBuilder>{

	public SvgUploadValidatorDefinitionBuilder() {}
	public SvgUploadValidatorDefinitionBuilder(final SvgUploadValidatorDefinition definition) {
		super(definition);
	}

	public SvgUploadValidatorDefinition build() {
		return build("svgUploadValidator");
	}
	public SvgUploadValidatorDefinition build(final String name) {
		final SvgUploadValidatorDefinition definition = new SvgUploadValidatorDefinition();
		super.populate(definition, name);
		return definition;
	}
}
