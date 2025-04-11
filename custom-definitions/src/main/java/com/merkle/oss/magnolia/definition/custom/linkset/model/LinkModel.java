package com.merkle.oss.magnolia.definition.custom.linkset.model;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;

import java.util.Objects;

public class LinkModel implements Link {
	private final String text;
	private final String href;
	private final String externalHref;
	private final boolean openInNewWindow;
	private final boolean isExternal;
	private final LinkType linkType;

	protected LinkModel(final Link link) {
		this(
				link.getText(),
				link.getHref(),
				link.getExternalHref(),
				link.isOpenInNewWindow(),
				link.isExternal(),
				link.getLinkType()
		);
	}

	public LinkModel(
			final String text,
			final String href,
			final String externalHref,
			final boolean openInNewWindow,
			final boolean isExternal,
			final LinkType linkType
	) {
		this.text = text;
		this.href = href;
		this.externalHref = externalHref;
		this.openInNewWindow = openInNewWindow;
		this.isExternal = isExternal;
		this.linkType = linkType;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getHref() {
		return href;
	}

	@Override
	public String getExternalHref() {
		return externalHref;
	}

	@Override
	public boolean isOpenInNewWindow() {
		return openInNewWindow;
	}

	@Override
	public boolean isExternal() {
		return isExternal;
	}

	@Override
	public LinkType getLinkType() {
		return linkType;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LinkModel linkModel)) {
			return false;
		}
        return openInNewWindow == linkModel.openInNewWindow && isExternal == linkModel.isExternal && Objects.equals(text, linkModel.text) && Objects.equals(href, linkModel.href) && Objects.equals(externalHref, linkModel.externalHref) && Objects.equals(linkType, linkModel.linkType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(text, href, externalHref, openInNewWindow, isExternal, linkType);
	}

	@Override
	public String toString() {
		return "LinkModel{" +
				"text='" + text + '\'' +
				", href='" + href + '\'' +
				", externalHref='" + externalHref + '\'' +
				", openInNewWindow=" + openInNewWindow +
				", isExternal=" + isExternal +
				", linkType=" + linkType +
				'}';
	}
}
