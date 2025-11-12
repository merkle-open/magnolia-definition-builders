package com.merkle.oss.magnolia.definition.builder.contentapp.action;

import info.magnolia.ui.contentapp.action.OpenLocationActionDefinition;

import java.util.Optional;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.action.AbstractActionDefinitionBuilder;

public class OpenLocationActionDefinitionBuilder extends AbstractActionDefinitionBuilder<OpenLocationActionDefinition, OpenLocationActionDefinitionBuilder> {
    @Nullable
    private String appType;
    @Nullable
    private String appName;
    @Nullable
    private String subAppId;
    @Nullable
    private String parameter;

    public OpenLocationActionDefinitionBuilder() {}
    public OpenLocationActionDefinitionBuilder(final OpenLocationActionDefinition definition) {
        super(definition);
        Optional.ofNullable(definition.getAppType()).ifPresent(this::appType);
        Optional.ofNullable(definition.getAppName()).ifPresent(this::appName);
        Optional.ofNullable(definition.getSubAppId()).ifPresent(this::subAppId);
        Optional.ofNullable(definition.getParameter()).ifPresent(this::parameter);
    }

    public OpenLocationActionDefinitionBuilder appType(final String appType) {
        this.appType = appType;
        return self();
    }
    public OpenLocationActionDefinitionBuilder appName(final String appName) {
        this.appName = appName;
        return self();
    }
    public OpenLocationActionDefinitionBuilder subAppId(final String subAppId) {
        this.subAppId = subAppId;
        return self();
    }
    public OpenLocationActionDefinitionBuilder parameter(final String parameter) {
        this.parameter = parameter;
        return self();
    }

    public OpenLocationActionDefinition build(final String name) {
        final OpenLocationActionDefinition definition = new OpenLocationActionDefinition();
        populate(definition, name);
        Optional.ofNullable(appType).ifPresent(definition::setAppType);
        Optional.ofNullable(appName).ifPresent(definition::setAppName);
        Optional.ofNullable(subAppId).ifPresent(definition::setSubAppId);
        Optional.ofNullable(parameter).ifPresent(definition::setParameter);
        return definition;
    }
}
