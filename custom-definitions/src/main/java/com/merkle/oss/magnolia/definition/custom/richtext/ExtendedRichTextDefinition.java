package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.dam.app.field.DamRichTextFieldDefinition;
import info.magnolia.pages.app.field.PageLinkFieldDefinition;
import info.magnolia.repository.RepositoryConstants;
import info.magnolia.ui.field.LinkFieldDefinition;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;

public class ExtendedRichTextDefinition extends DamRichTextFieldDefinition {
	@Nullable
	private ToolbarConfig toolbarConfig;
	@Nullable
	private LinkConfig linkConfig;
	@Nullable
	private MgnlLinkConfig mgnlLinkConfig;

	@Nullable
	private HtmlSupport htmlSupport;
	private List<HeadingOption> headings = List.of(
			HeadingOption.PARAGRAPH,
			HeadingOption.HEADING_1,
			HeadingOption.HEADING_2,
			HeadingOption.HEADING_3,
			HeadingOption.HEADING_4,
			HeadingOption.HEADING_5,
			HeadingOption.HEADING_6
	);

    public ExtendedRichTextDefinition() {
		setFactoryClass(ExtendedRichTextFactory.class);
		final Map<String, LinkFieldDefinition<?>> linkFieldDefinitions = getLinkFieldDefinitions();
		final PageLinkFieldDefinition internalLinkFieldDefinition = new PageLinkFieldDefinition();
		linkFieldDefinitions.put(RepositoryConstants.WEBSITE, internalLinkFieldDefinition);
		this.setLinkFieldDefinitions(linkFieldDefinitions);
	}

	public Optional<ToolbarConfig> getToolbarConfig() {
		return Optional.ofNullable(toolbarConfig);
	}
	public void setToolbarConfig(final ToolbarConfig toolbarConfig) {
		this.toolbarConfig = toolbarConfig;
	}

	public Optional<LinkConfig> getLinkConfig() {
		return Optional.ofNullable(linkConfig);
	}
	public void setLinkConfig(final LinkConfig linkConfig) {
		this.linkConfig = linkConfig;
	}

	public Optional<MgnlLinkConfig> getMgnlLinkConfig() {
		return Optional.ofNullable(mgnlLinkConfig);
	}
	public void setMgnlLinkConfig(final MgnlLinkConfig mgnlLinkConfig) {
		this.mgnlLinkConfig = mgnlLinkConfig;
	}

	public List<HeadingOption> getHeadings() {
		return headings;
	}
	public void setHeadings(final List<HeadingOption> headings) {
		this.headings = headings;
	}

    public Optional<HtmlSupport> getHtmlSupport() {
        return Optional.ofNullable(htmlSupport);
    }
    public void setHtmlSupport(final HtmlSupport htmlSupport) {
        this.htmlSupport = htmlSupport;
    }
}
