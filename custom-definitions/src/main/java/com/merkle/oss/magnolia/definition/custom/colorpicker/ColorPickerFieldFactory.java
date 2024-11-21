package com.merkle.oss.magnolia.definition.custom.colorpicker;

import com.vaadin.ui.Component;

import info.magnolia.i18nsystem.FixedLocaleProvider;
import info.magnolia.i18nsystem.TranslationService;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.editor.LocaleContext;
import info.magnolia.ui.field.factory.AbstractFieldFactory;

import java.util.Optional;

import javax.inject.Inject;

public class ColorPickerFieldFactory extends AbstractFieldFactory<Integer, ColorPickerFieldDefinition> {
	final ColorPickerFieldDefinition definition;
	private final LocaleContext localeContext;
	private final TranslationService translationService;

	@Inject
	public ColorPickerFieldFactory(
			final ColorPickerFieldDefinition definition,
			final ComponentProvider componentProvider,
			final LocaleContext localeContext,
			final TranslationService translationService
	) {
		super(definition, componentProvider);
		this.definition = definition;
		this.localeContext = localeContext;
		this.translationService = translationService;
	}

	@Override
	protected Component createFieldComponent() {
		return new ColorPickerField(definition, getLabel());
	}

	private String getLabel() {
		final String key =  Optional
				.ofNullable(definition.getNoColorCheckboxLabelKey())
				.orElse("merkle.customDefinitions.colorpicker.noColorCheckbox.fallbackLabel");
		return translationService.translate(new FixedLocaleProvider(localeContext.getCurrent()), new String[] { key });
	}
}
