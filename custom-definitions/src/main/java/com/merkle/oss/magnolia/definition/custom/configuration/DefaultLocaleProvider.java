package com.merkle.oss.magnolia.definition.custom.configuration;

import com.merkle.oss.magnolia.powernode.PowerNode;
import info.magnolia.cms.i18n.I18nContentSupport;

import javax.inject.Inject;
import java.util.Locale;

public class DefaultLocaleProvider implements LocaleProvider {
	private final I18nContentSupport i18nContentSupport;

	@Inject
	public DefaultLocaleProvider(final I18nContentSupport i18nContentSupport) {
		this.i18nContentSupport = i18nContentSupport;
	}

	@Override
	public Locale getDefaultLocale(final PowerNode node) {
		return i18nContentSupport.getDefaultLocale();
	}
}
