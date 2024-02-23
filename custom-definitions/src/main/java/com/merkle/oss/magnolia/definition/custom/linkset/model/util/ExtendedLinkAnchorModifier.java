package com.merkle.oss.magnolia.definition.custom.linkset.model.util;

import com.google.common.base.Joiner;
import com.merkle.oss.magnolia.definition.custom.linkset.model.Link;
import com.merkle.oss.magnolia.definition.custom.linkset.model.LinkModel;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtendedLinkAnchorModifier {
	private final Pattern pattern = Pattern.compile("^([^#]*)(#.+|)$");

	public Link with(final Link extendedLink, final String anchorId) {
		return new LinkModel(
				extendedLink.getText(),
				getUrlWithAnchor(extendedLink.getHref(), anchorId),
				getUrlWithAnchor(extendedLink.getExternalHref(), anchorId),
				extendedLink.isOpenInNewWindow(),
				extendedLink.isExternal(),
				extendedLink.getLinkType()
		);
	}

	private String getUrlWithAnchor(final String href, final String anchorId) {
		final Matcher matcher = pattern.matcher(href);
		if(matcher.matches()) {
			return Joiner.on("#").join(matcher.group(1), anchorId);
		}
		return href;
	}
}
