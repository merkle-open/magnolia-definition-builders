package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.warp.RequestUtil;
import info.magnolia.warp.engine.form.embedded.formschemapojos.JSFieldProperty;
import info.magnolia.warp.engine.form.embedded.mapper.field.scalar.ConfiguredFieldDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.merkle.oss.magnolia.definition.custom.colorpicker.ColorPickerFieldDefinition;

import jakarta.inject.Inject;

public class ColorPickerFieldDefinitionMapper extends ConfiguredFieldDefinitionMapper<ColorPickerFieldDefinition, JSFieldProperty.JSFieldPropertyBuilder<?, ?>> {

    private final RequestUtil requestUtil;

    @Inject
    public ColorPickerFieldDefinitionMapper(FormDefinitionProblemUtil formDefinitionProblemUtil, RequestUtil requestUtil) {
        super(formDefinitionProblemUtil);
        this.requestUtil = requestUtil;
    }

    @Override
    public Collection<Type> getSupportedClasses() {
        return Collections.singleton(ColorPickerFieldDefinition.class);
    }

    @Override
    protected JSFieldProperty.JSFieldPropertyBuilder<?, ?> getPropertyBuilder() {
        return JSFieldProperty.builder();
    }

    @Override
    public JSFieldProperty.JSFieldPropertyBuilder<?, ?> addFieldProperties(ColorPickerFieldDefinition fieldDefinition, JSFieldProperty.JSFieldPropertyBuilder<?, ?> builder) {
        var fieldScript = requestUtil.getContextPath() + "/.resources/custom-definitions-warp/webresources/colorPickerField/index.html";

        return (JSFieldProperty.JSFieldPropertyBuilder<?, ?>) super.addFieldProperties(fieldDefinition, builder)
                .uiBuilder()
                .options(Map.of("fieldScript", fieldScript))
                .uiDone();
    }
}