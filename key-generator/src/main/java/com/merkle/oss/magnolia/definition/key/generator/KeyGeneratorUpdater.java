package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.i18nsystem.I18nKeyGenerator;
import info.magnolia.i18nsystem.I18nable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class KeyGeneratorUpdater {

	public <T> void update(final Class<T> definition, final Class<? extends I18nKeyGenerator<?>> keyGenerator) {
		try {
			final I18nable annotation = definition.getAnnotation(I18nable.class);
			final Method method = Class.class.getDeclaredMethod("getDeclaredAnnotationMap");
			method.setAccessible(true);
			final Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) method.invoke(definition);
			annotations.put(I18nable.class, new I18nable() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return annotation.annotationType();
				}
				@Override
				public Class<? extends I18nKeyGenerator<?>> keyGenerator() {
					return keyGenerator;
				}
			});
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			throw new RuntimeException("Failed to update key generator for " + definition, e);
		}
	}
}
