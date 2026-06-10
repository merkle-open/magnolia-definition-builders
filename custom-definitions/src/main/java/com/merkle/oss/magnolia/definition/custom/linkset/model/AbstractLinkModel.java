package com.merkle.oss.magnolia.definition.custom.linkset.model;

import java.util.Objects;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;

public abstract class AbstractLinkModel implements Link {
	private final String text;
	private final String href;
	private final boolean openInNewWindow;
	private final boolean isExternal;
	private final LinkType linkType;

    protected AbstractLinkModel(final Link link) {
		this(
				link.getText(),
				link.getHref(),
				link.isOpenInNewWindow(),
				link.isExternal(),
				link.getLinkType()
		);
	}

	public AbstractLinkModel(
			final String text,
			final String href,
			final boolean openInNewWindow,
			final boolean isExternal,
			final LinkType linkType
	) {
		this.text = text;
		this.href = href;
		this.openInNewWindow = openInNewWindow;
		this.isExternal = isExternal;
		this.linkType = linkType;
    }

	protected abstract Link create(final Link link);

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getHref() {
		return href;
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
	public Link withText(final String text) {
		return create(new LinkModel(text, href, openInNewWindow, isExternal, linkType));
	}


	@Override
	public Link withHref(final String href) {
		return create(new LinkModel(text, href, openInNewWindow, isExternal, linkType));
	}

	@Override
	public Link withOpenInNewWindow(final boolean openInNewWindow) {
		return create(new LinkModel(text, href, openInNewWindow, isExternal, linkType));
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AbstractLinkModel that)) {
			return false;
		}
        return openInNewWindow == that.openInNewWindow && isExternal == that.isExternal && Objects.equals(text, that.text) && Objects.equals(href, that.href) && Objects.equals(linkType, that.linkType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(text, href, openInNewWindow, isExternal, linkType);
	}

	@Override
	public String toString() {
		return "AbstractLinkModel{" +
				"text='" + text + '\'' +
				", href='" + href + '\'' +
				", openInNewWindow=" + openInNewWindow +
				", isExternal=" + isExternal +
				", linkType=" + linkType +
				'}';
	}
}
