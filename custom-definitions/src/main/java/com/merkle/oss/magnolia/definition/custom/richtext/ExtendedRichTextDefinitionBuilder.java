package com.merkle.oss.magnolia.definition.custom.richtext;

import com.merkle.oss.magnolia.definition.builder.simple.AbstractRichTextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExtendedRichTextDefinitionBuilder extends AbstractRichTextFieldDefinitionBuilder<ExtendedRichTextDefinition, ExtendedRichTextDefinitionBuilder> {
	@Nullable
	private String formatTags;
	@Nullable
	private String customStyleSet;
	@Nullable
	private String customTemplates;
	@Nullable
	private String template;
	@Nullable
	private String enterMode;
	@Nullable
	private String contentCss;
	@Nullable
	private String extraAllowedContent;
	@Nullable
	private Boolean forcePasteAsPlainText;
	@Nullable
	private Boolean pasteFromWordRemoveFontStyles;
	@Nullable
	private Boolean pasteFromWordPromptCleanup;
	@Nullable
	private String bodyClass;
	@Nullable
	private RichTextToolbarConfig toolbarConfig;
	private final Map<String, String> extraConfig = new HashMap<>();
	private final Map<String, String> externalPlugins = new HashMap<>();

	public ExtendedRichTextDefinitionBuilder formatTags(final String formatTags) {
		this.formatTags = formatTags;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder customStyleSet(final String customStyleSet) {
		this.customStyleSet = customStyleSet;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder customTemplates(final String customTemplates) {
		this.customTemplates = customTemplates;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder template(final String template) {
		this.template = template;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder enterMode(final String enterMode) {
		this.enterMode = enterMode;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder extraAllowedContent(final String extraAllowedContent) {
		this.extraAllowedContent = extraAllowedContent;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder contentCss(final String contentCss) {
	    this.contentCss = contentCss;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder forcePasteAsPlainText(final boolean forcePasteAsPlainText) {
		this.forcePasteAsPlainText = forcePasteAsPlainText;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder pasteFromWordRemoveFontStyles(final boolean pasteFromWordRemoveFontStyles) {
		this.pasteFromWordRemoveFontStyles = pasteFromWordRemoveFontStyles;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder pasteFromWordPromptCleanup(final boolean pasteFromWordPromptCleanup) {
		this.pasteFromWordPromptCleanup = pasteFromWordPromptCleanup;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder bodyClass(final String bodyClass) {
		this.bodyClass = bodyClass;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder toolbarConfig(final RichTextToolbarConfig toolbarConfig) {
		this.toolbarConfig = toolbarConfig;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder externalPlugin(final String name, final String source) {
		this.externalPlugins.put(name, source);
		return self();
	}

	public ExtendedRichTextDefinitionBuilder extraConfig(final String name, final String value) {
		this.extraConfig.put(name, value);
		return self();
	}

	public ExtendedRichTextDefinition build(final String name) {
		final ExtendedRichTextDefinition definition = new ExtendedRichTextDefinition();
		super.populate(definition, name);
		Optional.ofNullable(formatTags).ifPresent(definition::setFormatTags);
		Optional.ofNullable(customStyleSet).ifPresent(definition::setCustomStyleSet);
		Optional.ofNullable(customTemplates).ifPresent(definition::setCustomTemplates);
		Optional.ofNullable(template).ifPresent(definition::setTemplate);
		Optional.ofNullable(enterMode).ifPresent(definition::setEnterMode);
		Optional.ofNullable(contentCss).ifPresent(definition::setContentCss);
		Optional.ofNullable(extraAllowedContent).ifPresent(definition::setExtraAllowedContent);
		Optional.ofNullable(forcePasteAsPlainText).ifPresent(definition::setForcePasteAsPlainText);
		Optional.ofNullable(pasteFromWordPromptCleanup).ifPresent(definition::setPasteFromWordPromptCleanup);
		Optional.ofNullable(pasteFromWordRemoveFontStyles).ifPresent(definition::setPasteFromWordRemoveFontStyles);
		Optional.ofNullable(bodyClass).ifPresent(definition::setBodyClass);
		Optional.ofNullable(toolbarConfig).ifPresent(definition::setToolbarConfig);
		definition.setExternalPlugins(externalPlugins);
		definition.setExtraConfig(extraConfig);
		return definition;
	}
}
