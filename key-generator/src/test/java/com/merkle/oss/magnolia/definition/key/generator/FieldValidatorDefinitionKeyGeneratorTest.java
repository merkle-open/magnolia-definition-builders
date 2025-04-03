package com.merkle.oss.magnolia.definition.key.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.field.CheckBoxFieldDefinition;
import info.magnolia.ui.field.CompositeFieldDefinition;
import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.JcrMultiFieldDefinition;
import info.magnolia.ui.field.RegexpValidatorDefinition;
import info.magnolia.ui.field.TextFieldDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinitionKeyGenerator;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldValidatorDefinitionKeyGeneratorTest extends AbstractKeyGeneratorTest {
    private FieldValidatorDefinitionKeyGenerator keyGenerator;

    @BeforeEach
    void setUp() {
        keyGenerator = new FieldValidatorDefinitionKeyGenerator(
                new KeyGeneratorUtil("FallbackDialog", "FallbackApp", "^idPrefix", Set.of(ConfiguredFormDefinition.class)),
                new FieldDefinitionKeyGenerator(),
                new KeyPrefixer()
        );
    }

    @Test
    void keysFor_isMagnoliaModule() throws NoSuchMethodException {
        final RegexpValidatorDefinition validator = new RegexpValidatorDefinition();
        validator.setName("regexpValidator");
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        textField.setValidators(List.of(validator));
        final TextFieldDefinition decoratedTextField = i18nIfy("someMagnoliaModuleIdPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "someMagnoliaModuleIdPrefix.dialogs.SomeDialog.someText.validation.errorMessage",
                        "someText.validation.errorMessage",
                        "validators.regexpValidator.errorMessage"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField.getValidators().get(0), FieldValidatorDefinition.class.getMethod("getErrorMessage")))
        );
    }

    @Test
    void keysFor_dialog() throws NoSuchMethodException {
        final RegexpValidatorDefinition validator = new RegexpValidatorDefinition();
        validator.setName("regexpValidator");
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        textField.setValidators(List.of(validator));
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeDialog.field.someText.regexpValidator.errorMessage",
                        "FallbackDialog.field.someText.regexpValidator.errorMessage",
                        "validators.regexpValidator.errorMessage"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField.getValidators().get(0), FieldValidatorDefinition.class.getMethod("getErrorMessage")))
        );
    }

    @Test
    void keysFor_app() throws NoSuchMethodException {
        final RegexpValidatorDefinition validator = new RegexpValidatorDefinition();
        validator.setName("regexpValidator");
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        textField.setValidators(List.of(validator));
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeApp.field.SomeSubApp.someText.regexpValidator.errorMessage",
                        "SomeApp.field.someText.regexpValidator.errorMessage",
                        "FallbackApp.field.SomeSubApp.someText.regexpValidator.errorMessage",
                        "FallbackApp.field.someText.regexpValidator.errorMessage",
                        "validators.regexpValidator.errorMessage"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField.getValidators().get(0), FieldValidatorDefinition.class.getMethod("getErrorMessage")))
        );
    }

    @Test
    void keysFor_dialog_prefix() throws NoSuchMethodException {
        final RegexpValidatorDefinition validator = new RegexpValidatorDefinition();
        validator.setName("regexpValidator");
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        textField.setValidators(List.of(validator));
        KeyPrefixer.keyPrefix(textField, "somePrefix");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeDialog.somePrefix.field.someText.regexpValidator.errorMessage",
                        "FallbackDialog.somePrefix.field.someText.regexpValidator.errorMessage",
                        "validators.regexpValidator.errorMessage"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField.getValidators().get(0), FieldValidatorDefinition.class.getMethod("getErrorMessage")))
        );
    }

    @Test
    void keysFor_app_prefix() throws NoSuchMethodException {
        final RegexpValidatorDefinition validator = new RegexpValidatorDefinition();
        validator.setName("regexpValidator");
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        textField.setValidators(List.of(validator));
        KeyPrefixer.keyPrefix(textField, "somePrefix");
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(textField), textField);
        assertEquals(
                List.of(
                        "SomeApp.somePrefix.field.SomeSubApp.someText.regexpValidator.errorMessage",
                        "SomeApp.somePrefix.field.someText.regexpValidator.errorMessage",
                        "FallbackApp.somePrefix.field.SomeSubApp.someText.regexpValidator.errorMessage",
                        "FallbackApp.somePrefix.field.someText.regexpValidator.errorMessage",
                        "validators.regexpValidator.errorMessage"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField.getValidators().get(0), FieldValidatorDefinition.class.getMethod("getErrorMessage")))
        );
    }

    @Test
    void keysFor_dialog_complex() throws NoSuchMethodException {
        final RegexpValidatorDefinition validator = new RegexpValidatorDefinition();
        validator.setName("regexpValidator");
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        textField.setValidators(List.of(validator));
        final CompositeFieldDefinition<?> composite = new CompositeFieldDefinition<>();
        composite.setName("someComposite");
        composite.setProperties(List.of(textField));
        final JcrMultiFieldDefinition multi = new JcrMultiFieldDefinition();
        multi.setName("someMulti");
        multi.setField(composite);
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(multi), textField);

        assertEquals(
                List.of(
                        "SomeDialog.field.someMulti.someComposite.someText.regexpValidator.errorMessage",
                        "FallbackDialog.field.someMulti.someComposite.someText.regexpValidator.errorMessage",
                        "validators.regexpValidator.errorMessage"
                ),
                List.of(keyGenerator.keysFor((String)null,  decoratedTextField.getValidators().get(0), FieldValidatorDefinition.class.getMethod("getErrorMessage")))
        );
    }

    @Test
    void keysFor_app_complex() throws NoSuchMethodException {
        final RegexpValidatorDefinition validator = new RegexpValidatorDefinition();
        validator.setName("regexpValidator");
        final TextFieldDefinition textField = new TextFieldDefinition();
        textField.setName("someText");
        textField.setValidators(List.of(validator));
        final CompositeFieldDefinition<?> composite = new CompositeFieldDefinition<>();
        composite.setName("someComposite");
        composite.setProperties(List.of(textField));
        final JcrMultiFieldDefinition multi = new JcrMultiFieldDefinition();
        multi.setName("someMulti");
        multi.setField(composite);
        final TextFieldDefinition decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", List.of(multi), textField);

        assertEquals(
                List.of(
                        "SomeApp.field.SomeSubApp.someMulti.someComposite.someText.regexpValidator.errorMessage",
                        "SomeApp.field.someMulti.someComposite.someText.regexpValidator.errorMessage",
                        "FallbackApp.field.SomeSubApp.someMulti.someComposite.someText.regexpValidator.errorMessage",
                        "FallbackApp.field.someMulti.someComposite.someText.regexpValidator.errorMessage",
                        "validators.regexpValidator.errorMessage"
                ),
                List.of(keyGenerator.keysFor((String)null,  decoratedTextField.getValidators().get(0), FieldValidatorDefinition.class.getMethod("getErrorMessage")))
        );
    }
}
