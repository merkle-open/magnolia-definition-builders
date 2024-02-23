package com.merkle.oss.magnolia.definition.custom.videoset.model;

import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoTypes;
import com.merkle.oss.magnolia.definition.custom.videoset.model.VideoModel.VideoSource;
import com.merkle.oss.magnolia.definition.custom.videoset.model.VideoModel.VideoSourceTransformer;
import info.magnolia.dam.templating.functions.DamTemplatingFunctions;

import javax.inject.Inject;
import java.util.Locale;
import java.util.Optional;

public class DamVideoSourceTransformer implements VideoSourceTransformer {
	private final DamTemplatingFunctions damTemplatingFunctions;

	@Inject
	public DamVideoSourceTransformer(final DamTemplatingFunctions damTemplatingFunctions) {
		this.damTemplatingFunctions = damTemplatingFunctions;
	}

	@Override
	public boolean test(final VideoType videoType) {
		return VideoTypes.DAM.equals(videoType);
	}

	@Override
	public Optional<VideoSource> transform(final Locale locale, final String src) {
		return Optional.ofNullable(damTemplatingFunctions.getAsset(src)).map(asset ->
			new VideoSource(asset.getLink(), asset.getCaption())
		);
	}
}
