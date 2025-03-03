package com.merkle.oss.magnolia.definition.builder.contentapp.contentview;

import info.magnolia.ui.contentapp.configuration.TreeViewDefinition;

import java.util.Optional;

public class TreeViewDefinitionBuilder<T> extends AbstractGridViewDefinitionBuilder<T, TreeViewDefinition<T>, TreeViewDefinitionBuilder<T>> {

    public TreeViewDefinitionBuilder() {}
    public TreeViewDefinitionBuilder(final TreeViewDefinition<T> definition) {
        super(definition);
    }

    public TreeViewDefinition<T> build() {
        final TreeViewDefinition<T> definition = new TreeViewDefinition<>();
        Optional.ofNullable(implementationClass).ifPresent(definition::setImplementationClass);
        Optional.ofNullable(name).ifPresent(definition::setName);
        Optional.ofNullable(icon).ifPresent(definition::setIcon);
        Optional.ofNullable(multiSelect).ifPresent(definition::setMultiSelect);
        Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
        Optional.ofNullable(scrollToSelectedItem).ifPresent(definition::setScrollToSelectedItem);
        Optional.ofNullable(filterableColumns).ifPresent(definition::setFilterableColumns);

        Optional.ofNullable(columns).ifPresent(definition::setColumns);
        Optional.ofNullable(dropConstraint).ifPresent(definition::setDropConstraint);
        Optional.ofNullable(multiSelectByRowClick).ifPresent(definition::setMultiSelectByRowClick);
        return definition;
    }
}
