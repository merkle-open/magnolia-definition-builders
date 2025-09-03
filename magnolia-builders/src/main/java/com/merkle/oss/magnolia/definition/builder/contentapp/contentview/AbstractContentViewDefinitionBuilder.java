package com.merkle.oss.magnolia.definition.builder.contentapp.contentview;

import info.magnolia.ui.contentapp.configuration.ContentViewDefinition;
import info.magnolia.ui.contentapp.browser.ContentView;

import java.util.Optional;

import jakarta.annotation.Nullable;

public class AbstractContentViewDefinitionBuilder<T, D extends ContentViewDefinition<T>, B extends AbstractContentViewDefinitionBuilder<T, D, B>> {
    @Nullable
    protected Class<? extends ContentView> implementationClass;
    @Nullable
    protected String name;
    @Nullable
    protected String icon;
    @Nullable
    protected Boolean multiSelect;
    @Nullable
    protected Boolean readOnly;
    @Nullable
    protected Boolean scrollToSelectedItem;
    @Nullable
    protected Boolean filterableColumns;

    public AbstractContentViewDefinitionBuilder() {}
    public AbstractContentViewDefinitionBuilder(final D definition) {
        Optional.ofNullable(definition.getImplementationClass()).ifPresent(this::implementationClass);
        Optional.ofNullable(definition.getIcon()).ifPresent(this::icon);
        multiSelect(definition.isMultiSelect());
        readOnly(definition.isReadOnly());
        scrollToSelectedItem(definition.isScrollToSelectedItem());
    }

    public B implementationClass(final Class<? extends ContentView> implementationClass) {
        this.implementationClass = implementationClass;
        return self();
    }

    public B name(final String name) {
        this.name = name;
        return self();
    }

    public B icon(final String icon) {
        this.icon = icon;
        return self();
    }

    public B multiSelect(final boolean multiSelect) {
        this.multiSelect = multiSelect;
        return self();
    }

    public B readOnly(final boolean readOnly) {
        this.readOnly = readOnly;
        return self();
    }

    public B scrollToSelectedItem(final boolean scrollToSelectedItem) {
        this.scrollToSelectedItem = scrollToSelectedItem;
        return self();
    }

    public B filterableColumns(final boolean filterableColumns) {
        this.filterableColumns = filterableColumns;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }
}
