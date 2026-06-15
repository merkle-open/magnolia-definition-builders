package com.merkle.oss.magnolia.definition.custom.linkset.model;

import java.io.Serializable;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkType;

public interface Link extends Serializable {
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
