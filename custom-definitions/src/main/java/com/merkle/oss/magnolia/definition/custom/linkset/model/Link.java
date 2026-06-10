package com.merkle.oss.magnolia.definition.custom.linkset.model;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;

public interface Link {
	String getText();
	String getHref();
	boolean isOpenInNewWindow();

	Link withText(String text);
	Link withHref(String href);
	Link withOpenInNewWindow(boolean openInNewWindow);

	LinkType getLinkType();
	default boolean isExternal() {
		return false;
	}
}
