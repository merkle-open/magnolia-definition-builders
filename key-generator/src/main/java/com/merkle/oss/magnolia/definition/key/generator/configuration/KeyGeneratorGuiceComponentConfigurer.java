package com.merkle.oss.magnolia.definition.key.generator.configuration;

import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import info.magnolia.objectfactory.guice.AbstractGuiceComponentConfigurer;
import info.magnolia.ui.editor.ConfiguredFormDefinition;

public class KeyGeneratorGuiceComponentConfigurer extends AbstractGuiceComponentConfigurer {
	@Override
	protected void configure() {
		super.configure();
		final Multibinder<Class<?>> excludedAncestorsMultibinder = Multibinder.newSetBinder(binder(), new TypeLiteral<Class<?>>(){}, ExcludedAncestors.class);
		excludedAncestorsMultibinder.addBinding().toInstance(ConfiguredFormDefinition.class);
	}
}
