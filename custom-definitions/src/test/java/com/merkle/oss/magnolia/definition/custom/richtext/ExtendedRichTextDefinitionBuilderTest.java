package com.merkle.oss.magnolia.definition.custom.richtext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;

class ExtendedRichTextDefinitionBuilderTest {

	@Test
	void testBuilder() {
		final ToolbarConfig toolbarConfig = new ToolbarConfig.Builder().build();
		final LinkConfig linkConfig = new LinkConfig.Builder().build();
		final MgnlLinkConfig mgnlLinkConfig = new MgnlLinkConfig.Builder().build();
		final HtmlSupport htmlSupport = new HtmlSupport.Builder().build();
		final ExtendedRichTextDefinition definition = new ExtendedRichTextDefinitionBuilder()
				.headings(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_1))
				.heading(HeadingOption.HEADING_2)
				.toolbarConfig(toolbarConfig)
				.linkConfig(linkConfig)
				.mgnlLinkConfig(mgnlLinkConfig)
				.htmlSupport(htmlSupport)
				.build("extendedRichText");

		assertEquals(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_1, HeadingOption.HEADING_2), definition.getHeadings());
		assertEquals(Optional.of(toolbarConfig), definition.getToolbarConfig());
		assertEquals(Optional.of(linkConfig), definition.getLinkConfig());
		assertEquals(Optional.of(mgnlLinkConfig), definition.getMgnlLinkConfig());
		assertEquals(Optional.of(htmlSupport), definition.getHtmlSupport());
	}
}
