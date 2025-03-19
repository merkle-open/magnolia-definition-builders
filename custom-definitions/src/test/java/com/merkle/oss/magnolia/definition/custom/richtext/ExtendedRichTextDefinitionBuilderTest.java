package com.merkle.oss.magnolia.definition.custom.richtext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbar;

class ExtendedRichTextDefinitionBuilderTest {

	@Test
	void testBuilder() {
		final RichTextToolbar richTextToolbar = RichTextToolbar.builder().build();
		final LinkConfig linkConfig = new LinkConfig.Builder().build();
		final HtmlSupport htmlSupport = new HtmlSupport.Builder().build();
		final ExtendedRichTextDefinition definition = new ExtendedRichTextDefinitionBuilder()
				.headings(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_1))
				.heading(HeadingOption.HEADING_2)
				.toolbarConfig(richTextToolbar)
				.linkConfig(linkConfig)
				.htmlSupport(htmlSupport)
				.build("extendedRichText");

		assertEquals(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_1, HeadingOption.HEADING_2), definition.getHeadings());
		assertEquals(Optional.of(richTextToolbar), definition.getToolbarConfig());
		assertEquals(Optional.of(linkConfig), definition.getLinkConfig());
		assertEquals(Optional.of(htmlSupport), definition.getHtmlSupport());
	}
}
