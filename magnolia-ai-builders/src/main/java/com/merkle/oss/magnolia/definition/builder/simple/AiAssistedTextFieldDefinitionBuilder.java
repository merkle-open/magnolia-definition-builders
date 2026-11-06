package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import info.magnolia.ai.automations.config.ExtensionConfiguration;
import info.magnolia.ai.automations.ui.fields.AiAssistedTextFieldDefinition;
import jakarta.annotation.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * builds a {@link AiAssistedTextFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/ai-accelerator/developers/assisted-text-fields/">magnolia Docs - configure AI-assisted text fields</a>
 * @author Merkle DACH
 */
public class AiAssistedTextFieldDefinitionBuilder extends AbstractConfiguredComplexPropertyDefinitionBuilder<Object, AiAssistedTextFieldDefinition, AiAssistedTextFieldDefinitionBuilder> {

    @Nullable
    private String placeholder;
    @Nullable
    private Integer rows;
    @Nullable
    private Integer maxLength;
    @Nullable
    private Map<String, ExtensionConfiguration> extensions;
    @Nullable
    private String taskId;
    @Nullable
    private Boolean required;
    @Nullable
    private Boolean readOnly;

    public AiAssistedTextFieldDefinitionBuilder() {}

    public AiAssistedTextFieldDefinitionBuilder placeholder(final String placeholder) {
        this.placeholder = placeholder;
        return self();
    }

    public AiAssistedTextFieldDefinitionBuilder rows(final int rows) {
        this.rows = rows;
        return self();
    }

    public AiAssistedTextFieldDefinitionBuilder maxLength(final int maxLength) {
        this.maxLength = maxLength;
        return self();
    }

    public AiAssistedTextFieldDefinitionBuilder extension(final String extensionName) {
        final ExtensionConfiguration extensionConfiguration = new ExtensionConfiguration();
        extensionConfiguration.setExtension(extensionName);
        return extension(extensionName, extensionConfiguration);
    }

    public AiAssistedTextFieldDefinitionBuilder extension(final String extensionName, final ExtensionConfiguration extensionConfiguration) {
        return extensions(Stream.concat(
                Stream.ofNullable(this.extensions).map(Map::entrySet).flatMap(Collection::stream),
                Stream.of(Map.entry(extensionName, extensionConfiguration))
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public AiAssistedTextFieldDefinitionBuilder extensions(final Map<String, ExtensionConfiguration> extensions) {
        this.extensions = extensions;
        return self();
    }

    public AiAssistedTextFieldDefinitionBuilder taskId(final String taskId) {
        this.taskId = taskId;
        return self();
    }

    public AiAssistedTextFieldDefinitionBuilder required() {
       return required(true);
    }

    public AiAssistedTextFieldDefinitionBuilder required(final boolean required) {
        this.required = required;
        return self();
    }

    public AiAssistedTextFieldDefinitionBuilder readOnly() {
        return readOnly(true);
    }

    public AiAssistedTextFieldDefinitionBuilder readOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return self();
    }

    public AiAssistedTextFieldDefinition build(final String name) {
        final AiAssistedTextFieldDefinition definition = new AiAssistedTextFieldDefinition();
        super.populate(definition, name);
        Optional.ofNullable(placeholder).ifPresent(definition::setPlaceholder);
        Optional.ofNullable(rows).ifPresent(definition::setRows);
        Optional.ofNullable(maxLength).ifPresent(definition::setMaxLength);
        Optional.ofNullable(extensions).ifPresent(definition::setExtensions);
        Optional.ofNullable(taskId).ifPresent(definition::setTaskId);
        Optional.ofNullable(required).ifPresent(definition::setRequired);
        Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
        definition.init();
        return definition;
    }
}
