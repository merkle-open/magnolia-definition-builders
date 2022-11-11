package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.field.AbstractSelectFieldDefinition;
import info.magnolia.ui.field.ConfiguredSwitchableFieldDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfiguredSwitchableFieldDefinitionBuilder<T> extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, ConfiguredSwitchableFieldDefinition<T>, ConfiguredSwitchableFieldDefinitionBuilder<T>> {
	@Nullable
	private List<FormDefinition<T>> forms;
	@Nullable
	private Class<? extends WithPropertyNameDecorator.PropertyNameDecorator> propertyNameDecorator;

	public ConfiguredSwitchableFieldDefinitionBuilder() {
		super(ConfiguredSwitchableFieldDefinition::new);
	}

	public ConfiguredSwitchableFieldDefinitionBuilder<T> propertyNameDecorator(final Class<? extends WithPropertyNameDecorator.PropertyNameDecorator> propertyNameDecorator) {
		this.propertyNameDecorator = propertyNameDecorator;
		return self();
	}

	public ConfiguredSwitchableFieldDefinitionBuilder<T> form(final FormDefinition<T> form) {
		return forms(Stream.concat(
				Stream.ofNullable(forms).flatMap(Collection::stream),
				Stream.of(form)
		).collect(Collectors.toList()));
	}

	public ConfiguredSwitchableFieldDefinitionBuilder<T> forms(final List<FormDefinition<T>> forms) {
		this.forms = forms;
		return self();
	}

	public ConfiguredSwitchableFieldDefinition<T> build(final String name, final AbstractSelectFieldDefinition<Option, OptionListDefinition> field) {
		final ConfiguredSwitchableFieldDefinition<T> definition = super.build(name);
		definition.setField(field);
		Optional.ofNullable(propertyNameDecorator).map(Class.class::cast).ifPresent(definition::setPropertyNameDecorator);
		Stream.ofNullable(forms).flatMap(Collection::stream).forEach(definition.getForms()::add);
		return definition;
	}
}
