package com.merkle.oss.magnolia.definition.custom.linkset.model;

import static info.magnolia.repository.RepositoryConstants.WEBSITE;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import com.merkle.oss.magnolia.definition.custom.configuration.LinkUtil;
import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkTypes;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.merkle.oss.magnolia.powernode.ValueConverter;

import jakarta.annotation.Nullable;
import jakarta.inject.Inject;

public class InternalLinkFactory implements LinkModelFactory.LinkFactory {
	private final PowerNodeService powerNodeService;
	private final LinkUtil linkUtil;
    private final LocaleProvider localeProvider;
    private final boolean singleTree;

    @Inject
	public InternalLinkFactory(
			final PowerNodeService powerNodeService,
			final LinkUtil linkUtil,
			final LocaleProvider localeProvider
	) {
		this(powerNodeService, linkUtil, localeProvider, false);
    }

	protected InternalLinkFactory(
			final PowerNodeService powerNodeService,
			final LinkUtil linkUtil,
			final LocaleProvider localeProvider,
			final boolean singleTree
	) {
		this.powerNodeService = powerNodeService;
		this.linkUtil = linkUtil;
		this.localeProvider = localeProvider;
        this.singleTree = singleTree;
    }

	@Override
	public boolean test(final LinkType linkType) {
		return LinkTypes.INTERNAL.equals(linkType);
	}

	@Override
	public Optional<Link> create(final Locale locale, final Locale dialogLocale, final PowerNode node, final String name) {
		return node.getProperty(name, singleTree ? localeProvider.getDefaultLocale(node) : dialogLocale, ValueConverter::getString)
				.flatMap(identifier ->
						powerNodeService.getByIdentifier(WEBSITE, identifier)
				)
				.map(targetPage ->
						create(
								locale,
								targetPage,
								node.getProperty(LinkSetDefinitionBuilder.LINK_TEXT_PROPERTY_NAME_PROVIDER.apply(name), dialogLocale, ValueConverter::getString).orElse(null),
								node.getProperty(LinkSetDefinitionBuilder.OPEN_IN_NEW_TAB_PROPERTY_NAME_PROVIDER.apply(name), ValueConverter::getBoolean).orElse(false)
						)
				);
	}

	public Link create(final Locale locale, final PowerNode targetPage, @Nullable final String text, final boolean openInNewWindow) {
		return new LinkModel(
				getText(() -> Optional.ofNullable(text), locale, targetPage),
				linkUtil.createInternalLink(locale, targetPage),
				linkUtil.createExternalLink(locale, targetPage),
				openInNewWindow,
				false,
				LinkTypes.INTERNAL
		);
	}

	// dialogTitle -> page node name
	protected String getText(final Supplier<Optional<String>> dialogTextSupplier, final Locale locale, final PowerNode targetPage) {
		return dialogTextSupplier.get().orElseGet(targetPage::getName);
	}
}
