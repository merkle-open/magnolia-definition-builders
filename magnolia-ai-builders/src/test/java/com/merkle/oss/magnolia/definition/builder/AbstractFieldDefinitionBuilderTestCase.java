package com.merkle.oss.magnolia.definition.builder;

import static org.junit.jupiter.api.Assertions.*;

import info.magnolia.ui.editor.ItemProviderDefinition;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;

import java.util.function.BiFunction;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;

public abstract class AbstractFieldDefinitionBuilderTestCase {

	public <T, D extends ConfiguredComplexPropertyDefinition<T>, B extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, D, B>> B assertComplexField(final B builder, final BiFunction<String, B, D> buildFunction, final ItemProviderDefinition<T, T> itemProvider) {
		final ConfiguredComplexPropertyDefinition<T> definition = buildFunction.apply(
				"someName",
				builder
						.label("someLable")
						.description("someDescription")
						.i18n(true)
						.styleName("someStyleName")
						.itemProvider(itemProvider)
		);
		assertEquals("someLable", definition.getLabel());
		assertEquals("someDescription", definition.getDescription());
		assertTrue(definition.isI18n());
		assertEquals("someStyleName", definition.getStyleName());
		assertEquals(itemProvider, definition.getItemProvider());
		return builder;
	}

	public <T, D extends ConfiguredComplexPropertyDefinition<T>, B extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, D, B>> B assertComplexField(final B builder, final TriFunction<String, String, B, D> buildFunction) {
		final ConfiguredComplexPropertyDefinition<T> definition = buildFunction.apply(
				"someName",
				"someText",
				builder
						.label("someLable")
						.description("someDescription")
						.i18n(true)
						.styleName("someStyleName")
		);
		assertEquals("someLable", definition.getLabel());
		assertEquals("someDescription", definition.getDescription());
		assertTrue(definition.isI18n());
		assertEquals("someStyleName", definition.getStyleName());
		return builder;
	}

	@FunctionalInterface
	public interface TriFunction<T, U, V, R> {
		R apply(T t, U u, V v);
	}
}
