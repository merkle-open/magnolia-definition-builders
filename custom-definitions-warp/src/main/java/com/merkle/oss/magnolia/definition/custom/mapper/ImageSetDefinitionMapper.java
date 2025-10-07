package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinition;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class ImageSetDefinitionMapper extends AbstractComplexPropertyFormDefinitionMapper<ImageSetDefinition> {
    @Inject
    public ImageSetDefinitionMapper(final Provider<FormDefinitionMapper> formDefinitionMapper, final FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
    }
}
