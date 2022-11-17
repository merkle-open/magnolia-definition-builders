package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.optionlist.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionBuilderTest {

	private static final String NAME = "name";
	private static final String VALUE = "value";
	private static final String LABEL = "label";
	private static final String ICON_SRC = "iconSrc";

	@Test
	public void testOptionBuilder() {
		Option option = new OptionBuilder()
				.build(NAME, VALUE);
		assertNotNull(option);
		assertEquals(NAME, option.getName());
		assertEquals(VALUE, option.getValue());
		assertNull(option.getIconSrc());
		assertNull(option.getLabel());
	}

	@Test
	public void testOptionBuilderLabel() {
		Option option = new OptionBuilder()
				.label(LABEL)
				.build(NAME, VALUE);
		assertNotNull(option);
		assertEquals(NAME, option.getName());
		assertEquals(VALUE, option.getValue());
		assertEquals(LABEL, option.getLabel());
		assertNull(option.getIconSrc());
	}

	@Test
	public void testOptionBuilderIconSrc() {
		Option option = new OptionBuilder()
				.iconSrc(ICON_SRC)
				.build(NAME, VALUE);
		assertNotNull(option);
		assertEquals(NAME, option.getName());
		assertEquals(VALUE, option.getValue());
		assertEquals(ICON_SRC, option.getIconSrc());
		assertNull(option.getLabel());
	}

	@Test
	public void testOptionBuilderComplete() {
		Option option = new OptionBuilder()
				.iconSrc(ICON_SRC)
				.label(LABEL)
				.build(NAME, VALUE);
		assertNotNull(option);
		assertEquals(NAME, option.getName());
		assertEquals(VALUE, option.getValue());
		assertEquals(ICON_SRC, option.getIconSrc());
		assertEquals(LABEL, option.getLabel());
	}
}
