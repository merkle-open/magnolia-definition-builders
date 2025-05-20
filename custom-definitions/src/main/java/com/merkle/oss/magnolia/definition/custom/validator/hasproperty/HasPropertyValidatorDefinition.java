package com.merkle.oss.magnolia.definition.custom.validator.hasproperty;

import info.magnolia.i18nsystem.I18nable;
import info.magnolia.ui.field.ConfiguredFieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;
import info.magnolia.ui.field.ValidatorType;

import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;

@I18nable(keyGenerator = FieldValidatorDefinitionKeyGenerator.class)
@ValidatorType("hasPropertyValidator")
public class HasPropertyValidatorDefinition extends ConfiguredFieldValidatorDefinition {
    private final String propertyName;
    private final boolean i18n;

    public HasPropertyValidatorDefinition(
            final String propertyName,
            final boolean i18n
    ) {
        this.propertyName = propertyName;
        this.i18n = i18n;
    }

    @Override
    public Class<? extends FieldValidatorFactory<Node>> getFactoryClass() {
        return HasPropertyValidatorFactory.class;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public boolean isI18n() {
        return i18n;
    }
}
