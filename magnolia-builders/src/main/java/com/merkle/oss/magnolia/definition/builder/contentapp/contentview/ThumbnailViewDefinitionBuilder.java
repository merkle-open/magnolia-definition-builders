package com.merkle.oss.magnolia.definition.builder.contentapp.contentview;

import info.magnolia.ui.contentapp.configuration.ThumbnailViewDefinition;

import java.util.Optional;

public class ThumbnailViewDefinitionBuilder<T> extends AbstractContentViewDefinitionBuilder<T, ThumbnailViewDefinition<T>, ThumbnailViewDefinitionBuilder<T>> {

    public ThumbnailViewDefinitionBuilder() {}
    public ThumbnailViewDefinitionBuilder(final ThumbnailViewDefinition<T> definition) {
        super(definition);
    }

    public ThumbnailViewDefinition<T> build() {
        final ThumbnailViewDefinition<T> definition = new ThumbnailViewDefinition<>();
        Optional.ofNullable(implementationClass).ifPresent(definition::setImplementationClass);
        Optional.ofNullable(name).ifPresent(definition::setName);
        Optional.ofNullable(icon).ifPresent(definition::setIcon);
        Optional.ofNullable(multiSelect).ifPresent(definition::setMultiSelect);
        Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
        return definition;
    }
}
