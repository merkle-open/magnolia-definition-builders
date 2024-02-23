package com.merkle.oss.magnolia.definition.custom.switchable.configuration;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.merkle.oss.magnolia.definition.custom.switchable.SingleForm;
import com.merkle.oss.magnolia.definition.key.generator.configuration.ExcludedAncestors;

public class SwitchableGuiceModule implements Module {
	@Override
	public void configure(final Binder binder) {
		final Multibinder<Class<?>> excludedAncestorsMultibinder = Multibinder.newSetBinder(binder, new TypeLiteral<Class<?>>(){}, ExcludedAncestors.class);
		excludedAncestorsMultibinder.addBinding().toInstance(SingleForm.class);
	}
}
