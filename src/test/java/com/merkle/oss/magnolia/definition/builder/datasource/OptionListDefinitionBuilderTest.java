package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionListDefinitionBuilderTest {

	private OptionListDefinitionBuilder builder;
	private Option option1, option2;

	@BeforeEach
	public void setup() {
		option1 = new Option();
		option2 = new Option();
		builder = new OptionListDefinitionBuilder()
				.sort(false);
	}

	@Test
	public void testBuilder() {
		OptionListDefinition definition = builder.build();
		assertFalse(definition.isSort());
		assertNotNull(definition.getOptions());
		assertTrue(definition.getOptions().isEmpty());
	}

	@Test
	public void testBuilderOption() {
		OptionListDefinition definition = builder
				.option(option1)
				.option(option2)
				.sort(true)
				.build();
		assertTrue(definition.isSort());
		assertNotNull(definition.getOptions());
		assertFalse(definition.getOptions().isEmpty());
		assertEquals(2, definition.getOptions().size());
		assertTrue(definition.getOptions().contains(option1));
		assertTrue(definition.getOptions().contains(option2));
	}

	@Test
	public void testBuilderOptions() {
		OptionListDefinition definition = builder
				.options(List.of(option1,option2))
				.sort(true)
				.build();
		assertTrue(definition.isSort());
		assertNotNull(definition.getOptions());
		assertFalse(definition.getOptions().isEmpty());
		assertEquals(2, definition.getOptions().size());
		assertTrue(definition.getOptions().contains(option1));
		assertTrue(definition.getOptions().contains(option2));
	}
}
