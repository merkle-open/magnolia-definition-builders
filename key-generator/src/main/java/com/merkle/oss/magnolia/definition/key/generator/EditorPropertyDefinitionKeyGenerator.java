package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.config.NamedDefinition;
import info.magnolia.objectfactory.Components;
import info.magnolia.ui.contentapp.detail.DetailDescriptor;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinitionKeyGenerator;

import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;
import jakarta.inject.Inject;

public class EditorPropertyDefinitionKeyGenerator extends info.magnolia.ui.editor.i18n.EditorPropertyDefinitionKeyGenerator {
    private final KeyGeneratorUtil keyGeneratorUtil;
    private final FieldDefinitionKeyGenerator fieldDefinitionKeyGenerator;
    private final KeyPrefixer keyPrefixer;

    public EditorPropertyDefinitionKeyGenerator() {
        // can't inject anything in keyGenerators -.- see I18nKeyGeneratorFactory
        this(Components.getComponent(KeyGeneratorUtil.class), new FieldDefinitionKeyGenerator(), new KeyPrefixer());
    }

    @Inject
    public EditorPropertyDefinitionKeyGenerator(
            final KeyGeneratorUtil keyGeneratorUtil,
            final FieldDefinitionKeyGenerator fieldDefinitionKeyGenerator,
            final KeyPrefixer keyPrefixer
    ) {
        this.keyGeneratorUtil = keyGeneratorUtil;
        this.fieldDefinitionKeyGenerator = fieldDefinitionKeyGenerator;
        this.keyPrefixer = keyPrefixer;
    }

    @Override
    public String[] keysFor(@Nullable final String undecoratedResult, final EditorPropertyDefinition definition, final AnnotatedElement el) {
        if (definition instanceof FieldDefinition) {
            return fieldDefinitionKeyGenerator.keysFor(undecoratedResult, definition, el);
        }
        return super.keysFor(undecoratedResult, definition, el);
    }

    @Override
    protected void keysFor(
            final List<String> list,
            final EditorPropertyDefinition definition,
            final AnnotatedElement el
    ) {
        if (keyGeneratorUtil.isMagnoliaModule(definition)) {
            super.keysFor(list, definition, el);
        } else {
            final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
            addKey(list, getKeys(rootDefinitionName, ignored -> true, definition, el));
            addKey(list, getKeys(rootDefinitionName, d -> !(d instanceof DetailDescriptor), definition, el));
            addKey(list, getKeys(keyGeneratorUtil.getFallbackName(definition), ignored -> true, definition, el));
            addKey(list, getKeys(keyGeneratorUtil.getFallbackName(definition), d -> !(d instanceof DetailDescriptor), definition, el));
        }
    }

    protected String[] getKeys(final String name, final Predicate<Object> filter, final EditorPropertyDefinition definition, final AnnotatedElement el) {
        final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
        return Stream.of(
                Stream.of(name),
                keyPrefixer.getKeyPrefix(definition),
                Stream.of("field"),
                streamNames(filter, definition).filter(Predicate.not(rootDefinitionName::equals)),
                Stream.of(
                        replaceColons(definition.getName()),
                        fieldOrGetterName(el)
                )
        ).flatMap(Function.identity()).sequential().toArray(String[]::new);
    }

    private Stream<String> streamNames(final Predicate<Object> filter, final Object definition) {
        final List<Object> ancestors = getAncestors(definition);
        Collections.reverse(ancestors);
        return ancestors.stream()
                .filter(ancestor ->
                        filter.test(ancestor) && keyGeneratorUtil.getExcludedAncestors().stream().noneMatch(excluded -> excluded.isInstance(ancestor))
                )
                .filter(NamedDefinition.class::isInstance)
                .map(NamedDefinition.class::cast)
                .map(NamedDefinition::getName)
                .distinct();
    }
}
