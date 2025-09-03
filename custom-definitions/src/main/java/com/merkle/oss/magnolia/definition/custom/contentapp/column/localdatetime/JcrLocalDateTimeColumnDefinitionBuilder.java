package com.merkle.oss.magnolia.definition.custom.contentapp.column.localdatetime;

import java.util.Optional;

import jakarta.annotation.Nullable;
import javax.jcr.Item;

import com.merkle.oss.magnolia.definition.builder.contentapp.column.AbstractColumnDefinitionBuilder;

public class JcrLocalDateTimeColumnDefinitionBuilder extends AbstractColumnDefinitionBuilder<Item, JcrLocalDateTimeColumnDefinition, JcrLocalDateTimeColumnDefinitionBuilder> {
	@Nullable
	private String propertyName;

	public JcrLocalDateTimeColumnDefinitionBuilder() {}
	public JcrLocalDateTimeColumnDefinitionBuilder(final JcrLocalDateTimeColumnDefinition definition) {
		super(definition);
		this.propertyName = definition.getPropertyName();
	}

	public JcrLocalDateTimeColumnDefinitionBuilder propertyName(final String propertyName) {
		this.propertyName = propertyName;
		return self();
	}

	public JcrLocalDateTimeColumnDefinition build(final String name) {
		final JcrLocalDateTimeColumnDefinition definition = new JcrLocalDateTimeColumnDefinition();
		super.populate(definition, name);
		definition.setPropertyName(Optional.ofNullable(propertyName).orElse(name));
		return definition;
	}
}