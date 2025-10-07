package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.config.registry.DefinitionProvider;
import info.magnolia.ui.editor.ComplexPropertyDefinition;
import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.warp.engine.form.embedded.formschemapojos.CompositeProperty;
import info.magnolia.warp.engine.form.embedded.formschemapojos.PropertyType;
import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.embedded.mapper.field.complex.ConfiguredComplexPropertyDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinition;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoSetDefinition;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class SetDefinitionMapper<D extends ComplexPropertyDefinition<?> & FormDefinition<?>> extends ConfiguredComplexPropertyDefinitionMapper<D, CompositeProperty.CompositePropertyBuilder> {

    @Inject
    SetDefinitionMapper(Provider<FormDefinitionMapper> formDefinitionMapper, FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
    }

    @Override
    public Collection<Type> getSupportedClasses() {
        return List.of(ImageSetDefinition.class, VideoSetDefinition.class);
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
    public CompositeProperty.CompositePropertyBuilder addFieldProperties(D fieldDefinition, CompositeProperty.CompositePropertyBuilder builder) {
        return (CompositeProperty.CompositePropertyBuilder) super.addFieldProperties(fieldDefinition, builder)
                .properties(fieldDefinition.getProperties().stream().map(formDefinitionMapper.get()::fieldDefinitionToProperty).toList())
                .translatable(fieldDefinition.isI18n())
                .type(PropertyType.COMPOSITE);
    }
}