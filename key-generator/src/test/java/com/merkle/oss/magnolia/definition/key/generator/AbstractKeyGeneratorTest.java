package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.config.NamedDefinition;
import info.magnolia.context.Context;
import info.magnolia.i18nsystem.bytebudddy.ByteBuddyI18nizer;
import info.magnolia.ui.api.app.AppDescriptor;
import info.magnolia.ui.contentapp.configuration.ContentAppDescriptor;
import info.magnolia.ui.contentapp.detail.DetailDescriptor;
import info.magnolia.ui.contentapp.detail.DetailSubAppDescriptor;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.dialog.ConfiguredFormDialogDefinition;
import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.MultiFieldDefinition;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import javax.inject.Provider;

import org.mockito.Mockito;

abstract class AbstractKeyGeneratorTest {

    protected <T, D extends NamedDefinition> D i18nIfy(final String dialogId, final List<EditorPropertyDefinition> properties, final D definition) {
        return i18nIfy(properties, definition, (UnaryOperator<FormDefinition<T>>) form -> {
            final ConfiguredFormDialogDefinition<T> dialog = new ConfiguredFormDialogDefinition<>();
            dialog.setId(dialogId);
            dialog.setForm(form);
            final ConfiguredFormDialogDefinition<T> decoratedDialog = new ByteBuddyI18nizer(new TranslationServiceMock(), () -> Mockito.mock(Context.class)).decorate(dialog);
            return decoratedDialog.getForm();
        });
    }

    protected <T, D extends NamedDefinition> D i18nIfy(final String appId, final String appName, final String subAppName, final List<EditorPropertyDefinition> properties, final D definition) {
        return i18nIfy(properties, definition, (UnaryOperator<FormDefinition<T>>) form -> {
            final ContentAppDescriptor<DatasourceDefinition> appDescriptor = new ContentAppDescriptorWithId(appId);
            final NamedDetailDescriptor<T> detailSubApp = new NamedDetailDescriptor<>();
            appDescriptor.setName(appName);
            appDescriptor.setSubApps(Map.of(subAppName, detailSubApp));
            detailSubApp.setName(subAppName);
            detailSubApp.setForm(form);
            final ContentAppDescriptor<DatasourceDefinition> decoratedAppDescriptor = new ByteBuddyI18nizer(new TranslationServiceMock(), () -> Mockito.mock(Context.class)).decorate(appDescriptor);
            return ((DetailDescriptor<T, DatasourceDefinition>)decoratedAppDescriptor.getSubApps().get(subAppName)).getForm();
        });
    }

    private <T, D extends NamedDefinition> D i18nIfy(final List<EditorPropertyDefinition> properties, final D definition, final UnaryOperator<FormDefinition<T>> i18nizer) {
        final ConfiguredFormDefinition<T> form = new ConfiguredFormDefinition<>();
        form.setProperties(properties);
        return streamProperties(i18nizer.apply(form))
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

    public static class NamedDetailDescriptor<T> extends DetailDescriptor<T, DatasourceDefinition> implements NamedDefinition {}
    public static class ContentAppDescriptorWithId extends ContentAppDescriptor<DatasourceDefinition> {
        private final String id;
        public ContentAppDescriptorWithId(final String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }
    }
}
