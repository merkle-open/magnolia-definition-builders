package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.LinkFieldDefinition;
import info.magnolia.ui.field.RichTextFieldDefinition;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * builds a {@link RichTextFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Rich-text-field.html">magnolia Docs - Rich text field </a>
 * @author Merkle DACH
 */
public abstract class AbstractRichTextFieldDefinitionBuilder<D extends RichTextFieldDefinition, B extends AbstractRichTextFieldDefinitionBuilder<D, B>> extends AbstractConfiguredFieldDefinitionBuilder<String, D, B> {
	@Nullable
	private Boolean alignment;
	@Nullable
	private Boolean images;
	@Nullable
	private Boolean lists;
	@Nullable
	private Boolean source;
	@Nullable
	private Boolean tables;
	@Nullable
	private Long height;
	@Nullable
	private String colors;
	@Nullable
	private String fonts;
	@Nullable
	private String fontSizes;
	@Nullable
	private String configJsFile;
	@Nullable
	private Map<String, LinkFieldDefinition<?>> linkFieldDefinitions;

	public B alignment(final boolean alignment) {
		this.alignment = alignment;
		return self();
	}

	public B images(final boolean images) {
		this.images = images;
		return self();
	}

	public B lists(final boolean lists) {
		this.lists = lists;
		return self();
	}

	public B source(final boolean source) {
		this.source = source;
		return self();
	}

	public B tables(final boolean tables) {
		this.tables = tables;
		return self();
	}

	public B height(final long height) {
		this.height = height;
		return self();
	}

	public B colors(final String colors) {
		this.colors = colors;
		return self();
	}

	public B fonts(final String fonts) {
		this.fonts = fonts;
		return self();
	}

	public B fontSizes(final String fontSizes) {
		this.fontSizes = fontSizes;
		return self();
	}

	public B configJsFile(final String configJsFile) {
		this.configJsFile = configJsFile;
		return self();
	}

	public B linkFieldDefinition(final String key, final LinkFieldDefinition<?> value) {
		return linkFieldDefinitions(Stream.concat(
				Stream.ofNullable(linkFieldDefinitions).map(Map::entrySet).flatMap(Collection::stream),
				Stream.of(Map.entry(key, value))
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}

	public B linkFieldDefinitions(final Map<String, LinkFieldDefinition<?>> linkFieldDefinitions) {
		this.linkFieldDefinitions = linkFieldDefinitions;
		return self();
	}

	@Override
	protected void populate(final D definition, final String name) {
		super.populate(definition, name);
		Optional.ofNullable(alignment).ifPresent(definition::setAlignment);
		Optional.ofNullable(images).ifPresent(definition::setImages);
		Optional.ofNullable(lists).ifPresent(definition::setLists);
		Optional.ofNullable(source).ifPresent(definition::setSource);
		Optional.ofNullable(tables).ifPresent(definition::setTables);
		Optional.ofNullable(height).ifPresent(definition::setHeight);
		Optional.ofNullable(colors).ifPresent(definition::setColors);
		Optional.ofNullable(fonts).ifPresent(definition::setFonts);
		Optional.ofNullable(fontSizes).ifPresent(definition::setFontSizes);
		Optional.ofNullable(configJsFile).ifPresent(definition::setConfigJsFile);
		Stream.ofNullable(linkFieldDefinitions).map(Map::entrySet).flatMap(Collection::stream).forEach(entry ->
				definition.getLinkFieldDefinitions().put(entry.getKey(), entry.getValue())
		);
	}
}
