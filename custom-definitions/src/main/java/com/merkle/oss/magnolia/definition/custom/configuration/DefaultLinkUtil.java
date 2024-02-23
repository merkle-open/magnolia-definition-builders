package com.merkle.oss.magnolia.definition.custom.configuration;

import com.merkle.oss.magnolia.powernode.PowerNode;
import info.magnolia.context.MgnlContext;
import info.magnolia.link.Link;
import info.magnolia.link.LinkTransformerManager;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class DefaultLinkUtil implements LinkUtil {
	@Override
	public String toExternalLink(final String path) {
		final Link link = new Link();
		link.setPath(StringUtils.removeStart(path, MgnlContext.getContextPath()));
		return LinkTransformerManager.getInstance().getCompleteUrl().transform(link);
	}

	@Override
	public String createExternalLink(final Locale locale, final PowerNode page) {
		return info.magnolia.link.LinkUtil.createExternalLink(page);
	}

	@Override
	public String createInternalLink(final Locale locale, final PowerNode page) {
		return info.magnolia.link.LinkUtil.createLink(page);
	}
}
