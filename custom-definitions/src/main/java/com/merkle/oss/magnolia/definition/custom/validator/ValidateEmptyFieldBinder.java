package com.merkle.oss.magnolia.definition.custom.validator;

import static info.magnolia.util.Functions.defaultCombiner;

import info.magnolia.objectfactory.Components;
import info.magnolia.ui.field.FieldBinder;
import info.magnolia.ui.field.FieldDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.LinkFieldBinder;
import info.magnolia.ui.field.MultiValueFieldBinder;
import info.magnolia.ui.field.SelectFieldBinder;
import info.magnolia.ui.field.TextFieldBinder;

import java.util.List;
import java.util.Objects;

import jakarta.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;

public class ValidateEmptyFieldBinder {
    public static class Default<T> extends Wrapper<T> {
        @Inject
        public Default(final FieldBinder.Default<T> wrapped) {
            super(wrapped);
        }
    }

    public static class Text<T> extends Wrapper<T> {
        @Inject
        public Text(final TextFieldBinder<T> wrapped) {
            super(wrapped);
        }
    }

    public static class Link<T> extends Wrapper<T> {
        @Inject
        public Link(final LinkFieldBinder<T> wrapped) {
            super(wrapped);
        }
    }

    public static class Multi<T> extends Wrapper<List<T>> {
        @Inject
        public Multi(final MultiValueFieldBinder<T> wrapped) {
            super(wrapped);
        }
    }

    public static class Select<T> extends Wrapper<T> {
        @Inject
        public Select(final SelectFieldBinder<T> wrapped) {
            super(wrapped);
        }
    }


    private static class Wrapper<T> implements FieldBinder<T> {
        private final FieldBinder<T> wrapped;

        private Wrapper(final FieldBinder<T> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public <BT> Binder.BindingBuilder<BT, T> configureBinding(final FieldDefinition<T> fieldDefinition, final Binder.BindingBuilder<BT, T> source) {
            final Binder.BindingBuilder<BT, T> result = wrapped.configureBinding(fieldDefinition, source);
            final Object emptyValue = result.getField().getEmptyValue();
            return fieldDefinition.getValidators().stream()
                    .map(validatorDefinition -> newValidator(validatorDefinition, fieldDefinition, result.getField()))
                    .reduce(result, (bttBindingBuilder, validator) ->
                            bttBindingBuilder.withValidator((value, context) -> {
                                final Object inputValue = result.getField().getValue();
                                if (!Objects.equals(inputValue, emptyValue)) {
                                    return ValidationResult.ok();
                                }
                                return validator.apply(inputValue, context);
                            }), defaultCombiner());
        }

        private <VT> Validator<VT> newValidator(final FieldValidatorDefinition validatorDefinition, final Object... parameters) {
            return Components
                    .newInstance(validatorDefinition.getFactoryClass(), ArrayUtils.add(parameters, validatorDefinition))
                    .createValidator();
        }
    }
}
