package com.merkle.oss.magnolia.definition.custom.configuration;

import com.merkle.oss.magnolia.powernode.PowerNode;

import java.util.Locale;

public interface LocaleProvider {
	Locale getDefaultLocale(PowerNode node);
}
