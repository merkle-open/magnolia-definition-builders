package com.merkle.oss.magnolia.definition.custom.contentapp.filter.propertysearch;

import info.magnolia.i18nsystem.I18nText;
import info.magnolia.ui.filter.FilterOperator;
import info.magnolia.ui.filteringapp.configuration.ConfiguredFilterViewDefinition;

import java.util.Optional;

import com.merkle.oss.magnolia.definition.builder.contentapp.filter.AbstractConfiguredFilterViewDefinitionBuilder;

import jakarta.annotation.Nullable;

public class PropertySearchFilterViewDefinition extends ConfiguredFilterViewDefinition<PropertySearchFilterView> {
    private FilterOperator filterOperator = FilterOperator.CONTAINS;
    private String placeholder;
    private int minInputLength = 3;

    public PropertySearchFilterViewDefinition(final String name) {
        setImplementationClass(PropertySearchFilterView.class);
        setName(name);
    }

    public void setFilterOperator(final FilterOperator filterOperator) {
        this.filterOperator = filterOperator;
    }
    public FilterOperator getFilterOperator() {
        return filterOperator;
    }

    public void setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
    }
    @I18nText
    public String getPlaceholder() {
        return placeholder;
    }

    public void setMinInputLength(final int minInputLength) {
        this.minInputLength = minInputLength;
    }
    public int getMinInputLength() {
        return minInputLength;
    }

    public static class Builder extends AbstractConfiguredFilterViewDefinitionBuilder<PropertySearchFilterView, PropertySearchFilterViewDefinition, Builder> {
        @Nullable
        private FilterOperator filterOperator;
        @Nullable
        private String placeholder;
        @Nullable
        private Integer minInputLength;

        public Builder() {}
        public Builder(final PropertySearchFilterViewDefinition definition) {
            super(definition);
            Optional.ofNullable(definition.getFilterOperator()).ifPresent(this::filterOperator);
            Optional.ofNullable(definition.getPlaceholder()).ifPresent(this::placeholder);
        }

        public Builder filterOperator(final FilterOperator filterOperator) {
            this.filterOperator = filterOperator;
            return self();
        }

        public Builder placeholder(final String placeholder) {
            this.placeholder = placeholder;
            return self();
        }

        public Builder minInputLength(final int minInputLength) {
            this.minInputLength = minInputLength;
            return self();
        }

        public PropertySearchFilterViewDefinition build(final String name) {
            final PropertySearchFilterViewDefinition definition = new PropertySearchFilterViewDefinition(name);
            super.populate(definition, name);
            Optional.ofNullable(filterOperator).ifPresent(definition::setFilterOperator);
            Optional.ofNullable(placeholder).ifPresent(definition::setPlaceholder);
            Optional.ofNullable(minInputLength).ifPresent(definition::setMinInputLength);
            return definition;
        }
    }
}
