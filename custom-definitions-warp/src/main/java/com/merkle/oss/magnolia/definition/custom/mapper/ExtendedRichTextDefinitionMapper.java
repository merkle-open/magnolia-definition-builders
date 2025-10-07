package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.ui.field.RichTextFieldDefinition;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5Config;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig;
import info.magnolia.warp.engine.form.embedded.formschemapojos.Property;
import info.magnolia.warp.engine.form.embedded.mapper.field.scalar.RichTextFieldDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;

import com.merkle.oss.magnolia.definition.custom.richtext.ExtendedRichTextDefinition;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;

import jakarta.inject.Inject;

public class ExtendedRichTextDefinitionMapper extends RichTextFieldDefinitionMapper {

    @Inject
    protected ExtendedRichTextDefinitionMapper(CKEditor5Config ckEditor5Config, FormDefinitionProblemUtil formDefinitionProblemUtil) {
        super(ckEditor5Config, formDefinitionProblemUtil);
    }

    @Override
    public Collection<Type> getSupportedClasses() {
        return Collections.singleton(ExtendedRichTextDefinition.class);
    }

    @Override
    public Property.PropertyBuilder addFieldProperties(RichTextFieldDefinition definition, Property.PropertyBuilder builder) {
        var richTextFieldDefinition = (ExtendedRichTextDefinition) definition;
        var propertyBuilder = super.addFieldProperties(richTextFieldDefinition, builder);

        ((ExtendedRichTextDefinition) definition).getToolbarConfig()
                .map(RichTextToolbarConfig::getConfig)
                .ifPresent(toolbarGroups -> {
                    CKEditor5TextFieldConfig config = (CKEditor5TextFieldConfig) propertyBuilder.build().getUi().getOptions().get(CK_EDITOR_CONFIG);
                    config.toolbar.items = toolbarGroups.stream().
                            flatMap(toolbarGroup -> toolbarGroup.getItems().stream())
                            .toList();
                    //TODO whole custom config
                });

        return propertyBuilder;
    }
}