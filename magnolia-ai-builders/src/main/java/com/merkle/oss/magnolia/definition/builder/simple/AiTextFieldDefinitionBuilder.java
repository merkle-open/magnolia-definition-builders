package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import info.magnolia.ai.automations.ui.fields.AiTextFieldDefinition;
import jakarta.annotation.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * builds a {@link AiTextFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/ai-accelerator/developers/text-fields/">magnolia Docs - configure AI-powered text fields</a>
 * @author Merkle DACH
 */
public class AiTextFieldDefinitionBuilder extends AbstractConfiguredComplexPropertyDefinitionBuilder<Object, AiTextFieldDefinition, AiTextFieldDefinitionBuilder> {

    @Nullable
    private Map<String, Object> promptProperties;
    @Nullable
    private String rows;
    @Nullable
    private String words;
    @Nullable
    private String taskId;
    @Nullable
    private Boolean required;
    @Nullable
    private Boolean readOnly;

    public AiTextFieldDefinitionBuilder() {}

    public AiTextFieldDefinitionBuilder rows(final int rows) {
        this.rows = Integer.toString(rows);
        return self();
    }

    public AiTextFieldDefinitionBuilder words(final int words) {
        this.words = Integer.toString(words);
        return self();
    }

    public AiTextFieldDefinitionBuilder taskId(final String taskId) {
        this.taskId = taskId;
        return self();
    }

    public AiTextFieldDefinitionBuilder required(final boolean required) {
        this.required = required;
        return self();
    }

    public AiTextFieldDefinitionBuilder required() {
        return required(true);
    }

    public AiTextFieldDefinitionBuilder readOnly(final boolean readOnly) {
        this.readOnly = readOnly;
        return self();
    }

    public AiTextFieldDefinitionBuilder readOnly() {
        return readOnly(true);
    }

    public AiTextFieldDefinitionBuilder promptProperty(final String promptVariableName, final String dialogFieldName) {
        return promptProperties(Stream.concat(
                Stream.ofNullable(this.promptProperties).map(Map::entrySet).flatMap(Collection::stream),
                Stream.of(Map.entry(promptVariableName, dialogFieldName))
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public AiTextFieldDefinitionBuilder promptProperties(final Map<String, Object> promptProperties) {
        this.promptProperties = promptProperties;
        return self();
    }

    public AiTextFieldDefinition build(final String name, final String prompt) {
        final AiTextFieldDefinition definition = new AiTextFieldDefinition();
        super.populate(definition, name);
        definition.setPrompt(prompt);
        Optional.ofNullable(promptProperties).ifPresent(definition::setPromptProperties);
        Optional.ofNullable(rows).ifPresent(definition::setRows);
        Optional.ofNullable(words).ifPresent(definition::setWords);
        Optional.ofNullable(taskId).ifPresent(definition::setTaskId);
        Optional.ofNullable(required).ifPresent(definition::setRequired);
        Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
        definition.init();
        return definition;
    }

}
