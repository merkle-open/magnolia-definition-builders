package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.warp.RequestUtil;
import info.magnolia.warp.engine.form.embedded.formschemapojos.JSFieldProperty;
import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.embedded.mapper.field.complex.ConfiguredComplexPropertyDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.merkle.oss.magnolia.definition.custom.separator.SeparatorFieldDefinition;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class SeparatorFieldMapper extends ConfiguredComplexPropertyDefinitionMapper<SeparatorFieldDefinition, JSFieldProperty.JSFieldPropertyBuilder<?, ?>> {
    private final RequestUtil requestUtil;

    @Inject
    protected SeparatorFieldMapper(
            final Provider<FormDefinitionMapper> formDefinitionMapper,
            final FormDefinitionProblemUtil formDefinitionProblemUtil,
            final RequestUtil requestUtil
    ) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
        this.requestUtil = requestUtil;
    }

    @Override
    protected JSFieldProperty.JSFieldPropertyBuilder<?, ?> getPropertyBuilder() {
        return JSFieldProperty.builder();
    }

    @Override
    public JSFieldProperty.JSFieldPropertyBuilder<?, ?> addFieldProperties(final SeparatorFieldDefinition fieldDefinition, final JSFieldProperty.JSFieldPropertyBuilder<?, ?> builder) {
        final String fieldScript = requestUtil.getContextPath() + "/.resources/custom-definitions-warp/webresources/separatorField/index.html";

        return (JSFieldProperty.JSFieldPropertyBuilder<?, ?>) super.addFieldProperties(fieldDefinition, builder)
                .translatable(fieldDefinition.isI18n())
                .uiBuilder()
                .options(Map.of("fieldScript", fieldScript))
                .uiDone();
    }
}
