package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.config.registry.DefinitionProvider;
import info.magnolia.warp.engine.form.embedded.formschemapojos.CompositeProperty;
import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.embedded.mapper.field.complex.SwitchableFieldDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class SwitchableDefinitionMapper extends SwitchableFieldDefinitionMapper<SwitchableDefinition, CompositeProperty.CompositePropertyBuilder<?, ?>> {

    @Inject
    SwitchableDefinitionMapper(Provider<FormDefinitionMapper> formDefinitionMapper, FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
    }

    @Override
    public Collection<Type> getSupportedClasses() {
        return Collections.singleton(SwitchableDefinition.class);
    }

    @Override
    public Stream<DefinitionProvider.Problem> validateSupportOfFieldProperties(SwitchableDefinition fieldDefinition) {
        return Stream.empty();
    }
}