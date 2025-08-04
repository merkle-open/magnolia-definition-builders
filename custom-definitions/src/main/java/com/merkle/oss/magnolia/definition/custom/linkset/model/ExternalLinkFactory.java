package com.merkle.oss.magnolia.definition.custom.linkset.model;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkTypes;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public class ExternalLinkFactory implements LinkModelFactory.LinkFactory {

	@Override
	public boolean test(final LinkType linkType) {
		return LinkTypes.EXTERNAL.equals(linkType);
	}

	@Override
	public Optional<Link> create(final Locale locale, final Locale dialogLocale, final PowerNode node, final String propertyName) {
		return node.getProperty(propertyName, dialogLocale, ValueConverter::getString)
				.map(href ->
						new LinkModel(
								getText(() -> node.getProperty(LinkSetDefinitionBuilder.LINK_TEXT_PROPERTY_NAME_PROVIDER.apply(propertyName), dialogLocale, ValueConverter::getString), locale, href),
								href,
								href,
								node.getProperty(LinkSetDefinitionBuilder.OPEN_IN_NEW_TAB_PROPERTY_NAME_PROVIDER.apply(propertyName), ValueConverter::getBoolean).orElse(true),
								true,
								LinkTypes.EXTERNAL
						)
				);
	}

	// dialogTitle -> href
	protected String getText(final Supplier<Optional<String>> dialogTextSupplier, final Locale locale, final String href) {
		return dialogTextSupplier.get().orElse(href);
	}
}
