package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.editor.FormView;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;
import info.magnolia.ui.framework.layout.StackedLayoutProducer;

import javax.jcr.Node;
import java.util.List;

public class SingleForm<T extends EditorPropertyDefinition> extends ConfiguredComplexPropertyDefinition<Node> implements FormDefinition<Node> {
	private final T field;

	public SingleForm(final T field) {
		this.field = field;
		setLabel("");
		setName(field.getName());
		setImplementationClass((Class) FormView.class);
		setItemProvider(new CurrentItemProviderDefinition<>());
	}

	public T getField() {
		return field;
	}

	@Override
	public List<EditorPropertyDefinition> getProperties() {
		return List.of(field);
	}

	@Override
	public FieldLayoutDefinition<?> getLayout() {
		return new StackedLayoutProducer.Definition();
	}
}
