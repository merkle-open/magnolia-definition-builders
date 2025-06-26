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

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.jcr.Node;

public class SwitchableDefinition extends ConfiguredComplexPropertyDefinition<Node> implements SwitchableFormDefinition<Node> {
	private final Class<? extends PropertyNameDecorator> propertyNameDecoratorClass;
	private final AbstractSelectFieldDefinition optionsField; // Can't add generics because magnolia is stripping type info in AbstractOptionGroupFieldDefinition
	private final List<SwitchableForm> forms;
	private final boolean fieldI18n;
	private boolean readOnly;
	private boolean required;

	public SwitchableDefinition(
			final Class<? extends PropertyNameDecorator> propertyNameDecoratorClass,
			final AbstractSelectFieldDefinition optionsField,
			final List<SwitchableForm> forms,
			final boolean fieldI18n
	) {
		this.propertyNameDecoratorClass = propertyNameDecoratorClass;
		this.optionsField = optionsField;
		this.forms = forms;
		this.fieldI18n = fieldI18n;
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
		if(fieldI18n) {
			super.setI18n(i18n);
			applyField(field -> field.setI18n(i18n));
		}
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
		applyForms(switchableForm -> switchableForm.setValidators(validators));
	}
	public List<FieldValidatorDefinition> getValidators() {
		return forms.stream().map(SwitchableForm::getValidators).flatMap(Collection::stream).collect(Collectors.toList());
	}
	public void setValidators(final String field, final List<FieldValidatorDefinition> validators) {
		getForm(field).setValidators(validators);
	}
	public List<FieldValidatorDefinition> getValidators(final String field) {
		return getForm(field).getValidators();
	}

	private void applyField(final Consumer<AbstractSelectFieldDefinition> fieldConsumer) {
		Optional.ofNullable(optionsField).ifPresent(fieldConsumer);
	}

	private void applyForms(final Consumer<SwitchableForm> fieldConsumer) {
		forms.forEach(fieldConsumer);
	}

	private SwitchableForm getForm(final String field) {
		return forms.stream().filter(form -> Objects.equals(field, form.getName())).findAny().orElseThrow(() ->
			new IllegalArgumentException("There's no field " + field + " in this switchable!")
		);
	}
}
