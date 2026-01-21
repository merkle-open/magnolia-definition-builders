package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import com.merkle.oss.magnolia.definition.custom.childnodewrapper.ChildNodeWrapper;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class ChildNodeWrapperDefinitionMapper extends AbstractComplexPropertyFormDefinitionMapper<ChildNodeWrapper<?>> {
    @Inject
    public ChildNodeWrapperDefinitionMapper(final Provider<FormDefinitionMapper> formDefinitionMapper, final FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
    }
}
