package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.ui.chooser.definition.ChooserDefinition;
import info.magnolia.ui.contentapp.configuration.column.ColumnDefinition;

import java.lang.reflect.AnnotatedElement;
import java.util.List;
import java.util.Optional;

public class ColumnDefinitionKeyGenerator extends info.magnolia.ui.contentapp.configuration.column.ColumnDefinitionKeyGenerator {

    @Override
    protected void keysFor(
            final List<String> list,
            final ColumnDefinition definition,
            final AnnotatedElement el
    ) {
        super.keysFor(list, definition, el);
        getChooserDialogDefinition(definition).ifPresent(dialogName ->
                addKey(list, getKeys(dialogName, definition, el))
        );
    }

    private Optional<? extends ChooserDefinition<?, ?>> getChooserDialogDefinition(final ColumnDefinition<?> definition) {
        return getAncestors(definition).stream()
                .filter(ChooserDefinition.class::isInstance)
                .map(chooser -> (ChooserDefinition<?,?>) chooser)
                .filter(chooser -> chooser.getId() != null)
                .findAny();
    }

    protected String[] getKeys(final ChooserDefinition<?, ?> chooser, final ColumnDefinition<?> definition, final AnnotatedElement el) {
        return new String[] {
                getModuleName(chooser.getId()),
                getIdWithoutModuleName(chooser.getId()),
                "views",
                replaceColons(definition.getName()),
                fieldOrGetterName(el)
        };
    }
}