package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.optionlist.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionBuilderTest {

	@Test
	void testBuilder() {
		final Option definition = new OptionBuilder()
				.label("label")
				.iconSrc("iconSrc")
				.build("option", "value");

		assertEquals("label", definition.getLabel());
		assertEquals("iconSrc", definition.getIconSrc());
		assertEquals("option", definition.getName());
		assertEquals("value", definition.getValue());
	}
}
