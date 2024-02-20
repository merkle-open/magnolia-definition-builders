package com.merkle.oss.magnolia.definition.custom.richtext;

import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;
import info.magnolia.ui.field.RichTextFieldDefinition;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class ExtendedRichTextDefinition extends RichTextFieldDefinition {
	@Nullable
	private String formatTags;
	@Nullable
	private String customStyleSet;
	@Nullable
	private String customTemplates;
	@Nullable
	private String template;
	@Nullable
	private String extraAllowedContent;
	@Nullable
	private String contentCss;
	@Nullable
	private String enterMode;
	private boolean forcePasteAsPlainText;
	private boolean pasteFromWordRemoveFontStyles;
	private boolean pasteFromWordPromptCleanup;
	@Nullable
	private String bodyClass;
	@Nullable
	private RichTextToolbarConfig toolbarConfig;
	private Map<String, String> extraConfig = Collections.emptyMap();
	private Map<String, String> externalPlugins = Collections.emptyMap();

	public ExtendedRichTextDefinition() {
		setFactoryClass(ExtendedRichTextFactory.class);
	}

	public Optional<RichTextToolbarConfig> getToolbarConfig() {
		return Optional.ofNullable(toolbarConfig);
	}

	public void setToolbarConfig(RichTextToolbarConfig toolbarConfig) {
		this.toolbarConfig = toolbarConfig;
	}

	public Optional<String> getFormatTags() {
		return Optional.ofNullable(formatTags);
	}

	public void setFormatTags(final String formatTags) {
		this.formatTags = formatTags;
	}

	public Optional<String> getCustomStyleSet() {
		return Optional.ofNullable(customStyleSet);
	}

	public void setCustomStyleSet(final String customStyleSet) {
		this.customStyleSet = customStyleSet;
	}

	public Optional<String> getCustomTemplates() {
		return Optional.ofNullable(customTemplates);
	}

	public void setCustomTemplates(final String customTemplates) {
		this.customTemplates = customTemplates;
	}

	public Optional<String> getTemplate() {
		return Optional.ofNullable(template);
	}

	public void setTemplate(final String template) {
		this.template = template;
	}

	public Optional<String> getExtraAllowedContent() {
		return Optional.ofNullable(extraAllowedContent);
	}

	public void setExtraAllowedContent(final String extraAllowedContent) {
		this.extraAllowedContent = extraAllowedContent;
	}

	public Optional<String> getContentCss() {
		return Optional.ofNullable(contentCss);
	}

	public void setContentCss(final String contentCss) {
		this.contentCss = contentCss;
	}

	public Optional<String> getEnterMode() {
		return Optional.ofNullable(enterMode);
	}

	public void setEnterMode(final String enterMode) {
		this.enterMode = enterMode;
	}

	public boolean isForcePasteAsPlainText() {
		return forcePasteAsPlainText;
	}

	public void setForcePasteAsPlainText(final boolean forcePasteAsPlainText) {
		this.forcePasteAsPlainText = forcePasteAsPlainText;
	}

	public boolean isPasteFromWordRemoveFontStyles() {
		return pasteFromWordRemoveFontStyles;
	}

	public void setPasteFromWordRemoveFontStyles(boolean pasteFromWordRemoveFontStyles) {
		this.pasteFromWordRemoveFontStyles = pasteFromWordRemoveFontStyles;
	}

	public boolean isPasteFromWordPromptCleanup() {
		return pasteFromWordPromptCleanup;
	}

	public void setPasteFromWordPromptCleanup(boolean pasteFromWordPromptCleanup) {
		this.pasteFromWordPromptCleanup = pasteFromWordPromptCleanup;
	}

	public Optional<String> getBodyClass() {
		return Optional.ofNullable(bodyClass);
	}

	public void setBodyClass(final String bodyClass) {
		this.bodyClass = bodyClass;
	}

	public void setExtraConfig(final Map<String, String> extraConfig) {
		this.extraConfig = extraConfig;
	}

	public Map<String, String> getExtraConfig() {
		return extraConfig;
	}

	public void setExternalPlugins(final Map<String, String> externalPlugins) {
		this.externalPlugins = externalPlugins;
	}

	public Map<String, String> getExternalPlugins() {
		return externalPlugins;
	}
}
