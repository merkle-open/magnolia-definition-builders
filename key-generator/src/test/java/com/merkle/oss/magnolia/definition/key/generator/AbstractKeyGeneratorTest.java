package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.config.NamedDefinition;
import info.magnolia.context.Context;
import info.magnolia.i18nsystem.bytebudddy.ByteBuddyI18nizer;
import info.magnolia.ui.dialog.ConfiguredFormDialogDefinition;
import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.MultiFieldDefinition;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.mockito.Mockito;

abstract class AbstractKeyGeneratorTest {

    protected <T, D extends NamedDefinition> D i18nIfy(final String dialogId, final List<EditorPropertyDefinition> properties, final D definition) {
        final ConfiguredFormDialogDefinition<T> dialog = new ConfiguredFormDialogDefinition<>();
        dialog.setId(dialogId);
        final ConfiguredFormDefinition<T> form = new ConfiguredFormDefinition<>();
        dialog.setForm(form);
        form.setProperties(properties);
        final ConfiguredFormDialogDefinition<T> decoratedDialog = new ByteBuddyI18nizer(new TranslationServiceMock(), () -> Mockito.mock(Context.class)).decorate(dialog);
        return streamProperties(decoratedDialog.getForm())
                .filter(property -> Objects.equals(property.getName(), definition.getName()))
                .findFirst()
                .map(d -> (D)d)
                .orElseThrow();
    }

    private <T> Stream<EditorPropertyDefinition> streamProperties(final FormDefinition<T> form) {
        return Stream.concat(
                form.getProperties().stream(),
                form.getSubFormDefinitions().stream().flatMap(this::streamChildProperties)
        );
    }

    private <T> Stream<EditorPropertyDefinition> streamChildProperties(final EditorPropertyDefinition property) {
        if(property instanceof FormDefinition) {
            final FormDefinition<T> form = (FormDefinition<T>) property;
            return Stream.concat(
                    form.getProperties().stream(),
                    form.getSubFormDefinitions().stream().flatMap(this::streamChildProperties)
            );
        }
        if(property instanceof MultiFieldDefinition) {
            return streamChildProperties(((MultiFieldDefinition<T>) property).getField());
        }
        return Stream.empty();
    }
}