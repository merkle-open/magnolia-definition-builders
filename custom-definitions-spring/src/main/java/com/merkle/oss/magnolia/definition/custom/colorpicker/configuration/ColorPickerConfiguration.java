package com.merkle.oss.magnolia.definition.custom.colorpicker.configuration;

import info.magnolia.objectfactory.Components;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.merkle.oss.magnolia.definition.custom.colorpicker.model.ColorFactory;

@Configuration
public class ColorPickerConfiguration {

	@Bean("merkle-customDefinition-colorPickerFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ColorFactory colorFactory() {
		return Components.getComponent(ColorFactory.class);
	}
}
