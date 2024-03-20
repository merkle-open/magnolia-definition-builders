package com.merkle.oss.magnolia.definition.custom.imageset.configuration;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageTypes;
import com.merkle.oss.magnolia.definition.custom.imageset.model.DamImageSourceTransformer;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageModel;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkTypes;

public class ImageSetGuiceModule implements Module {
	@Override
	public void configure(final Binder binder) {
		final Multibinder<ImageType.Resolver> imageTypeResolversMultibinder = Multibinder.newSetBinder(binder, ImageType.Resolver.class);
		imageTypeResolversMultibinder.addBinding().to(ImageTypes.Resolver.class);

		final Multibinder<ImageModel.ImageSourceTransformer> imageSourceTransformersMultibinder = Multibinder.newSetBinder(binder, ImageModel.ImageSourceTransformer.class);
		imageSourceTransformersMultibinder.addBinding().to(DamImageSourceTransformer.class);
	}
}
