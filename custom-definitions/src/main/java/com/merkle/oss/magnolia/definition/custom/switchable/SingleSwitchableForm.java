package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;

import java.util.List;

public class SingleSwitchableForm<T extends ConfiguredFieldDefinition<?>> extends SingleForm<T> implements SwitchableForm {

	public SingleSwitchableForm(final T field) {
		super(field);
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		getField().setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() {
		return getField().isReadOnly();
	}

	@Override
	public void setI18n(final boolean i18n) {
		super.setI18n(i18n);
		getField().setI18n(i18n);
	}

	@Override
	public void setRequired(final boolean required) {
		getField().setRequired(required);
	}

	@Override
	public boolean isRequired() {
		return getField().isRequired();
	}

	@Override
	public void setValidators(final List<FieldValidatorDefinition> validators){
		getField().setValidators(validators);
	}

	@Override
	public List<FieldValidatorDefinition> getValidators() {
		return getField().getValidators();
	}
}
