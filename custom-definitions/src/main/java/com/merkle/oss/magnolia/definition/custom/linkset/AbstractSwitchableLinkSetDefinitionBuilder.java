package com.merkle.oss.magnolia.definition.custom.linkset;

import info.magnolia.ui.editor.JcrChildNodeProviderDefinition;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.switchable.AbstractSwitchableDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.PrefixFormNameExceptFieldPropertyNameDecorator;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;

public abstract class AbstractSwitchableLinkSetDefinitionBuilder<B extends AbstractSwitchableLinkSetDefinitionBuilder<B>> extends AbstractSwitchableDefinitionBuilder<LinkType, B> {
	public static final String LINK_TYPE_PROPERTY = "link";

	@Nullable
	private List<LinkType> linkOptions;

	protected AbstractSwitchableLinkSetDefinitionBuilder(final String labelPrefix, final boolean switchableFieldI18n) {
		super(labelPrefix, switchableFieldI18n);
		final JcrChildNodeProviderDefinition childNodeProvider = new JcrChildNodeProviderDefinition();
		childNodeProvider.setSupportI18N(false);
		itemProvider(childNodeProvider);
		propertyNameDecorator(PrefixFormNameExceptFieldPropertyNameDecorator.class);
		optionPropertyNameDecorator(ignored -> LINK_TYPE_PROPERTY);
		linkOptions(List.of(LinkTypes.values()));
	}

	protected abstract FieldOption<LinkType> createFieldOption(LinkType linkType);

	public B linkOption(final LinkType linkOption) {
		return linkOptions(Stream.concat(
				Stream.ofNullable(linkOptions).flatMap(Collection::stream),
				Stream.of(linkOption)
		).collect(Collectors.toList()));
	}

	public B linkOptions(final LinkType... linkOptions) {
		return linkOptions(List.of(linkOptions));
	}

	public B linkOptions(final List<LinkType> linkOptions) {
		this.linkOptions = linkOptions;
		return self();
	}

	@Override
	public SwitchableDefinition build(final String name) {
		fieldOptions(Stream.ofNullable(linkOptions).flatMap(Collection::stream).map(this::createFieldOption).collect(Collectors.toList()));
		final SwitchableDefinition definition = super.build(name);
		return definition;
	}
}
