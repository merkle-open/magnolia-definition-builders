package com.merkle.oss.magnolia.definition.custom.contentapp.column.modificationdate;

import java.util.Optional;

import jakarta.annotation.Nullable;
import javax.jcr.Item;

import com.merkle.oss.magnolia.definition.builder.contentapp.column.AbstractColumnDefinitionBuilder;

public class ModificationDateColumnDefinitionBuilder extends AbstractColumnDefinitionBuilder<Item, ModificationDateColumnDefinition, ModificationDateColumnDefinitionBuilder> {
	@Nullable
	private String name;

	public ModificationDateColumnDefinitionBuilder() {}
	public ModificationDateColumnDefinitionBuilder(final ModificationDateColumnDefinition definition) {
		super(definition);
	}

	public ModificationDateColumnDefinitionBuilder name(final String name) {
		this.name = name;
		return self();
	}

	public ModificationDateColumnDefinition build() {
		final ModificationDateColumnDefinition definition = new ModificationDateColumnDefinition();
		super.populate(definition, Optional.ofNullable(name).orElseGet(definition::getName));
		return definition;
	}
}