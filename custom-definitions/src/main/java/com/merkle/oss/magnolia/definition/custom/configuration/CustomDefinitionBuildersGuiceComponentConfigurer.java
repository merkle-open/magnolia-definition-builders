package com.merkle.oss.magnolia.definition.custom.configuration;

import com.merkle.oss.magnolia.definition.custom.imageset.configuration.ImageSetGuiceModule;
import com.merkle.oss.magnolia.definition.custom.linkset.configuration.LinkSetGuiceModule;
import com.merkle.oss.magnolia.definition.custom.switchable.configuration.SwitchableGuiceModule;
import info.magnolia.objectfactory.guice.AbstractGuiceComponentConfigurer;

public class CustomDefinitionBuildersGuiceComponentConfigurer extends AbstractGuiceComponentConfigurer {
	@Override
	protected void configure() {
		super.configure();
		new SwitchableGuiceModule().configure(binder());
		new LinkSetGuiceModule().configure(binder());
		new ImageSetGuiceModule().configure(binder());
	}
}
