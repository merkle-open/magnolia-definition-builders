package com.merkle.oss.magnolia.definition.custom.richtext;

import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbar;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtendedRichTextDefinitionBuilderTest {

	@Test
	void testBuilder() {
		final RichTextToolbar richTextToolbar = RichTextToolbar.builder().build();
		final ExtendedRichTextDefinition definition = new ExtendedRichTextDefinitionBuilder()
				.formatTags("formatTags")
				.customStyleSet("customStyleSet")
				.customTemplates("customTemplates")
				.template("template")
				.enterMode("enterMode")
				.contentCss("contentCss")
				.extraAllowedContent("extraAllowedContent")
				.forcePasteAsPlainText(true)
				.pasteFromWordPromptCleanup(true)
				.pasteFromWordRemoveFontStyles(true)
				.bodyClass("bodyClass")
				.toolbarConfig(richTextToolbar)
				.externalPlugin("plugin1", "source1")
				.externalPlugin("plugin2", "source2")
				.extraConfig("config1", "value1")
				.extraConfig("config2", "value2")
				.build("extendedRichText");

		assertEquals(Optional.of("formatTags"), definition.getFormatTags());
		assertEquals(Optional.of("customStyleSet"), definition.getCustomStyleSet());
		assertEquals(Optional.of("customTemplates"), definition.getCustomTemplates());
		assertEquals(Optional.of("template"), definition.getTemplate());
		assertEquals(Optional.of("enterMode"), definition.getEnterMode());
		assertEquals(Optional.of("contentCss"), definition.getContentCss());
		assertEquals(Optional.of("extraAllowedContent"), definition.getExtraAllowedContent());
		assertTrue(definition.isForcePasteAsPlainText());
		assertTrue(definition.isPasteFromWordPromptCleanup());
		assertTrue(definition.isPasteFromWordRemoveFontStyles());
		assertEquals(Optional.of("bodyClass"), definition.getBodyClass());
		assertEquals(Optional.of(richTextToolbar), definition.getToolbarConfig());
		assertEquals(Map.of("plugin1", "source1", "plugin2", "source2"), definition.getExternalPlugins());
		assertEquals(Map.of("config1", "value1", "config2", "value2"), definition.getExtraConfig());
	}
}