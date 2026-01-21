package com.merkle.oss.magnolia.definition.custom.mapper;

import info.magnolia.config.registry.DefinitionProvider;
import info.magnolia.ui.editor.ComplexPropertyDefinition;
import info.magnolia.ui.editor.SwitchableFormDefinition;
import info.magnolia.warp.engine.form.embedded.formschemapojos.CompositeProperty;
import info.magnolia.warp.engine.form.embedded.mapper.FormDefinitionMapper;
import info.magnolia.warp.engine.form.embedded.mapper.field.complex.SwitchableFieldDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import jakarta.inject.Provider;

public abstract class AbstractComplexPropertySwitchableFormDefinitionMapper<D extends ComplexPropertyDefinition<?> & SwitchableFormDefinition<?>> extends SwitchableFieldDefinitionMapper<D, CompositeProperty.CompositePropertyBuilder> {
    private final Class<D> supportedClass;

    protected AbstractComplexPropertySwitchableFormDefinitionMapper(
            final Provider<FormDefinitionMapper> formDefinitionMapper,
            final FormDefinitionProblemUtil formDefinitionProblemUtil,
            final Class<D> supportedClass
    ) {
        super(formDefinitionMapper, formDefinitionProblemUtil);
        this.supportedClass = supportedClass;
    }

    @Override
    public Collection<Type> getSupportedClasses() {
        return Set.of(supportedClass);
    }

    @Override
    public Stream<DefinitionProvider.Problem> validateSupportOfFieldProperties(final D fieldDefinition) {
        return Stream.empty();
    }
}
