package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.JcrMultiValueFieldDefinition;

import javax.jcr.Property;

public class JcrMultiValueFieldDefinitionBuilder extends AbstractMultiFieldDefinitionBuilder<Property, JcrMultiValueFieldDefinition, JcrMultiValueFieldDefinitionBuilder> {

	public JcrMultiValueFieldDefinition build(final String name, final EditorPropertyDefinition field) {
		final JcrMultiValueFieldDefinition definition = new JcrMultiValueFieldDefinition();
		super.populate(definition, name, field);
		definition.init();
		return definition;
	}
}
