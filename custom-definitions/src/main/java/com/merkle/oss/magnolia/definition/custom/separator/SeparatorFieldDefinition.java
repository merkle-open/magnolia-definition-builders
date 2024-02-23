package com.merkle.oss.magnolia.definition.custom.separator;

import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.EditorDefinition;
import info.magnolia.ui.editor.EditorView;
import info.magnolia.ui.editor.ItemProviderDefinition;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;
import info.magnolia.ui.field.FieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;


@FieldType("separatorField")
public class SeparatorFieldDefinition extends ConfiguredComplexPropertyDefinition<Object> implements EditorDefinition<Object> {
	private final String name = UUID.randomUUID().toString();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getLabel() {
		return StringUtils.EMPTY;
	}

	@Override
	public ItemProviderDefinition<Object, Object> getItemProvider() {
		return new CurrentItemProviderDefinition<>();
	}

	@Override
	public Class<? extends EditorView<Object>> getImplementationClass() {
		return SeparatorField.class;
	}
}