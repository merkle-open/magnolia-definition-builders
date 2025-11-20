package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.config.registry.DefinitionProvider;
import info.magnolia.ui.editor.ComplexPropertyDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.warp.engine.form.embedded.formschemapojos.CompositeProperty;
import info.magnolia.warp.engine.form.embedded.formschemapojos.PropertyType;
import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.embedded.mapper.field.complex.ConfiguredComplexPropertyDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.util.stream.Stream;

import jakarta.inject.Provider;

public abstract class AbstractComplexPropertyFormDefinitionMapper<D extends ComplexPropertyDefinition<?> & FormDefinition<?>> extends ConfiguredComplexPropertyDefinitionMapper<D, CompositeProperty.CompositePropertyBuilder> {

    protected AbstractComplexPropertyFormDefinitionMapper(
            final Provider<FormDefinitionMapper> formDefinitionMapper,
            final FormDefinitionProblemUtil formDefinitionProblemUtil
    ) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
    }

    @Override
    protected CompositeProperty.CompositePropertyBuilder getPropertyBuilder() {
        return CompositeProperty.builder();
    }

    @Override
    public Stream<DefinitionProvider.Problem> validateSupportOfFieldProperties(D fieldDefinition) {
        return Stream.empty();
    }

    @Override
    public CompositeProperty.CompositePropertyBuilder addFieldProperties(final D fieldDefinition, final CompositeProperty.CompositePropertyBuilder builder) {
        return (CompositeProperty.CompositePropertyBuilder) super.addFieldProperties(fieldDefinition, builder)
                .properties(fieldDefinition.getProperties().stream().map(formDefinitionMapper.get()::fieldDefinitionToProperty).toList())
                .translatable(fieldDefinition.isI18n())
                .type(PropertyType.COMPOSITE);
    }
}
