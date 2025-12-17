package com.merkle.oss.magnolia.definition.custom.videoset.model;

import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoTypes;
import com.merkle.oss.magnolia.definition.custom.videoset.model.VideoModel.VideoSource;
import com.merkle.oss.magnolia.definition.custom.videoset.model.VideoModel.VideoSourceTransformer;
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

public class DamVideoSourceTransformer implements VideoSourceTransformer {
    private final PowerNodeService powerNodeService;
    private final DamTemplatingFunctions damTemplatingFunctions;

	@Inject
	public DamVideoSourceTransformer(
			final PowerNodeService powerNodeService,
			final DamTemplatingFunctions damTemplatingFunctions
	) {
        this.powerNodeService = powerNodeService;
        this.damTemplatingFunctions = damTemplatingFunctions;
	}

	@Override
	public boolean test(final VideoType videoType) {
		return VideoTypes.DAM.equals(videoType);
	}

	@Override
	public Optional<VideoSource> transform(final Locale locale, final String src) {
		return Optional.ofNullable(damTemplatingFunctions.getAsset(src)).map(asset ->
			new VideoSource(asset.getLink(), getAltText(locale, asset))
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
