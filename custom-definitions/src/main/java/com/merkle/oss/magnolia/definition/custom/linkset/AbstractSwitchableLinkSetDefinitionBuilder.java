package com.merkle.oss.magnolia.definition.custom.linkset;

import com.merkle.oss.magnolia.definition.custom.switchable.AbstractSwitchableDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;
import info.magnolia.ui.editor.JcrChildNodeProviderDefinition;
import info.magnolia.ui.field.NoopNameDecorator;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractSwitchableLinkSetDefinitionBuilder<B extends AbstractSwitchableLinkSetDefinitionBuilder<B>> extends AbstractSwitchableDefinitionBuilder<LinkType, B> {
	public static final String LINK_TYPE_PROPERTY = "link";

	@Nullable
	private List<LinkType> linkOptions;

	protected AbstractSwitchableLinkSetDefinitionBuilder(final String labelPrefix) {
		super(labelPrefix);
		final JcrChildNodeProviderDefinition childNodeProvider = new JcrChildNodeProviderDefinition();
		childNodeProvider.setSupportI18N(false);
		itemProvider(childNodeProvider);
		propertyNameDecorator(NoopNameDecorator.class);
		optionPropertyNameDecorator(ignored -> LINK_TYPE_PROPERTY);
		linkOptions(List.of(LinkTypes.values()));
	}

	protected abstract FieldOption<LinkType> createFieldOption(LinkType linkType);

	public B linkOption(final LinkType imageOption) {
		return linkOptions(Stream.concat(
				Stream.ofNullable(linkOptions).flatMap(Collection::stream),
				Stream.of(imageOption)
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
