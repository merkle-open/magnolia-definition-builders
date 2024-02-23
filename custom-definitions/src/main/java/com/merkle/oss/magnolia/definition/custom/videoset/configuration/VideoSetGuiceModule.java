package com.merkle.oss.magnolia.definition.custom.videoset.configuration;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoTypes;
import com.merkle.oss.magnolia.definition.custom.videoset.model.DamVideoSourceTransformer;
import com.merkle.oss.magnolia.definition.custom.videoset.model.VideoModel;
import com.merkle.oss.magnolia.definition.custom.videoset.model.VimeoVideoSourceTransformer;
import com.merkle.oss.magnolia.definition.custom.videoset.model.YoutubeVideoSourceTransformer;

public class VideoSetGuiceModule implements Module {
	@Override
	public void configure(final Binder binder) {
		final Multibinder<VideoType.Resolver> videoTypeResolversMultibinder = Multibinder.newSetBinder(binder, VideoType.Resolver.class);
		videoTypeResolversMultibinder.addBinding().toProvider(() -> VideoTypes::fromValue);

		final Multibinder<VideoModel.VideoSourceTransformer> videoSourceTransformersMultibinder = Multibinder.newSetBinder(binder, VideoModel.VideoSourceTransformer.class);
		videoSourceTransformersMultibinder.addBinding().to(DamVideoSourceTransformer.class);
		videoSourceTransformersMultibinder.addBinding().to(VimeoVideoSourceTransformer.class);
		videoSourceTransformersMultibinder.addBinding().to(YoutubeVideoSourceTransformer.class);
	}
}
