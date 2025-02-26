package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.ui.field.LinkFieldDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.simple.AbstractConfiguredFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.AbstractToolbarGroupBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.ImageGroupBuilder;

public class ExtendedRichTextDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<String, ExtendedRichTextDefinition, ExtendedRichTextDefinitionBuilder> {
	@Nullable
	private RichTextToolbarConfig toolbarConfig;
	@Nullable
	private LinkConfig linkConfig;
	@Nullable
	private List<HeadingOption> headings;
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

	public ExtendedRichTextDefinitionBuilder toolbarConfig(final RichTextToolbarConfig toolbarConfig) {
		this.toolbarConfig = toolbarConfig;
		return self();
	}

	public ExtendedRichTextDefinitionBuilder linkConfig(final LinkConfig linkConfig) {
		this.linkConfig = linkConfig;
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
		Optional.ofNullable(headings).ifPresent(definition::setHeadings);
		definition.setImages(contains(new ImageGroupBuilder()));
		definition.setLists(contains(new ImageGroupBuilder()));
		Stream.ofNullable(linkFieldDefinitions).map(Map::entrySet).flatMap(Collection::stream).forEach(entry ->
				definition.getLinkFieldDefinitions().put(entry.getKey(), entry.getValue())
		);
		Optional.ofNullable(height).ifPresent(definition::setHeight);
		return definition;
	}

	private boolean contains(final AbstractToolbarGroupBuilder<?> toolbarGroupBuilder) {
		return Optional.ofNullable(toolbarConfig)
				.map(RichTextToolbarConfig::getConfig)
				.stream().flatMap(Collection::parallelStream)
				.anyMatch(toolbarGroup ->
						Objects.equals(toolbarGroup.getName(), toolbarGroupBuilder.getLabel())
				);
	}
}
