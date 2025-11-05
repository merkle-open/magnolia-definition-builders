package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.ui.field.LinkFieldDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.simple.AbstractConfiguredFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontColor;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontFamily;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontSize;
import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.table.TableConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.ImageToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.ListToolbarConfigItem;

public class ExtendedRichTextDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<String, ExtendedRichTextDefinition, ExtendedRichTextDefinitionBuilder> {
	@Nullable
	private ToolbarConfig toolbarConfig;
	@Nullable
	private LinkConfig linkConfig;
	@Nullable
	private MgnlLinkConfig mgnlLinkConfig;
    @Nullable
    private TableConfig tableConfig;
	@Nullable
	private HtmlSupport htmlSupport;
	@Nullable
	private List<HeadingOption> headings;
    @Nullable
    private FontFamily fontFamily;
    @Nullable
    private FontSize fontSize;
    @Nullable
    private FontColor fontColor;
	@Nullable
	private Map<String, LinkFieldDefinition<?>> linkFieldDefinitions;
	@Nullable
	private Long height;

	public ExtendedRichTextDefinitionBuilder() {}
	public ExtendedRichTextDefinitionBuilder(final ExtendedRichTextDefinition definition) {
		super(definition);
		definition.getToolbarConfig().ifPresent(this::toolbarConfig);
		headings(definition.getHeadings());
		linkFieldDefinitions(definition.getLinkFieldDefinitions());
		height(definition.getHeight());
	}

	public ExtendedRichTextDefinitionBuilder toolbarConfig(final ToolbarConfig toolbarConfig) {
		this.toolbarConfig = toolbarConfig;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder linkConfig(final LinkConfig linkConfig) {
		this.linkConfig = linkConfig;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder mgnlLinkConfig(final MgnlLinkConfig mgnlLinkConfig) {
		this.mgnlLinkConfig = mgnlLinkConfig;
		return self();
	}

    public ExtendedRichTextDefinitionBuilder tableConfig(final TableConfig tableConfig) {
        this.tableConfig = tableConfig;
        return self();
    }

	public ExtendedRichTextDefinitionBuilder htmlSupport(final HtmlSupport htmlSupport) {
		this.htmlSupport = htmlSupport;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder heading(final HeadingOption heading) {
		return headings(Stream.concat(
				Stream.ofNullable(headings).flatMap(Collection::stream),
				Stream.of(heading)
		).collect(Collectors.toList()));
	}

	public ExtendedRichTextDefinitionBuilder headings(final List<HeadingOption> headings) {
		this.headings = headings;
		return self();
	}

    public ExtendedRichTextDefinitionBuilder fontFamily(final FontFamily fontFamily) {
        this.fontFamily = fontFamily;
        return self();
    }

    public ExtendedRichTextDefinitionBuilder fontSize(final FontSize fontSize) {
        this.fontSize = fontSize;
        return self();
    }

    public ExtendedRichTextDefinitionBuilder fontColor(final FontColor fontColor) {
        this.fontColor = fontColor;
        return self();
    }

	public ExtendedRichTextDefinitionBuilder linkFieldDefinition(final String key, final LinkFieldDefinition<?> value) {
		return linkFieldDefinitions(Stream.concat(
				Stream.ofNullable(linkFieldDefinitions).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(key, value))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}

	public ExtendedRichTextDefinitionBuilder linkFieldDefinitions(final Map<String, LinkFieldDefinition<?>> linkFieldDefinitions) {
		this.linkFieldDefinitions = linkFieldDefinitions;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder height(final long height) {
		this.height = height;
		return self();
	}

	public ExtendedRichTextDefinition build(final String name) {
		final ExtendedRichTextDefinition definition = new ExtendedRichTextDefinition();
		super.populate(definition, name);
		Optional.ofNullable(toolbarConfig).ifPresent(definition::setToolbarConfig);
		Optional.ofNullable(linkConfig).ifPresent(definition::setLinkConfig);
		Optional.ofNullable(tableConfig).ifPresent(definition::setTableConfig);
		Optional.ofNullable(mgnlLinkConfig).ifPresent(definition::setMgnlLinkConfig);
		Optional.ofNullable(htmlSupport).ifPresent(definition::setHtmlSupport);
		Optional.ofNullable(headings).ifPresent(definition::setHeadings);
		Optional.ofNullable(fontFamily).ifPresent(definition::setFontFamily);
		Optional.ofNullable(fontSize).ifPresent(definition::setFontSize);
		Optional.ofNullable(fontColor).ifPresent(definition::setFontColor);
		definition.setImages(contains(new ImageToolbarConfigItem.Builder().image().build()));
		definition.setLists(contains(new ListToolbarConfigItem.Builder().bulletedList().numberedList().todoList().build()));
		Stream.ofNullable(linkFieldDefinitions).map(Map::entrySet).flatMap(Collection::stream).forEach(entry ->
				definition.getLinkFieldDefinitions().put(entry.getKey(), entry.getValue())
		);
		Optional.ofNullable(height).ifPresent(definition::setHeight);
		return definition;
	}

	private boolean contains(final ToolbarConfigItem.NestedToolbarConfigItem toolbarConfigItem) {
        return toolbarConfigItem.streamValues().anyMatch(this::contains);
    }
	private boolean contains(final String toolbarConfigItem) {
		return Optional.ofNullable(toolbarConfig)
                .map(toolbar -> toolbar.containsToolbarConfigItem(toolbarConfigItem))
                .orElse(false);
	}
}
