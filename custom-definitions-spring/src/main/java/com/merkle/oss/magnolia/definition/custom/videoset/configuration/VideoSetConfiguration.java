package com.merkle.oss.magnolia.definition.custom.videoset.configuration;

import com.merkle.oss.magnolia.definition.custom.videoset.model.VideoModel;
import com.merkle.oss.magnolia.definition.custom.videoset.model.VideoReferenceModel;
import info.magnolia.objectfactory.Components;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class VideoSetConfiguration {

	@Bean("merkle-customDefinition-videoModelFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public VideoModel.Factory videoModelFactory() {
		return Components.getComponent(VideoModel.Factory.class);
	}

	@Bean("merkle-customDefinition-videoReferenceModelFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public VideoReferenceModel.Factory videoReferenceModelFactory() {
		return Components.getComponent(VideoReferenceModel.Factory.class);
	}
}
