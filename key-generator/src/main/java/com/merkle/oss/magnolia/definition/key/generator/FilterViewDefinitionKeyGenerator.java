package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.objectfactory.Components;
import info.magnolia.ui.chooser.definition.ChooserDefinition;
import info.magnolia.ui.filteringapp.filter.FilterViewDefinition;

import java.lang.reflect.AnnotatedElement;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jakarta.inject.Inject;

public class FilterViewDefinitionKeyGenerator extends info.magnolia.ui.filteringapp.filter.FilterViewDefinitionKeyGenerator {
    private final KeyGeneratorUtil keyGeneratorUtil;

    public FilterViewDefinitionKeyGenerator() {
        // can't inject anything in keyGenerators -.- see I18nKeyGeneratorFactory
        this(Components.getComponent(KeyGeneratorUtil.class));
    }

    @Inject
    public FilterViewDefinitionKeyGenerator(final KeyGeneratorUtil keyGeneratorUtil) {
        this.keyGeneratorUtil = keyGeneratorUtil;
    }

    @Override
    protected void keysFor(
            final List<String> list,
            final FilterViewDefinition<?> definition,
            final AnnotatedElement el
    ) {
        if (keyGeneratorUtil.isMagnoliaModule(definition)) {
            super.keysFor(list, definition, el);
        } else {
            final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
            addKey(list, getKeys(rootDefinitionName, ignored -> true, definition, el));
            addKey(list, getKeys(rootDefinitionName, d -> !(d instanceof ChooserDefinition), definition, el));
            addKey(list, "filters", definition.getName(), fieldOrGetterName(el));
        }
    }

    protected String[] getKeys(final String name, final Predicate<Object> filter, final FilterViewDefinition<?> definition, final AnnotatedElement el) {
        final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
        return Stream.of(
                Stream.of(name),
                keyGeneratorUtil.streamAncestorNames(filter, definition).filter(Predicate.not(rootDefinitionName::equals)),
                Stream.of("filters"),
                Stream.of(
                        replaceColons(definition.getName()),
                        fieldOrGetterName(el)
                )
        ).flatMap(Function.identity()).sequential().toArray(String[]::new);
    }
}
