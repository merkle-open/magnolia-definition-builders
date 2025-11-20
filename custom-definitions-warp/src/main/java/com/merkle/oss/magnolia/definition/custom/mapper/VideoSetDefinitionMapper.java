package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import com.merkle.oss.magnolia.definition.custom.videoset.VideoSetDefinition;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class VideoSetDefinitionMapper extends AbstractComplexPropertyFormDefinitionMapper<VideoSetDefinition> {
    @Inject
    public VideoSetDefinitionMapper(final Provider<FormDefinitionMapper> formDefinitionMapper, final FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
    }
}
