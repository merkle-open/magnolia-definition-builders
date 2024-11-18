package com.merkle.oss.magnolia.definition.custom.colorpicker.model;

import java.util.Locale;
import java.util.Optional;

import javax.inject.Inject;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;
import com.vaadin.shared.ui.colorpicker.Color;

public class ColorFactory {
    private final LocaleProvider localeProvider;

    @Inject
    public ColorFactory(final LocaleProvider localeProvider) {
        this.localeProvider = localeProvider;
    }

    public Optional<Color> create(final String propertyName, final PowerNode node) {
        return create(propertyName, localeProvider.getDefaultLocale(node), node);
    }
    public Optional<Color> create(final String propertyName, final Locale locale, final PowerNode node) {
        return node
                .getProperty(propertyName, locale, ValueConverter::getInteger)
                .map(Color::new);
    }
}
