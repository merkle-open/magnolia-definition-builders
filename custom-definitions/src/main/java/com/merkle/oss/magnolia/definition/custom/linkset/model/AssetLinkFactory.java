package com.merkle.oss.magnolia.definition.custom.linkset.model;

import static java.util.function.Predicate.not;

import info.magnolia.dam.api.Asset;
import info.magnolia.dam.templating.functions.DamTemplatingFunctions;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkTypes;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;

import jakarta.inject.Inject;

public class AssetLinkFactory implements LinkModelFactory.LinkFactory {
	private final DamTemplatingFunctions damTemplatingFunctions;

	@Inject
	public AssetLinkFactory(final DamTemplatingFunctions damTemplatingFunctions) {
		this.damTemplatingFunctions = damTemplatingFunctions;
	}

	@Override
	public boolean test(final LinkType linkType) {
		return LinkTypes.ASSET_DAM.equals(linkType);
	}

	@Override
	public Optional<Link> create(final Locale locale, final Locale dialogLocale, final PowerNode node, final String propertyName) {
		return node.getProperty(propertyName, dialogLocale, ValueConverter::getString)
				.map(damTemplatingFunctions::getAsset)
				.map(asset ->
						new LinkModel(
								getText(() ->
												node.getProperty(LinkSetDefinitionBuilder.LINK_TEXT_PROPERTY_NAME_PROVIDER.apply(propertyName), dialogLocale, ValueConverter::getString),
										locale,
										asset
								),
								asset.getLink(),
								node.getProperty(LinkSetDefinitionBuilder.OPEN_IN_NEW_TAB_PROPERTY_NAME_PROVIDER.apply(propertyName), ValueConverter::getBoolean).orElse(false),
								false,
								LinkTypes.ASSET_DAM
						)
				);
	}

	// dialogTitle -> asset title -> asset caption -> asset name -> asset filename
	protected String getText(final Supplier<Optional<String>> dialogTextSupplier, final Locale locale, final Asset asset) {
		return dialogTextSupplier.get()
				.or(() -> Optional.ofNullable(asset.getTitle()).filter(not(String::isBlank)))
				.or(() -> Optional.ofNullable(asset.getCaption()).filter(not(String::isBlank)))
				.or(() -> Optional.ofNullable(asset.getName()).filter(not(String::isBlank)))
				.orElseGet(asset::getFileName);
	}
}
