package com.merkle.oss.magnolia.definition.custom.processor;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.warp.engine.form.content.processor.DataProcessor;

import java.util.Locale;
import java.util.Map;

import com.merkle.oss.magnolia.definition.custom.separator.SeparatorFieldDefinition;

/**
 * Ignores reading and writing of {@link com.merkle.oss.magnolia.definition.custom.separator.SeparatorFieldDefinition}.
 */
public class SeparatorFieldProcessor<T> implements DataProcessor<T, SeparatorFieldDefinition, Object> {

    @Override
    public boolean supports(EditorPropertyDefinition editorDefinition) {
        return editorDefinition instanceof SeparatorFieldDefinition;
    }

    @Override
    public Map<String, Object> read(T t, SeparatorFieldDefinition editorDefinition, Locale locale) {
        return Map.of();
    }

    @Override
    public T write(T t, SeparatorFieldDefinition editorDefinition, Map<String, Object> content, Locale locale) {
        return t;
    }
}
