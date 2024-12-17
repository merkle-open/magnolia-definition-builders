package com.merkle.oss.magnolia.definition.custom.richtext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbar;

class ExtendedRichTextDefinitionBuilderTest {

	@Test
	void testBuilder() {
		final RichTextToolbar richTextToolbar = RichTextToolbar.builder().build();
		final ExtendedRichTextDefinition definition = new ExtendedRichTextDefinitionBuilder()
				.headings(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_1))
				.heading(HeadingOption.HEADING_2)
				.toolbarConfig(richTextToolbar)
				.build("extendedRichText");

		assertEquals(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_1, HeadingOption.HEADING_2), definition.getHeadings());
		assertEquals(Optional.of(richTextToolbar), definition.getToolbarConfig());
	}
}