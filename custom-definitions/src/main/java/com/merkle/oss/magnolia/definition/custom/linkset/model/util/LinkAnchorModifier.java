package com.merkle.oss.magnolia.definition.custom.linkset.model.util;

import com.google.common.base.Joiner;
import com.merkle.oss.magnolia.definition.custom.linkset.model.Link;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkAnchorModifier {
	private final Pattern pattern = Pattern.compile("^([^#]*)(#.+|)$");

	public Link with(final Link link, final String anchorId) {
		return link.withHref(
				getUrlWithAnchor(link.getHref(), anchorId),
				getUrlWithAnchor(link.getExternalHref(), anchorId)
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
