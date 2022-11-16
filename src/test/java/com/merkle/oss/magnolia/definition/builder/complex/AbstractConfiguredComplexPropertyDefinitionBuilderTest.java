package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.editor.ItemProviderDefinition;
import info.magnolia.ui.field.StaticFieldViewDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractConfiguredComplexPropertyDefinitionBuilderTest {

	private static final String LABEL = "label";
	private static final String DESCRIPTION = "description";
	private static final String STYLE_NAME = "styleName";
	private ItemProviderDefinition itemProviderDefinition;

	protected AbstractConfiguredComplexPropertyDefinitionBuilder setup(AbstractConfiguredComplexPropertyDefinitionBuilder builder) {
		itemProviderDefinition = new ItemProviderDefinition<>();
		return builder
				.label(LABEL)
				.i18n()
				.description(DESCRIPTION)
				.styleName(STYLE_NAME)
				.itemProvider(itemProviderDefinition);
	}

	protected AbstractConfiguredComplexPropertyDefinitionBuilder setupBooleanValues(AbstractConfiguredComplexPropertyDefinitionBuilder builder, boolean i18n) {
		return builder.i18n(i18n);
	}

	protected void testAbstractConfiguredComplexPropertyDefinitionBuilder(StaticFieldViewDefinition fieldViewDefinition) {
		assertEquals(LABEL, fieldViewDefinition.getLabel());
		assertEquals(DESCRIPTION, fieldViewDefinition.getDescription());
		assertEquals(STYLE_NAME, fieldViewDefinition.getStyleName());
		assertEquals(itemProviderDefinition, fieldViewDefinition.getItemProvider());
		assertTrue(fieldViewDefinition.isI18n());
	}

	protected void testBooleanValues(StaticFieldViewDefinition fieldViewDefinition, boolean i18n) {
		assertEquals(i18n, fieldViewDefinition.isI18n());
	}
}
