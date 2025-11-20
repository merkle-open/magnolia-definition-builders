package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableDefinition;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class SwitchableDefinitionMapper extends AbstractComplexPropertySwitchableFormDefinitionMapper<SwitchableDefinition> {
    @Inject
    public SwitchableDefinitionMapper(final Provider<FormDefinitionMapper> formDefinitionMapper, final FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(formDefinitionMapper, formDefinitionProblemUtil, SwitchableDefinition.class);
    }
}
