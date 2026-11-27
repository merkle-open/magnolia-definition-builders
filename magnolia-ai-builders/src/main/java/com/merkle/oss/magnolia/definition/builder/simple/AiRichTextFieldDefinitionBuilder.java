package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import info.magnolia.ai.automations.config.ActionConfiguration;
import info.magnolia.ai.automations.ui.fields.AiRichTextFieldDefinition;
import jakarta.annotation.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * builds a {@link AiRichTextFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/ai-accelerator/developers/ai-rich-text-field/">magnolia Docs - configure AI rich text fields</a>
 * @author Merkle DACH
 */
public class AiRichTextFieldDefinitionBuilder extends AbstractConfiguredComplexPropertyDefinitionBuilder<Object, AiRichTextFieldDefinition, AiRichTextFieldDefinitionBuilder> {
    @Nullable
    private Boolean alignment;
    @Nullable
    private Boolean images;
    @Nullable
    private Boolean lists;
    @Nullable
    private Boolean source;
    @Nullable
    private Boolean tables;
    @Nullable
    private Long height;
    @Nullable
    private String colors;
    @Nullable
    private String fonts;
    @Nullable
    private String fontSizes;
    @Nullable
    private Map<String, ActionConfiguration> actions;
    @Nullable
    private String taskId;

    public AiRichTextFieldDefinitionBuilder alignment(final boolean alignment) {
        this.alignment = alignment;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder images(final boolean images) {
        this.images = images;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder lists(final boolean lists) {
        this.lists = lists;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder source(final boolean source) {
        this.source = source;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder tables(final boolean tables) {
        this.tables = tables;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder height(final long height) {
        this.height = height;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder colors(final String colors) {
        this.colors = colors;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder fonts(final String fonts) {
        this.fonts = fonts;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder fontSizes(final String fontSizes) {
        this.fontSizes = fontSizes;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder action(final String actionName, final ActionConfiguration actionConfiguration) {
        return actions(Stream.concat(
                Stream.ofNullable(this.actions).map(Map::entrySet).flatMap(Collection::stream),
                Stream.of(Map.entry(actionName, actionConfiguration))
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public AiRichTextFieldDefinitionBuilder actions(final Map<String, ActionConfiguration> actions) {
        this.actions = actions;
        return self();
    }

    public AiRichTextFieldDefinitionBuilder taskId(final String taskId) {
        this.taskId = taskId;
        return self();
    }

    public AiRichTextFieldDefinition build(final String name) {
        final AiRichTextFieldDefinition definition = new AiRichTextFieldDefinition();
        super.populate(definition, name);
        Optional.ofNullable(alignment).ifPresent(definition::setAlignment);
        Optional.ofNullable(images).ifPresent(definition::setImages);
        Optional.ofNullable(lists).ifPresent(definition::setLists);
        Optional.ofNullable(source).ifPresent(definition::setSource);
        Optional.ofNullable(tables).ifPresent(definition::setTables);
        Optional.ofNullable(height).ifPresent(definition::setHeight);
        Optional.ofNullable(colors).ifPresent(definition::setColors);
        Optional.ofNullable(fonts).ifPresent(definition::setFonts);
        Optional.ofNullable(fontSizes).ifPresent(definition::setFontSizes);
        Optional.ofNullable(actions).ifPresent(definition::setActions);
        Optional.ofNullable(taskId).ifPresent(definition::setTaskId);
        definition.init();
        return definition;
    }
}
