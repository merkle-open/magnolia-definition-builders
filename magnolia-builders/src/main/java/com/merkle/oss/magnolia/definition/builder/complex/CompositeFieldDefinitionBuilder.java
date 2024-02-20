package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.field.CompositeFieldDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.WithPropertyNameDecorator;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * builds a {@link CompositeFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Composite-field.html">magnolia Docs - Composite field </a>
 * @author Merkle DACH
 */
public class CompositeFieldDefinitionBuilder<T> extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, CompositeFieldDefinition<T>, CompositeFieldDefinitionBuilder<T>> {
	@Nullable
	private List<EditorPropertyDefinition> properties;
	@Nullable
	private FieldLayoutDefinition<?> layout;
	@Nullable
	private Class<? extends WithPropertyNameDecorator.PropertyNameDecorator> propertyNameDecorator;

	public CompositeFieldDefinitionBuilder() {
		super(CompositeFieldDefinition::new);
	}

	public CompositeFieldDefinitionBuilder<T> layout(final FieldLayoutDefinition<?> layout) {
		this.layout = layout;
		return self();
	}

	public CompositeFieldDefinitionBuilder<T> propertyNameDecorator(final Class<? extends WithPropertyNameDecorator.PropertyNameDecorator> propertyNameDecorator) {
		this.propertyNameDecorator = propertyNameDecorator;
		return self();
	}

	public CompositeFieldDefinitionBuilder<T> property(final EditorPropertyDefinition property) {
		return properties(Stream.concat(
				Stream.ofNullable(properties).flatMap(Collection::stream),
				Stream.of(property)
		).collect(Collectors.toList()));
	}

	public CompositeFieldDefinitionBuilder<T> properties(final List<EditorPropertyDefinition> properties) {
		this.properties = properties;
		return self();
	}

	public CompositeFieldDefinition<T> build(final String name) {
		final CompositeFieldDefinition<T> definition = super.build(name);
		Optional.ofNullable(layout).ifPresent(definition::setLayout);
		Optional.ofNullable(propertyNameDecorator).ifPresent(definition::setPropertyNameDecorator);
		Stream.ofNullable(properties).flatMap(Collection::stream).forEach(definition.getProperties()::add);
		return definition;
	}
}
