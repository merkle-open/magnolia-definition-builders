package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class OptionListDefinitionBuilderTest {

	@Test
	void testBuilder() {
		final Option option1 = mock(Option.class);
		final Option option2 = mock(Option.class);
		final Option option3 = mock(Option.class);

		final OptionListDefinition definition = new OptionListDefinitionBuilder()
				.options(List.of(option1, option2))
				.option(option3)
				.sort(true)
				.name("customName")
				.build();

		assertEquals("customName", definition.getName());
		assertEquals(List.of(option1, option2, option3), definition.getOptions());
		assertTrue(definition.isSort());

		final OptionListDefinition emptyDefinition = new OptionListDefinitionBuilder().build();
		assertEquals("optionlist", emptyDefinition.getName());
	}
}
