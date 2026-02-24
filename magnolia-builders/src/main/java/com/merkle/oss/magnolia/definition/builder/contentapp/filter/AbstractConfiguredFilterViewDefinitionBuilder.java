package com.merkle.oss.magnolia.definition.builder.contentapp.filter;

import info.magnolia.ui.filteringapp.configuration.ConfiguredFilterViewDefinition;
import info.magnolia.ui.filteringapp.filter.FilterView;

import java.util.Optional;

import jakarta.annotation.Nullable;

public class AbstractConfiguredFilterViewDefinitionBuilder<T extends FilterView, D extends ConfiguredFilterViewDefinition<T>, B extends AbstractConfiguredFilterViewDefinitionBuilder<T, D, B>> {
    @Nullable
    private Boolean visible;
    @Nullable
    private Boolean removable;
    @Nullable
    private String icon;
    @Nullable
    private String label;
    @Nullable
    private String propertyName;

    public AbstractConfiguredFilterViewDefinitionBuilder() {}

    public AbstractConfiguredFilterViewDefinitionBuilder(final D definition) {
        visible(definition.isVisible());
        removable(definition.isRemovable());
        Optional.ofNullable(definition.getIcon()).ifPresent(this::icon);
        Optional.ofNullable(definition.getLabel()).ifPresent(this::label);
        Optional.ofNullable(definition.getPropertyName()).ifPresent(this::propertyName);
    }

    public B visible(final boolean visible) {
        this.visible = visible;
        return self();
    }

    public B removable(final boolean removable) {
        this.removable = removable;
        return self();
    }

    public B icon(final String icon) {
        this.icon = icon;
        return self();
    }

    public B label(final String label) {
        this.label = label;
        return self();
    }

    public B propertyName(final String propertyName) {
        this.propertyName = propertyName;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    protected void populate(final D definition, final String name) {
        definition.setName(name);
        Optional.ofNullable(visible).ifPresent(definition::setVisible);
        Optional.ofNullable(removable).ifPresent(definition::setRemovable);
        Optional.ofNullable(icon).ifPresent(definition::setIcon);
        Optional.ofNullable(label).ifPresent(definition::setLabel);
        Optional.ofNullable(propertyName).ifPresent(definition::setPropertyName);
    }
}
