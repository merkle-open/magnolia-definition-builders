package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.objectfactory.Components;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.FieldDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;

import java.lang.reflect.AnnotatedElement;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FieldValidatorDefinitionKeyGenerator extends info.magnolia.ui.field.FieldValidatorDefinitionKeyGenerator {
    private final KeyGeneratorUtil keyGeneratorUtil;
    private final KeyPrefixer keyPrefixer;

    public FieldValidatorDefinitionKeyGenerator() {
        // can't inject anything in keyGenerators -.- see I18nKeyGeneratorFactory
        this(Components.getComponent(KeyGeneratorUtil.class), new KeyPrefixer());
    }

    public FieldValidatorDefinitionKeyGenerator(
            final KeyGeneratorUtil keyGeneratorUtil,
            final KeyPrefixer keyPrefixer
    ) {
        this.keyGeneratorUtil = keyGeneratorUtil;
        this.keyPrefixer = keyPrefixer;
    }

    @Override
    protected void keysFor(
            final List<String> list,
            final FieldValidatorDefinition definition,
            final AnnotatedElement el) {
        if (definition instanceof ConfiguredFieldValidatorDefinition && !keyGeneratorUtil.isMagnoliaModule(definition)) {
            keysFor(list, (ConfiguredFieldValidatorDefinition) definition, el);
        }
        super.keysFor(list, definition, el);
    }

    private void keysFor(
            final List<String> list,
            final ConfiguredFieldValidatorDefinition definition,
            final AnnotatedElement el) {
        final String dialogName = keyGeneratorUtil.getDialogName(definition);
        final FieldDefinition<?> fieldDefinition = this.getParentViaCast(definition);

        addKey(list, getKeys(dialogName, fieldDefinition, definition, el));
        addKey(list, getKeys(keyGeneratorUtil.getFallbackDialogName(), fieldDefinition, definition, el));
    }

    private String[] getKeys(
            final String dialogName,
            final FieldDefinition<?> fieldDefinition,
            final ConfiguredFieldValidatorDefinition definition,
            final AnnotatedElement el) {
        return Stream.of(
                Stream.of(dialogName),
                keyPrefixer.getKeyPrefix(fieldDefinition),
                Stream.of("field"),
                Stream.of(
                        replaceColons(fieldDefinition.getName()),
                        replaceColons(definition.getName()),
                        fieldOrGetterName(el)
                )
        ).flatMap(Function.identity()).sequential().toArray(String[]::new);
    }
}
