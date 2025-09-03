package com.merkle.oss.magnolia.definition.custom.imageset.model;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageTypes;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageModel.ImageSource;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageModel.ImageSourceTransformer;
import info.magnolia.dam.templating.functions.DamTemplatingFunctions;

import jakarta.inject.Inject;
import java.util.Locale;
import java.util.Optional;

public class DamImageSourceTransformer implements ImageSourceTransformer {
	private final DamTemplatingFunctions damTemplatingFunctions;

	@Inject
	public DamImageSourceTransformer(final DamTemplatingFunctions damTemplatingFunctions) {
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
					new ImageSource(asset.getLink(), asset.getCaption())
				);
	}
}
