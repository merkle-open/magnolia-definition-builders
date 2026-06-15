package com.merkle.oss.magnolia.definition.custom.linkset.model;

import java.io.Serializable;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;

public final class LinkModel extends AbstractLinkModel implements Serializable {

	public LinkModel(final Link link) {
		super(link);
	}

	public LinkModel(
			final String text,
			final String href,
			final boolean openInNewWindow,
			final boolean isExternal,
			final LinkType linkType
	) {
		super(text, href, openInNewWindow, isExternal, linkType);
	}

	@Override
	protected Link create(final Link link) {
		return new LinkModel(link);
	}
}
