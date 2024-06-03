package com.merkle.oss.magnolia.definition.custom.contentapp.column;

import info.magnolia.context.MgnlContext;
import info.magnolia.i18nsystem.FixedLocaleProvider;
import info.magnolia.i18nsystem.TranslationService;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.jcr.Item;

import com.vaadin.ui.DescriptionGenerator;

public class TimeZoneTooltip implements DescriptionGenerator<Item> {
    private final TranslationService translationService;
    private final Provider<ZoneId> zoneIdProvider;

    @Inject
    public TimeZoneTooltip(
            final TranslationService translationService,
            final Provider<ZoneId> zoneIdProvider
    ) {
        this.translationService = translationService;
        this.zoneIdProvider = zoneIdProvider;
    }

    @Override
    public String apply(final Item item) {
        final Locale locale = Locale.forLanguageTag(MgnlContext.getUser().getLanguage());
        final String prefix = translationService.translate(new FixedLocaleProvider(locale), new String[] { "ui-admincentral.dateField.timeZone.label" });
        final String longInfo = zoneIdProvider.get().getDisplayName(TextStyle.NARROW, locale);
        return prefix + " " + longInfo;
    }
}
