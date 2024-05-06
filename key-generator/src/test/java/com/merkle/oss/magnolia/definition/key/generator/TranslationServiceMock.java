package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.i18nsystem.LocaleProvider;
import info.magnolia.i18nsystem.TranslationService;

public class TranslationServiceMock implements TranslationService {
    @Override
    public String translate(LocaleProvider localeProvider, String[] keys) {
        return String.join(".", keys);
    }

    @Override
    public String translate(LocaleProvider localeProvider, String basename, String[] keys) {
        return translate(localeProvider, keys);
    }

    @Override
    public void reloadMessageBundles() {
    }
}
