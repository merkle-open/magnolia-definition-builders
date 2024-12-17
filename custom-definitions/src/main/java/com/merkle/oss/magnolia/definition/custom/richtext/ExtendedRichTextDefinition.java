package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.dam.app.field.DamRichTextFieldDefinition;
import info.magnolia.pages.app.field.PageLinkFieldDefinition;
import info.magnolia.repository.RepositoryConstants;
import info.magnolia.ui.field.LinkFieldDefinition;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;

public class ExtendedRichTextDefinition extends DamRichTextFieldDefinition {
	@Nullable
	private RichTextToolbarConfig toolbarConfig;

	private List<HeadingOption> headings = List.of(HeadingOption.values());

	public ExtendedRichTextDefinition() {
		setFactoryClass(ExtendedRichTextFactory.class);
		final Map<String, LinkFieldDefinition<?>> linkFieldDefinitions = getLinkFieldDefinitions();
		final PageLinkFieldDefinition internalLinkFieldDefinition = new PageLinkFieldDefinition();
		linkFieldDefinitions.put(RepositoryConstants.WEBSITE, internalLinkFieldDefinition);
		this.setLinkFieldDefinitions(linkFieldDefinitions);
	}

	public Optional<RichTextToolbarConfig> getToolbarConfig() {
		return Optional.ofNullable(toolbarConfig);
	}

	public void setToolbarConfig(RichTextToolbarConfig toolbarConfig) {
		this.toolbarConfig = toolbarConfig;
	}

	public List<HeadingOption> getHeadings() {
		return headings;
	}

	public void setHeadings(final List<HeadingOption> headings) {
		this.headings = headings;
	}
}
