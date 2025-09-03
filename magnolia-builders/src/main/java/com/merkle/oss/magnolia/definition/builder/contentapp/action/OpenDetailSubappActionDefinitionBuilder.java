package com.merkle.oss.magnolia.definition.builder.contentapp.action;

import info.magnolia.ui.contentapp.action.OpenDetailSubappActionDefinition;
import info.magnolia.ui.contentapp.detail.ContentDetailSubApp;

import java.util.Optional;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.action.AbstractActionDefinitionBuilder;

public class OpenDetailSubappActionDefinitionBuilder extends AbstractActionDefinitionBuilder<OpenDetailSubappActionDefinition, OpenDetailSubappActionDefinitionBuilder> {
    @Nullable
    private ViewType viewType;

    public OpenDetailSubappActionDefinitionBuilder() {}
    public OpenDetailSubappActionDefinitionBuilder(final OpenDetailSubappActionDefinition definition) {
        super(definition);
    }

    public OpenDetailSubappActionDefinitionBuilder viewType(final ViewType viewType) {
        this.viewType = viewType;
        return self();
    }

    public OpenDetailSubappActionDefinition build(final String name,  final String appName, final String subAppName) {
        final OpenDetailSubappActionDefinition definition = new OpenDetailSubappActionDefinition();
        populate(definition, name);
        definition.setAppName(appName);
        definition.setSubAppName(subAppName);
        Optional.ofNullable(viewType).map(ViewType::getViewType).ifPresent(definition::setViewType);
        return definition;
    }

    public enum ViewType {
        ADD(ContentDetailSubApp.VIEW_TYPE_ADD),
        EDIT(ContentDetailSubApp.VIEW_TYPE_EDIT),
        VIEW(ContentDetailSubApp.VIEW_TYPE_VIEW),
        VERSION(ContentDetailSubApp.VIEW_TYPE_VERSION);
        private final String viewType;
        ViewType(final String viewType) {
            this.viewType = viewType;
        }
        private String getViewType() {
            return viewType;
        }
    }
}
