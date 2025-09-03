package com.merkle.oss.magnolia.definition.builder.contentapp.contentview;

import info.magnolia.ui.contentapp.configuration.ContentViewDefinition;
import info.magnolia.ui.contentapp.configuration.ExtensionViewDefinition;
import info.magnolia.ui.contentapp.configuration.WorkbenchDefinition;
import info.magnolia.ui.filteringapp.filter.FilterView;
import info.magnolia.ui.filteringapp.filter.FilterViewDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

public class WorkbenchDefinitionBuilder<T> {
    @Nullable
    private List<ContentViewDefinition<T>> contentViews;
    @Nullable
    private List<ExtensionViewDefinition> extensionViews;
    @Nullable
    private Boolean searchEnabled;
    @Nullable
    private String searchPlaceholder;
    @Nullable
    private Boolean sortFilterOptions;
    @Nullable
    private List<FilterViewDefinition<? extends FilterView>> filters;

    public WorkbenchDefinitionBuilder<T> contentView(final ContentViewDefinition<T> contentView) {
        return contentViews(Stream.concat(
                Stream.ofNullable(contentViews).flatMap(Collection::stream),
                Stream.of(contentView)
        ).collect(Collectors.toList()));
    }
    public WorkbenchDefinitionBuilder<T> contentViews(final List<ContentViewDefinition<T>> contentViews) {
        this.contentViews = contentViews;
        return this;
    }

    public WorkbenchDefinitionBuilder<T> extensionView(final ExtensionViewDefinition<?> extensionView) {
        return extensionViews(Stream.concat(
                Stream.ofNullable(extensionViews).flatMap(Collection::stream),
                Stream.of(extensionView)
        ).collect(Collectors.toList()));
    }
    public WorkbenchDefinitionBuilder<T> extensionViews(final List<ExtensionViewDefinition> extensionViews) {
        this.extensionViews = extensionViews;
        return this;
    }

    public WorkbenchDefinitionBuilder<T> filter(final FilterViewDefinition<? extends FilterView> filter) {
        return filters(Stream.concat(
                Stream.ofNullable(filters).flatMap(Collection::stream),
                Stream.of(filter)
        ).collect(Collectors.toList()));
    }
    public WorkbenchDefinitionBuilder<T> filters(final List<FilterViewDefinition<? extends FilterView>> filters) {
        this.filters = filters;
        return this;
    }

    public WorkbenchDefinitionBuilder<T> searchEnabled(final boolean searchEnabled) {
        this.searchEnabled = searchEnabled;
        return this;
    }

    public WorkbenchDefinitionBuilder<T> searchPlaceholder(final String searchPlaceholder) {
        this.searchPlaceholder = searchPlaceholder;
        return this;
    }

    public WorkbenchDefinitionBuilder<T> sortFilterOptions(final boolean sortFilterOptions) {
        this.sortFilterOptions = sortFilterOptions;
        return this;
    }

    public WorkbenchDefinition<T> build() {
        final WorkbenchDefinition<T> definition = new WorkbenchDefinition<>();
        Optional.ofNullable(contentViews).ifPresent(definition::setContentViews);
        Optional.ofNullable(extensionViews).ifPresent(definition::setExtensionViews);
        Optional.ofNullable(filters).ifPresent(definition::setFilters);
        Optional.ofNullable(searchEnabled).ifPresent(definition::setSearchEnabled);
        Optional.ofNullable(searchPlaceholder).ifPresent(definition::setSearchPlaceholder);
        Optional.ofNullable(sortFilterOptions).ifPresent(definition::setSortFilterOptions);
        return definition;
    }
}
