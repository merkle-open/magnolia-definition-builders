package com.merkle.oss.magnolia.definition.custom.processor;

import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldDefinition;
import info.magnolia.warp.engine.form.content.processor.ComplexDataProcessor;
import info.magnolia.warp.engine.form.content.processor.ScalarProcessor;
import info.magnolia.warp.engine.form.content.provider.ContentHandlerContext;

import java.util.Locale;
import java.util.Map;

import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.custom.childnodewrapper.ChildNodeWrapper;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class ChildNodeWrapperDataProcessor<
        MAP extends Map<String, MAP>
        > extends ComplexDataProcessor<Node, ChildNodeWrapper<FieldDefinition<Node>>, MAP> {

    private final ScalarProcessor<Node, FieldDefinition<Node>, MAP> scalarProcessor;


    @Inject
    ChildNodeWrapperDataProcessor(Provider<ContentHandlerContext> contentHandlerContext,
                                  ComponentProvider componentProvider,
                                  ScalarProcessor scalarProcessor) {
        super(contentHandlerContext, componentProvider);
        this.scalarProcessor = scalarProcessor;
    }

    @Override
    public boolean supports(EditorPropertyDefinition editorDefinition) {
        return editorDefinition instanceof ChildNodeWrapper<?>;
    }

    @Override
    public Map<String, MAP> read(Node item, ChildNodeWrapper<FieldDefinition<Node>> editorDefinition, Locale locale) {
        var subNode = provideTargetItem(editorDefinition, item, locale).orElseThrow();
        return Map.of(NodeUtil.getName(subNode), (MAP) scalarProcessor.read(subNode, editorDefinition.getField(), locale));
    }

    @Override
    public Node write(Node item, ChildNodeWrapper<FieldDefinition<Node>> editorDefinition, Map<String, MAP> content, Locale locale) {
        var subNode = provideTargetItem(editorDefinition, item, locale).orElseThrow();
        return scalarProcessor.write(subNode, editorDefinition.getField(), content.get(NodeUtil.getName(subNode)), locale);
    }
}
