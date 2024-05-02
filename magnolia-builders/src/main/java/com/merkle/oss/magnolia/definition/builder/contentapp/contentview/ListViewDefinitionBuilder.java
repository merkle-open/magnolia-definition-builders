package com.merkle.oss.magnolia.definition.builder.contentapp.contentview;

import info.magnolia.ui.contentapp.configuration.ListViewDefinition;

import java.util.Optional;

public class ListViewDefinitionBuilder<T> extends AbstractGridViewDefinitionBuilder<T, ListViewDefinition<T>, ListViewDefinitionBuilder<T>> {

    public ListViewDefinitionBuilder() {}
    public ListViewDefinitionBuilder(final ListViewDefinition<T> definition) {
        super(definition);
    }

    public ListViewDefinition<T> build() {
        final ListViewDefinition<T> definition = new ListViewDefinition<>();
        Optional.ofNullable(implementationClass).ifPresent(definition::setImplementationClass);
        Optional.ofNullable(name).ifPresent(definition::setName);
        Optional.ofNullable(icon).ifPresent(definition::setIcon);
        Optional.ofNullable(multiSelect).ifPresent(definition::setMultiSelect);
        Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
        Optional.ofNullable(scrollToSelectedItem).ifPresent(definition::setScrollToSelectedItem);

        Optional.ofNullable(columns).ifPresent(definition::setColumns);
        Optional.ofNullable(dropConstraint).ifPresent(definition::setDropConstraint);
        Optional.ofNullable(multiSelectByRowClick).ifPresent(definition::setMultiSelectByRowClick);
        return definition;
    }
}
