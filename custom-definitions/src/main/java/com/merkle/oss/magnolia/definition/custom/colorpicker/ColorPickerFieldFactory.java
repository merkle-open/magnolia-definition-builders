package com.merkle.oss.magnolia.definition.custom.colorpicker;

import com.vaadin.ui.Component;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.field.factory.AbstractFieldFactory;

public class ColorPickerFieldFactory extends AbstractFieldFactory<Integer, ColorPickerFieldDefinition> {
	final ColorPickerFieldDefinition definition;

	public ColorPickerFieldFactory(final ColorPickerFieldDefinition definition, final ComponentProvider componentProvider) {
		super(definition, componentProvider);
		this.definition = definition;
	}

	@Override
	protected Component createFieldComponent() {
		return new ColorPickerField(definition);
	}
}
