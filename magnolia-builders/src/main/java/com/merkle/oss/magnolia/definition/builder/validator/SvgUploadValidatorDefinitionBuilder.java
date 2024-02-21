package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.SvgUploadValidatorDefinition;

public class SvgUploadValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<SvgUploadValidatorDefinition, SvgUploadValidatorDefinitionBuilder>{

	public SvgUploadValidatorDefinitionBuilder() {
		super(SvgUploadValidatorDefinition::new);
	}

	public SvgUploadValidatorDefinition build() {
		return build("svgUploadValidator");
	}
	public SvgUploadValidatorDefinition build(final String name) {
		return super.build(name);
	}
}
