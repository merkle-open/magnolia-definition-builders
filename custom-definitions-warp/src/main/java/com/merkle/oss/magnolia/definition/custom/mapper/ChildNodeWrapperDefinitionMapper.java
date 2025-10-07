package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.warp.engine.form.embedded.formschemapojos.CompositeProperty;
import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.embedded.mapper.field.complex.ConfiguredComplexPropertyDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;

import com.merkle.oss.magnolia.definition.custom.childnodewrapper.ChildNodeWrapper;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class ChildNodeWrapperDefinitionMapper extends ConfiguredComplexPropertyDefinitionMapper<ChildNodeWrapper<EditorPropertyDefinition>, CompositeProperty.CompositePropertyBuilder<?, ?>> {

    @Inject
    ChildNodeWrapperDefinitionMapper(Provider<FormDefinitionMapper> formDefinitionMapper, FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
    }

    @Override
    public Collection<Type> getSupportedClasses() {
        return Collections.singleton(ChildNodeWrapper.class);
    }
}