package com.merkle.oss.magnolia.definition.custom.configuration;

import com.merkle.oss.magnolia.powernode.PowerNode;

import java.util.Locale;

public interface LinkUtil {
	String toExternalLink(String path);
	String createExternalLink(Locale locale, PowerNode page);
	String createInternalLink(Locale locale, PowerNode page);
}
