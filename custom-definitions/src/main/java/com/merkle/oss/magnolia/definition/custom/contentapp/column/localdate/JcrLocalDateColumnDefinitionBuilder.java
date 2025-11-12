package com.merkle.oss.magnolia.definition.custom.contentapp.column.localdate;

import java.util.Optional;

import jakarta.annotation.Nullable;
import javax.jcr.Item;

import com.merkle.oss.magnolia.definition.builder.contentapp.column.AbstractColumnDefinitionBuilder;

public class JcrLocalDateColumnDefinitionBuilder extends AbstractColumnDefinitionBuilder<Item, JcrLocalDateColumnDefinition, JcrLocalDateColumnDefinitionBuilder> {
	@Nullable
	private String propertyName;

	public JcrLocalDateColumnDefinitionBuilder() {}
	public JcrLocalDateColumnDefinitionBuilder(final JcrLocalDateColumnDefinition definition) {
		super(definition);
		this.propertyName = definition.getPropertyName();
	}

	public JcrLocalDateColumnDefinitionBuilder propertyName(final String propertyName) {
		this.propertyName = propertyName;
		return self();
	}

	public JcrLocalDateColumnDefinition build(final String name) {
		final JcrLocalDateColumnDefinition definition = new JcrLocalDateColumnDefinition();
		super.populate(definition, name);
		definition.setPropertyName(Optional.ofNullable(propertyName).orElse(name));
		return definition;
	}
}