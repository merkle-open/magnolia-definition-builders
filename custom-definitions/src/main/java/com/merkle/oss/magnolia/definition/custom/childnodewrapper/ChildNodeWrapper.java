package com.merkle.oss.magnolia.definition.custom.childnodewrapper;

import info.magnolia.ui.editor.EditorView;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.editor.FormView;
import info.magnolia.ui.editor.ItemProviderDefinition;
import info.magnolia.ui.editor.JcrChildNodeProviderDefinition;
import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;
import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;
import info.magnolia.ui.framework.layout.SingleFieldLayoutProducer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.jcr.Node;

public class ChildNodeWrapper<T extends EditorPropertyDefinition> extends ConfiguredComplexPropertyDefinition<Node> implements FormDefinition<Node> {
    private final JcrChildNodeProviderDefinition nodeProvider = new JcrChildNodeProviderDefinition();
    private final String name;
    private final T field;

    public ChildNodeWrapper(final String nodeName, final T field) {
        this.field = field;
        this.name = nodeName + "." + field.getName();
        this.nodeProvider.setSupportI18N(false);
        this.nodeProvider.setNodeName(nodeName);
    }

    public T getField() {
        return field;
    }

    @Override
    public Class<? extends EditorView<Node>> getImplementationClass() {
        return (Class) FormView.class;
    }

    @Override
    public boolean isI18n() {
        return field.isI18n();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemProviderDefinition<Node, Node> getItemProvider() {
        return nodeProvider;
    }

    @Override
    public List<EditorPropertyDefinition> getProperties() {
        return List.of(field);
    }

    @Override
    public FieldLayoutDefinition<SingleFieldLayoutProducer> getLayout() {
        return new SingleFieldLayoutProducer.Definition();
    }

    public static List<EditorPropertyDefinition> wrap(final String nodeName, final ConfiguredFieldDefinition<?>... fields) {
        return wrap(nodeName, Arrays.stream(fields)).collect(Collectors.toList());
    }
    public static List<EditorPropertyDefinition> wrap(final String nodeName, final List<ConfiguredFieldDefinition<?>> fields) {
        return wrap(nodeName, fields.stream()).collect(Collectors.toList());
    }

    public static Stream<EditorPropertyDefinition> wrap(final String nodeName, final Stream<ConfiguredFieldDefinition<?>> fields) {
        return fields.map(field -> new ChildNodeWrapper<>(nodeName, field));
    }
}