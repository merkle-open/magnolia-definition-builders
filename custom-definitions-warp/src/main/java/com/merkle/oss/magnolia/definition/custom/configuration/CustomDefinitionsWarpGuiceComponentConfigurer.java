package com.merkle.oss.magnolia.definition.custom.configuration;

import info.magnolia.objectfactory.guice.AbstractGuiceComponentConfigurer;
import info.magnolia.warp.engine.form.content.processor.DataProcessor;
import info.magnolia.warp.engine.form.embedded.mapper.field.FieldDefinitionMapper;

import java.lang.reflect.Modifier;
import java.util.function.Predicate;

import org.reflections.Reflections;

import com.google.inject.multibindings.Multibinder;

public class CustomDefinitionsWarpGuiceComponentConfigurer extends AbstractGuiceComponentConfigurer {
    private static final Predicate<Class<?>> FILTER = clazz -> !Modifier.isAbstract(clazz.getModifiers())
            && clazz.getPackageName().startsWith("com.merkle.oss.magnolia.definition.custom");

	@Override
	protected void configure() {
		super.configure();
        final Multibinder<FieldDefinitionMapper> mappers = Multibinder.newSetBinder(binder(), FieldDefinitionMapper.class);
        new Reflections().getSubTypesOf(FieldDefinitionMapper.class).stream()
                .filter(FILTER)
                .map(clazz -> (Class<FieldDefinitionMapper>)clazz)
                .forEach(clazz -> mappers.addBinding().to(clazz));

        final Multibinder<DataProcessor> processors = Multibinder.newSetBinder(binder(), DataProcessor.class);
        new Reflections().getSubTypesOf(DataProcessor.class).stream()
                .filter(FILTER)
                .map(clazz -> (Class<DataProcessor>)clazz)
                .forEach(clazz -> processors.addBinding().to(clazz));
	}

}
