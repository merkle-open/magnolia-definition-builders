package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.JcrMultiValueFieldDefinition;

import javax.jcr.Property;

public class JcrMultiValueFieldDefinitionBuilder extends AbstractMultiFieldDefinitionBuilder<Property, JcrMultiValueFieldDefinition, JcrMultiValueFieldDefinitionBuilder> {

	public JcrMultiValueFieldDefinitionBuilder() {
		super(JcrMultiValueFieldDefinition::new);
	}

	public JcrMultiValueFieldDefinition build(final String name, final EditorPropertyDefinition field) {
		JcrMultiValueFieldDefinition fd = super.build(name, field);
		fd.init();
		return fd;
	}
}
