package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.RadioButtonGroupFieldDefinition;
import info.magnolia.ui.field.SelectFieldSupport;
import info.magnolia.ui.field.factory.RadioButtonGroupFieldFactory;

import javax.inject.Inject;

import com.merkle.oss.magnolia.definition.builder.simple.RadioButtonGroupFieldDefinitionBuilder;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.RadioButtonGroup;

@StyleSheet("hidden-radio-button-group-style.css")
public class HiddenRadioButtonGroup<T> extends RadioButtonGroup<T> {
    public HiddenRadioButtonGroup() {
        setStyleName("hide-switchable-field");
    }

    public static class Factory<T> extends RadioButtonGroupFieldFactory<T> {
        @Inject
        public Factory(
                final RadioButtonGroupFieldDefinition<T> definition,
                final ComponentProvider componentProvider,
                final SelectFieldSupport<T> selectFieldSupport
        ) {
            super(definition, componentProvider, selectFieldSupport);
        }

        @Override
        protected RadioButtonGroup<T> createFieldComponent() {
            return new HiddenRadioButtonGroup<>();
        }
    }

    public static class Definition<T> extends RadioButtonGroupFieldDefinition<T> {
        public Definition() {
            setFactoryClass((Class)Factory.class);
        }

        public static class Builder<T> extends RadioButtonGroupFieldDefinitionBuilder<T> {
            public Builder() {}
            public Definition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
                final Definition<T> definition = new Definition<>();
                super.populate(definition, name, datasourceDefinition);
                return definition;
            }
        }
    }
}
