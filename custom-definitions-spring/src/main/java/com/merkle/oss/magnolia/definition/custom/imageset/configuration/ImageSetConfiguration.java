package com.merkle.oss.magnolia.definition.custom.imageset.configuration;

import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageModel;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageReferenceModel;
import info.magnolia.objectfactory.Components;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ImageSetConfiguration {

	@Bean("merkle-customDefinition-imageModelFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ImageModel.Factory imageModelFactory() {
		return Components.getComponent(ImageModel.Factory.class);
	}

	@Bean("merkle-customDefinition-imageReferenceModelFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ImageReferenceModel.Factory imageReferenceModelFactory() {
		return Components.getComponent(ImageReferenceModel.Factory.class);
	}
}
