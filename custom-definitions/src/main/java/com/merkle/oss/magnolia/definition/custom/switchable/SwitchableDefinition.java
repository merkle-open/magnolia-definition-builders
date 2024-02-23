package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.editor.SwitchableFormDefinition;
import info.magnolia.ui.editor.SwitchableFormView;
import info.magnolia.ui.field.AbstractSelectFieldDefinition;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator.PropertyNameDecorator;

import javax.jcr.Node;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class SwitchableDefinition extends ConfiguredComplexPropertyDefinition<Node> implements SwitchableFormDefinition<Node> {
	private final Class<? extends PropertyNameDecorator> propertyNameDecoratorClass;
	private final AbstractSelectFieldDefinition optionsField; // Can't add generics because magnolia is stripping type info in AbstractOptionGroupFieldDefinition
	private final List<SwitchableForm> forms;
	private boolean readOnly;
	private boolean required;
	private List<FieldValidatorDefinition> validators;

	public SwitchableDefinition(
			final Class<? extends PropertyNameDecorator> propertyNameDecoratorClass,
			final AbstractSelectFieldDefinition optionsField,
			final List<SwitchableForm> forms
	) {
		this.propertyNameDecoratorClass = propertyNameDecoratorClass;
		this.optionsField = optionsField;
		this.forms = forms;
		setImplementationClass((Class) SwitchableFormView.class);
		setItemProvider(new CurrentItemProviderDefinition<>());
	}

	@Override
	public AbstractSelectFieldDefinition<Option, OptionListDefinition> getField() {
		return optionsField;
	}

	@Override
	public List<FormDefinition<Node>> getForms() {
		return List.copyOf(forms);
	}

	@Override
	public Class<PropertyNameDecorator> getPropertyNameDecorator() {
		return (Class) propertyNameDecoratorClass;
	}

	@Override
	public void setI18n(boolean i18n) {
		super.setI18n(i18n);
		applyField(field -> field.setI18n(i18n));
		applyForms(switchableForm -> switchableForm.setI18n(i18n));
	}

	public void setReadOnly(final boolean readOnly) {
		this.readOnly = readOnly;
		applyField(field -> field.setReadOnly(readOnly));
		applyForms(switchableForm -> switchableForm.setReadOnly(readOnly));
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setRequired(final boolean required) {
		this.required = required;
		applyField(field -> field.setRequired(required));
		applyForms(switchableForm -> switchableForm.setRequired(required));
	}

	public boolean isRequired() {
		return required;
	}

	public void setValidators(final List<FieldValidatorDefinition> validators) {
		this.validators = validators;
		applyForms(switchableForm -> switchableForm.setValidators(validators));
	}

	public List<FieldValidatorDefinition> getValidators() {
		return validators;
	}

	private void applyField(final Consumer<AbstractSelectFieldDefinition> fieldConsumer) {
		Optional.ofNullable(optionsField).ifPresent(fieldConsumer);
	}

	private void applyForms(final Consumer<SwitchableForm> fieldConsumer) {
		forms.forEach(fieldConsumer);
	}
}
