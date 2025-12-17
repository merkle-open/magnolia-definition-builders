package com.merkle.oss.magnolia.definition.custom.imageset.model;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageTypes;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageModel.ImageSource;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageModel.ImageSourceTransformer;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.merkle.oss.magnolia.powernode.ValueConverter;

import info.magnolia.dam.api.Asset;
import info.magnolia.dam.jcr.AbstractJcrItem;
import info.magnolia.dam.jcr.AssetNodeTypes;
import info.magnolia.dam.templating.functions.DamTemplatingFunctions;

import jakarta.inject.Inject;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class DamImageSourceTransformer implements ImageSourceTransformer {
    private final PowerNodeService powerNodeService;
    private final DamTemplatingFunctions damTemplatingFunctions;

	@Inject
	public DamImageSourceTransformer(
			final PowerNodeService powerNodeService,
			final DamTemplatingFunctions damTemplatingFunctions
	) {
        this.powerNodeService = powerNodeService;
        this.damTemplatingFunctions = damTemplatingFunctions;
	}

	@Override
	public boolean test(final ImageType imageType) {
		return ImageTypes.DAM.equals(imageType);
	}

	@Override
	public Optional<ImageSource> transform(final Locale locale, final String assetId) {
		return Optional
				.ofNullable(damTemplatingFunctions.getAsset(assetId))
				.map(asset ->
					new ImageSource(asset.getLink(), getAltText(locale, asset))
				);
	}

	protected String getAltText(final Locale locale, final Asset asset) {
		return getLocalizedStringFromNode(asset, locale, AssetNodeTypes.Asset.CAPTION).or(() ->
				Optional.of(asset.getCaption()).filter(StringUtils::isNotEmpty)
		).orElseGet(asset::getName);
	}

	private Optional<String> getLocalizedStringFromNode(final Asset asset, final Locale locale, final String key) {
		return Optional
				.of(asset)
				.filter(AbstractJcrItem.class::isInstance)
				.map(AbstractJcrItem.class::cast)
				.map(AbstractJcrItem::getNode)
				.map(powerNodeService::convertToPowerNode)
				.flatMap(assetNode ->
						assetNode.getProperty(key, locale, ValueConverter::getString)
				);
	}
}
