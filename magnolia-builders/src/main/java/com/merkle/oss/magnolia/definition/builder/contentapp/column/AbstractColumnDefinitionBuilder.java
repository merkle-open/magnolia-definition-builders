package com.merkle.oss.magnolia.definition.builder.contentapp.column;

import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;
import info.magnolia.ui.field.FieldDefinition;

import java.util.Optional;

import com.vaadin.data.ValueProvider;
import com.vaadin.ui.renderers.AbstractRenderer;

import jakarta.annotation.Nullable;

public class AbstractColumnDefinitionBuilder<T, D extends ConfiguredColumnDefinition<T>, B extends AbstractColumnDefinitionBuilder<T, D, B>> {
    @Nullable
    private Class<? extends AbstractRenderer> renderer;
    @Nullable
    private Class<T> type;
    @Nullable
    private Class<? extends ValueProvider<T, ?>> valueProvider;
    @Nullable
    private Integer expandRatio;
    @Nullable
    private Double width;
    @Nullable
    private Double minimumWidth;
    @Nullable
    private Double maximumWidth;
    @Nullable
    private Boolean minimumWidthFromContent;
    @Nullable
    private Boolean editable;
    @Nullable
    private FieldDefinition<?> filterComponent;
    @Nullable
    private Boolean sortable;
    @Nullable
    private String label;

    public AbstractColumnDefinitionBuilder() {}

    public AbstractColumnDefinitionBuilder(final D definition) {
        renderer(definition.getRenderer());
        type(definition.getType());
        Optional.ofNullable(definition.getValueProvider()).ifPresent(this::valueProvider);
        expandRatio(definition.getExpandRatio());
        width(definition.getWidth());
        minimumWidth(definition.getMinimumWidth());
        maximumWidth(definition.getMaximumWidth());
        minimumWidthFromContent(definition.isMinimumWidthFromContent());
        editable(definition.isEditable());
        Optional.ofNullable(definition.getFilterComponent()).ifPresent(this::filterComponent);
        sortable(definition.isSortable());
        Optional.ofNullable(definition.getLabel()).ifPresent(this::label);
    }

    public B renderer(final Class<? extends AbstractRenderer> renderer) {
        this.renderer = renderer;
        return self();
    }

    public B type(final Class<T> type) {
        this.type = type;
        return self();
    }

    public B valueProvider(final Class<? extends ValueProvider<T, ?>> valueProvider) {
        this.valueProvider = valueProvider;
        return self();
    }

    public B width(final double width) {
        this.width = width;
        return self();
    }

    public B minimumWidth(final double minimumWidth) {
        this.minimumWidth = minimumWidth;
        return self();
    }

    public B maximumWidth(final double maximumWidth) {
        this.maximumWidth = maximumWidth;
        return self();
    }

    public B minimumWidthFromContent(final boolean minimumWidthFromContent) {
        this.minimumWidthFromContent = minimumWidthFromContent;
        return self();
    }

    public B editable(final boolean editable) {
        this.editable = editable;
        return self();
    }

    public B filterComponent(final FieldDefinition<?> filterComponent) {
        this.filterComponent = filterComponent;
        return self();
    }

    public B sortable(final boolean sortable) {
        this.sortable = sortable;
        return self();
    }

    public B expandRatio(final int expandRatio) {
        this.expandRatio = expandRatio;
        return self();
    }

    public B label(final String label) {
        this.label = label;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    protected void populate(final D definition, final String name) {
        definition.setName(name);
        Optional.ofNullable(renderer).ifPresent(definition::setRenderer);
        Optional.ofNullable(type).ifPresent(definition::setType);
        Optional.ofNullable(valueProvider).ifPresent(definition::setValueProvider);
        Optional.ofNullable(expandRatio).ifPresent(definition::setExpandRatio);
        Optional.ofNullable(width).ifPresent(definition::setWidth);
        Optional.ofNullable(minimumWidth).ifPresent(definition::setMinimumWidth);
        Optional.ofNullable(maximumWidth).ifPresent(definition::setMaximumWidth);
        Optional.ofNullable(minimumWidthFromContent).ifPresent(definition::setMinimumWidthFromContent);
        Optional.ofNullable(editable).ifPresent(definition::setEditable);
        Optional.ofNullable(filterComponent).ifPresent(definition::setFilterComponent);
        Optional.ofNullable(sortable).ifPresent(definition::setSortable);
        Optional.ofNullable(label).ifPresent(definition::setLabel);
    }
}
