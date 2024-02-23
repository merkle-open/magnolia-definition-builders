package com.merkle.oss.magnolia.definition.custom.linkset.configuration;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkTypes;
import com.merkle.oss.magnolia.definition.custom.linkset.model.AssetLinkFactory;
import com.merkle.oss.magnolia.definition.custom.linkset.model.ExternalLinkFactory;
import com.merkle.oss.magnolia.definition.custom.linkset.model.InternalLinkFactory;
import com.merkle.oss.magnolia.definition.custom.linkset.model.LinkModelFactory;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoTypes;

public class LinkSetGuiceModule implements Module {
	@Override
	public void configure(final Binder binder) {
		final Multibinder<LinkType.Resolver> linkTypeResolversMultibinder = Multibinder.newSetBinder(binder, LinkType.Resolver.class);
		linkTypeResolversMultibinder.addBinding().toProvider(() -> LinkTypes::fromValue);

		final Multibinder<LinkModelFactory.LinkFactory> linkFactoriesMultibinder = Multibinder.newSetBinder(binder, LinkModelFactory.LinkFactory.class);
		linkFactoriesMultibinder.addBinding().to(ExternalLinkFactory.class);
		linkFactoriesMultibinder.addBinding().to(InternalLinkFactory.class);
		linkFactoriesMultibinder.addBinding().to(AssetLinkFactory.class);
	}
}
