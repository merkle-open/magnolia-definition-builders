package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.objectfactory.Components;
import info.magnolia.ui.contentapp.detail.DetailDescriptor;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinitionKeyGenerator;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FieldValidatorDefinitionKeyGenerator extends info.magnolia.ui.field.FieldValidatorDefinitionKeyGenerator {
    private final KeyGeneratorUtil keyGeneratorUtil;
    private final EditorPropertyDefinitionKeyGenerator editorPropertyDefinitionKeyGenerator;

    public FieldValidatorDefinitionKeyGenerator() {
        // can't inject anything in keyGenerators -.- see I18nKeyGeneratorFactory
        this(Components.getComponent(KeyGeneratorUtil.class),  new FieldDefinitionKeyGenerator(), new KeyPrefixer());
    }

    public FieldValidatorDefinitionKeyGenerator(
            final KeyGeneratorUtil keyGeneratorUtil,
            final FieldDefinitionKeyGenerator fieldDefinitionKeyGenerator,
            final KeyPrefixer keyPrefixer
    ) {
        this.keyGeneratorUtil = keyGeneratorUtil;
        this.editorPropertyDefinitionKeyGenerator = new EditorPropertyDefinitionKeyGenerator(keyGeneratorUtil, fieldDefinitionKeyGenerator, keyPrefixer);
    }

    @Override
    protected void keysFor(
            final List<String> list,
            final FieldValidatorDefinition definition,
            final AnnotatedElement el
    ) {
        if (definition instanceof ConfiguredFieldValidatorDefinition && !keyGeneratorUtil.isMagnoliaModule(definition)) {
            keysFor(list, (ConfiguredFieldValidatorDefinition) definition, el);
        } else {
            super.keysFor(list, definition, el);
        }
    }

    private void keysFor(
            final List<String> list,
            final ConfiguredFieldValidatorDefinition definition,
            final AnnotatedElement el
    ) {
        final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
        final EditorPropertyDefinition fieldDefinition = super.getParentViaCast(definition);
        addKey(list, getKeys(rootDefinitionName, ignored -> true, fieldDefinition, definition, el));
        addKey(list, getKeys(rootDefinitionName, d -> !(d instanceof DetailDescriptor), fieldDefinition, definition, el));
        addKey(list, getKeys(keyGeneratorUtil.getFallbackName(definition), ignored -> true, fieldDefinition, definition, el));
        addKey(list, getKeys(keyGeneratorUtil.getFallbackName(definition), d -> !(d instanceof DetailDescriptor), fieldDefinition, definition, el));
        addKey(list, "validators", definition.getName(), fieldOrGetterName(el));
    }

    protected String[] getKeys(
            final String rootDefinitionName,
            final Predicate<Object> filter,
            final EditorPropertyDefinition fieldDefinition,
            final ConfiguredFieldValidatorDefinition definition,
            final AnnotatedElement el
    ) {
        final String[] editorPropertyDefinitionKeys = editorPropertyDefinitionKeyGenerator.getKeys(rootDefinitionName, filter, fieldDefinition, el);
        return Stream.concat(
                Arrays.stream(editorPropertyDefinitionKeys).limit(editorPropertyDefinitionKeys.length - 1),
                Stream.of(
                        replaceColons(definition.getName()),
                        fieldOrGetterName(el)
                )
        ).sequential().toArray(String[]::new);
    }
}
