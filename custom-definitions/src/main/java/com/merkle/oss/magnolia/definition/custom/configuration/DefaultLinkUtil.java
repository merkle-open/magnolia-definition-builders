package com.merkle.oss.magnolia.definition.custom.configuration;

import java.util.Locale;

import com.merkle.oss.magnolia.powernode.PowerNode;

public class DefaultLinkUtil implements LinkUtil {
	@Override
	public String createInternalLink(final Locale locale, final PowerNode page) {
		return info.magnolia.link.LinkUtil.createLink(page);
	}
}
