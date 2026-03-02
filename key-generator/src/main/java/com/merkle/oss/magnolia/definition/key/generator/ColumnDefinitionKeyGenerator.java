package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.objectfactory.Components;
import info.magnolia.ui.ViewDefinition;
import info.magnolia.ui.chooser.definition.ChooserDefinition;
import info.magnolia.ui.contentapp.configuration.column.ColumnDefinition;

import java.lang.reflect.AnnotatedElement;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jakarta.inject.Inject;

public class ColumnDefinitionKeyGenerator extends info.magnolia.ui.contentapp.configuration.column.ColumnDefinitionKeyGenerator {
    private final KeyGeneratorUtil keyGeneratorUtil;

    public ColumnDefinitionKeyGenerator() {
        // can't inject anything in keyGenerators -.- see I18nKeyGeneratorFactory
        this(Components.getComponent(KeyGeneratorUtil.class));
    }

    @Inject
    public ColumnDefinitionKeyGenerator(final KeyGeneratorUtil keyGeneratorUtil) {
        this.keyGeneratorUtil = keyGeneratorUtil;
    }

    @Override
    protected void keysFor(
            final List<String> list,
            final ColumnDefinition definition,
            final AnnotatedElement el
    ) {
        final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
        final String moduleId = keyGeneratorUtil.getRootDefinitionModuleId(definition);
        final String path = keyGeneratorUtil.getRootDefinitionPath(definition);
        addKey(list, keyGeneratorUtil.prepend(getKeys(rootDefinitionName, d -> !(d instanceof ViewDefinition<?>), definition, el), moduleId, path));
        addKey(list, getKeys(rootDefinitionName, ignored -> true, definition, el));
        addKey(list, getKeys(rootDefinitionName, d -> !(d instanceof ViewDefinition<?>), definition, el));
        super.keysFor(list, definition, el);
    }

    protected String[] getKeys(final String name, final Predicate<Object> filter, final ColumnDefinition<?> definition, final AnnotatedElement el) {
        final String rootDefinitionName = keyGeneratorUtil.getRootDefinitionName(definition);
        final Predicate<Object> defaultFilter = d -> !(d instanceof ChooserDefinition);
        return Stream.of(
                Stream.of(name),
                Stream.of("views"),
                keyGeneratorUtil.streamAncestorNames(defaultFilter.and(filter), definition).filter(Predicate.not(rootDefinitionName::equals)),
                Stream.of(
                        replaceColons(definition.getName()),
                        fieldOrGetterName(el)
                )
        ).flatMap(Function.identity()).sequential().toArray(String[]::new);
    }
}
