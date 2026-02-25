package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.objectfactory.Components;
import info.magnolia.ui.contentapp.detail.DetailDescriptor;
import info.magnolia.ui.framework.layout.TabDefinition;

import java.lang.reflect.AnnotatedElement;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jakarta.inject.Inject;

public class TabDefinitionKeyGenerator extends info.magnolia.ui.editor.i18n.TabDefinitionKeyGenerator {

    private final KeyGeneratorUtil keyGeneratorUtil;

    public TabDefinitionKeyGenerator() {
        // can't inject anything in keyGenerators -.- see I18nKeyGeneratorFactory
        this(Components.getComponent(KeyGeneratorUtil.class));
    }

    @Inject
    public TabDefinitionKeyGenerator(final KeyGeneratorUtil keyGeneratorUtil) {
        this.keyGeneratorUtil = keyGeneratorUtil;
    }

    @Override
    protected void keysFor(
            final List<String> list,
            final TabDefinition definition,
            final AnnotatedElement el
    ) {
        if (keyGeneratorUtil.isMagnoliaModule(definition)) {
            super.keysFor(list, definition, el);
        } else {
            final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
            addKey(list, getKeys(rootDefinitionName, ignored -> true, definition, el));
            addKey(list, getKeys(rootDefinitionName, d -> !(d instanceof DetailDescriptor), definition, el));
            addKey(list, "tabs", definition.getName(), fieldOrGetterName(el));
        }
    }

    protected String[] getKeys(final String name, final Predicate<Object> filter, final TabDefinition definition, final AnnotatedElement el) {
        return Stream.of(
                Stream.of(name),
                Stream.of("tab"),
                keyGeneratorUtil.streamAncestorNames(filter, definition).filter(Predicate.not(name::equals)),
                Stream.of(
                        replaceColons(definition.getName()),
                        fieldOrGetterName(el)
                )
        ).flatMap(Function.identity()).sequential().toArray(String[]::new);
    }
}
