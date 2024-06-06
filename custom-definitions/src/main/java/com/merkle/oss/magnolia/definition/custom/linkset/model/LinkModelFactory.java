package com.merkle.oss.magnolia.definition.custom.linkset.model;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javax.inject.Inject;

import org.apache.commons.lang.NotImplementedException;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;
import com.merkle.oss.magnolia.definition.custom.linkset.model.util.ExtendedLinkAnchorModifier;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;

public class LinkModelFactory {
	private final LocaleProvider localeProvider;
	private final ExtendedLinkAnchorModifier extendedLinkAnchorModifier;
	private final Set<LinkType.Resolver> linkTypeResolvers;
	private final Set<LinkFactory> linkFactories;
	private final boolean switchableFieldI18n;

	@Inject
	public LinkModelFactory(
			final LocaleProvider localeProvider,
			final ExtendedLinkAnchorModifier extendedLinkAnchorModifier,
			final Set<LinkType.Resolver> linkTypeResolvers,
			final Set<LinkFactory> linkFactories
	) {
		this(localeProvider, extendedLinkAnchorModifier, linkTypeResolvers, linkFactories, true);
	}

	protected LinkModelFactory(
			final LocaleProvider localeProvider,
			final ExtendedLinkAnchorModifier extendedLinkAnchorModifier,
			final Set<LinkType.Resolver> linkTypeResolvers,
			final Set<LinkFactory> linkFactories,
			final boolean switchableFieldI18n
	) {
		this.localeProvider = localeProvider;
		this.extendedLinkAnchorModifier = extendedLinkAnchorModifier;
		this.linkTypeResolvers = linkTypeResolvers;
		this.linkFactories = linkFactories;
		this.switchableFieldI18n = switchableFieldI18n;
	}

	public Optional<Link> create(final Locale locale, final PowerNode node, final String propertyName) {
		return create(
				locale,
				localeProvider.getDefaultLocale(node),
				node,
				propertyName
		);
	}

	public Optional<Link> create(final Locale locale, final Locale dialogLocale, final PowerNode node, final String propertyName) {
		return node
				.getChild(propertyName)
				.flatMap(linkNode ->
						linkNode.getProperty(LinkSetDefinitionBuilder.LINK_TYPE_PROPERTY, switchableFieldI18n ? dialogLocale : localeProvider.getDefaultLocale(node), ValueConverter::getString).flatMap(this::resolve).flatMap(linkType ->
								create(locale, isSingleTree(linkType) ? localeProvider.getDefaultLocale(node) : dialogLocale, linkNode, linkType)
						)
				);
	}

	private Optional<Link> create(final Locale locale, final Locale dialogLocale, final PowerNode linkNode, final LinkType linkType) {
		return getLinkFactory(linkType).create(locale, dialogLocale, linkNode, linkType.getValue()).map(extendedLink ->
				wrapAnchor(extendedLink, dialogLocale, linkNode, linkType)
		);
	}

	protected boolean isSingleTree(final LinkType linkType) {
		return false;
	}

	private Optional<LinkType> resolve(final String type) {
		return linkTypeResolvers.stream()
				.map(resolver -> resolver.resolve(type))
				.flatMap(Optional::stream)
				.findAny();
	}

	private Link wrapAnchor(final Link extendedLink, final Locale dialogLocale, final PowerNode node, final LinkType linkType) {
		return node.getProperty(LinkSetDefinitionBuilder.ANCHOR_ID_PROPERTY_NAME_PROVIDER.apply(linkType.getValue()), dialogLocale, ValueConverter::getString)
				.map(anchorId ->
						extendedLinkAnchorModifier.with(extendedLink, anchorId)
				)
				.orElse(extendedLink);
	}

	private LinkFactory getLinkFactory(final LinkType linkType) {
		return linkFactories.stream()
				.filter(factory -> factory.test(linkType)).findFirst()
				.orElseThrow(() ->
						new NotImplementedException("No link factory configured for " + linkType)
				);
	}

	public interface LinkFactory extends Predicate<LinkType> {
		Optional<Link> create(Locale locale, Locale dialogLocale, PowerNode node, String propertyName);
	}
}