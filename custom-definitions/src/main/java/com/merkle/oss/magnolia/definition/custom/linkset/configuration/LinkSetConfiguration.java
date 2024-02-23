package com.merkle.oss.magnolia.definition.custom.linkset.configuration;

import com.merkle.oss.magnolia.definition.custom.linkset.model.AssetLinkFactory;
import com.merkle.oss.magnolia.definition.custom.linkset.model.ExternalLinkFactory;
import com.merkle.oss.magnolia.definition.custom.linkset.model.InternalLinkFactory;
import com.merkle.oss.magnolia.definition.custom.linkset.model.LinkModelFactory;
import com.merkle.oss.magnolia.definition.custom.linkset.model.util.ExtendedLinkAnchorModifier;
import com.merkle.oss.magnolia.definition.custom.linkset.model.util.ExtendedLinkQueryParamModifier;
import info.magnolia.objectfactory.Components;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LinkSetConfiguration {

	@Bean("merkle-customDefinition-linkModelFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public LinkModelFactory linkModelFactory() {
		return Components.getComponent(LinkModelFactory.class);
	}

	@Bean("merkle-customDefinition-assetLinkFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public AssetLinkFactory assetLinkFactory() {
		return Components.getComponent(AssetLinkFactory.class);
	}

	@Bean("merkle-customDefinition-internalLinkFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public InternalLinkFactory internalLinkFactory() {
		return Components.getComponent(InternalLinkFactory.class);
	}

	@Bean("merkle-customDefinition-externalLinkFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ExternalLinkFactory externalLinkFactory() {
		return Components.getComponent(ExternalLinkFactory.class);
	}

	@Bean("merkle-customDefinition-extendedLinkAnchorModifier")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ExtendedLinkAnchorModifier extendedLinkAnchorModifier() {
		return Components.getComponent(ExtendedLinkAnchorModifier.class);
	}

	@Bean("merkle-customDefinition-extendedLinkQueryParamModifier")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ExtendedLinkQueryParamModifier extendedLinkQueryParamModifier() {
		return Components.getComponent(ExtendedLinkQueryParamModifier.class);
	}
}
