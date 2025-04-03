package com.merkle.oss.magnolia.definition.key.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.field.CheckBoxFieldDefinition;
import info.magnolia.ui.field.CompositeFieldDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.JcrMultiFieldDefinition;
import info.magnolia.ui.field.TextFieldDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinitionKeyGenerator;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditorPropertyDefinitionKeyGeneratorTest extends AbstractKeyGeneratorTest {
    private EditorPropertyDefinitionKeyGenerator keyGenerator;

    @BeforeEach
    void setUp() {
        keyGenerator = new EditorPropertyDefinitionKeyGenerator(
                new KeyGeneratorUtil("FallbackDialog", "FallbackApp","^idPrefix", Set.of(ConfiguredFormDefinition.class)),
                new FieldDefinitionKeyGenerator(),
                new KeyPrefixer()
        );
    }

    @Test
    void keysFor_isMagnoliaModule_label() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final TextFieldDefinition decoratedTextField = i18nIfy("someMagnoliaModuleIdPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "someMagnoliaModuleIdPrefix.dialogs.SomeDialog.someText.label",
                        "someMagnoliaModuleIdPrefix.dialogs.SomeDialog.someText",
                        "fields.someText.label",
                        "fields.someText"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_dialog_label() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeDialog.field.someText.label",
                        "SomeDialog.field.someText",
                        "FallbackDialog.field.someText.label",
                        "FallbackDialog.field.someText"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_app_label() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeApp.field.SomeSubApp.someText.label",
                        "SomeApp.field.SomeSubApp.someText",
                        "SomeApp.field.someText.label",
                        "SomeApp.field.someText",
                        "FallbackApp.field.SomeSubApp.someText.label",
                        "FallbackApp.field.SomeSubApp.someText",
                        "FallbackApp.field.someText.label",
                        "FallbackApp.field.someText"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_dialog_label_prefix() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        KeyPrefixer.keyPrefix(textField, "somePrefix");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeDialog.somePrefix.field.someText.label",
                        "SomeDialog.somePrefix.field.someText",
                        "FallbackDialog.somePrefix.field.someText.label",
                        "FallbackDialog.somePrefix.field.someText"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_app_label_prefix() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        KeyPrefixer.keyPrefix(textField, "somePrefix");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeApp.somePrefix.field.SomeSubApp.someText.label",
                        "SomeApp.somePrefix.field.SomeSubApp.someText",
                        "SomeApp.somePrefix.field.someText.label",
                        "SomeApp.somePrefix.field.someText",
                        "FallbackApp.somePrefix.field.SomeSubApp.someText.label",
                        "FallbackApp.somePrefix.field.SomeSubApp.someText",
                        "FallbackApp.somePrefix.field.someText.label",
                        "FallbackApp.somePrefix.field.someText"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_dialog_label_complex() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final CompositeFieldDefinition<?> composite = new CompositeFieldDefinition<>();
        composite.setName("someComposite");
        composite.setProperties(List.of(textField));
        final JcrMultiFieldDefinition multi = new JcrMultiFieldDefinition();
        multi.setName("someMulti");
        multi.setField(composite);
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(multi), textField);
        assertEquals(
                List.of(
                        "SomeDialog.field.someMulti.someComposite.someText.label",
                        "SomeDialog.field.someMulti.someComposite.someText",
                        "FallbackDialog.field.someMulti.someComposite.someText.label",
                        "FallbackDialog.field.someMulti.someComposite.someText"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_app_label_complex() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final CompositeFieldDefinition<?> composite = new CompositeFieldDefinition<>();
        composite.setName("someComposite");
        composite.setProperties(List.of(textField));
        final JcrMultiFieldDefinition multi = new JcrMultiFieldDefinition();
        multi.setName("someMulti");
        multi.setField(composite);
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(multi), textField);
        assertEquals(
                List.of(
                        "SomeApp.field.SomeSubApp.someMulti.someComposite.someText.label",
                        "SomeApp.field.SomeSubApp.someMulti.someComposite.someText",
                        "SomeApp.field.someMulti.someComposite.someText.label",
                        "SomeApp.field.someMulti.someComposite.someText",
                        "FallbackApp.field.SomeSubApp.someMulti.someComposite.someText.label",
                        "FallbackApp.field.SomeSubApp.someMulti.someComposite.someText",
                        "FallbackApp.field.someMulti.someComposite.someText.label",
                        "FallbackApp.field.someMulti.someComposite.someText"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_dialog_description() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeDialog.field.someText.description",
                        "FallbackDialog.field.someText.description"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getDescription")))
        );
    }

    @Test
    void keysFor_app_description() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeApp.field.SomeSubApp.someText.description",
                        "SomeApp.field.someText.description",
                        "FallbackApp.field.SomeSubApp.someText.description",
                        "FallbackApp.field.someText.description"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, EditorPropertyDefinition.class.getMethod("getDescription")))
        );
    }

    @Test
    void keysFor_dialog_placeholder() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeDialog.field.someText.placeholder",
                        "FallbackDialog.field.someText.placeholder"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, TextFieldDefinition.class.getMethod("getPlaceholder")))
        );
    }

    @Test
    void keysFor_app_placeholder() throws NoSuchMethodException {
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeApp.field.SomeSubApp.someText.placeholder",
                        "SomeApp.field.someText.placeholder",
                        "FallbackApp.field.SomeSubApp.someText.placeholder",
                        "FallbackApp.field.someText.placeholder"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, TextFieldDefinition.class.getMethod("getPlaceholder")))
        );
    }

    @Test
    void keysFor_dialog_buttonLabel() throws NoSuchMethodException {
        final CheckBoxFieldDefinition checkbox = new CheckBoxFieldDefinition();
        checkbox.setName("someCheckbox");
        final CheckBoxFieldDefinition decoratedCheckbox = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(checkbox), checkbox);
        assertEquals(
                List.of(
                        "SomeDialog.field.someCheckbox.buttonLabel",
                        "FallbackDialog.field.someCheckbox.buttonLabel"
                ),
                List.of(keyGenerator.keysFor((String)null,  decoratedCheckbox, CheckBoxFieldDefinition.class.getMethod("getButtonLabel")))
        );
    }

    @Test
    void keysFor_app_buttonLabel() throws NoSuchMethodException {
        final CheckBoxFieldDefinition checkbox = new CheckBoxFieldDefinition();
        checkbox.setName("someCheckbox");
        final CheckBoxFieldDefinition decoratedCheckbox = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp",  List.of(checkbox), checkbox);
        assertEquals(
                List.of(
                        "SomeApp.field.SomeSubApp.someCheckbox.buttonLabel",
                        "SomeApp.field.someCheckbox.buttonLabel",
                        "FallbackApp.field.SomeSubApp.someCheckbox.buttonLabel",
                        "FallbackApp.field.someCheckbox.buttonLabel"
                ),
                List.of(keyGenerator.keysFor((String)null,  decoratedCheckbox, CheckBoxFieldDefinition.class.getMethod("getButtonLabel")))
        );
    }
}
