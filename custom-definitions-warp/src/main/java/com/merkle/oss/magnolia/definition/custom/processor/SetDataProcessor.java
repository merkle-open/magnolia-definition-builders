package com.merkle.oss.magnolia.definition.custom.processor;

import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.editor.ComplexPropertyDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.warp.engine.form.content.processor.CompositeDataProcessor;
import info.magnolia.warp.engine.form.content.processor.DelegatingDataProcessor;
import info.magnolia.warp.engine.form.content.provider.ContentHandlerContext;

import java.util.Map;

import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinition;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoSetDefinition;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

/**
 * Depends on <a href="https://magnolia-cms.atlassian.net/browse/WARPFORM-1022">...</a>.
 */
public class SetDataProcessor<
        M extends Map<String, M>
        > extends CompositeDataProcessor<Node, M, ComplexPropertyDefinition<?>> {

    @Inject
    public SetDataProcessor(Provider<ContentHandlerContext> contentHandlerContext, ComponentProvider componentProvider, Provider<DelegatingDataProcessor> delegatingDataProcessor) {
        super(contentHandlerContext, componentProvider, delegatingDataProcessor);
    }

    @Override
    public boolean supports(EditorPropertyDefinition editorDefinition) {
        return editorDefinition instanceof VideoSetDefinition || editorDefinition instanceof ImageSetDefinition;
    }
}
