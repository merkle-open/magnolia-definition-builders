package com.merkle.oss.magnolia.definition.builder.contentapp.action;

import info.magnolia.ui.api.action.ActionDefinition;
import info.magnolia.ui.contentapp.action.ChainedActionDefinition;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.action.AbstractActionDefinitionBuilder;

public class ChainedActionDefinitionBuilder extends AbstractActionDefinitionBuilder<ChainedActionDefinition, ChainedActionDefinitionBuilder> {
    @Nullable
    private Map<String, ActionDefinition> actions;

    public ChainedActionDefinitionBuilder() {}
    public ChainedActionDefinitionBuilder(final ChainedActionDefinition definition) {
        super(definition);
    }

    public ChainedActionDefinitionBuilder action(final String key, ActionDefinition action){
        return actions(Stream.concat(
                Stream.ofNullable(actions).map(Map::entrySet).flatMap(Collection::stream),
                Stream.of(Map.entry(key, action))
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
    public ChainedActionDefinitionBuilder actions(final Map<String, ActionDefinition> actions){
        this.actions = actions;
        return self();
    }

    public ChainedActionDefinition build(final String name) {
        final ChainedActionDefinition definition = new ChainedActionDefinition();
        populate(definition, name);
        Optional.ofNullable(actions).ifPresent(definition::setActions);
        return definition;
    }
}
