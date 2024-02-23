package com.merkle.oss.magnolia.definition.key.generator;

import com.merkle.oss.magnolia.definition.key.generator.configuration.ExcludedAncestors;
import com.merkle.oss.magnolia.definition.key.generator.configuration.FallbackDialogName;
import com.merkle.oss.magnolia.definition.key.generator.configuration.IdPrefix;
import info.magnolia.i18nsystem.AbstractI18nKeyGenerator;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.lang.reflect.AnnotatedElement;
import java.util.List;
import java.util.Set;

public class KeyGeneratorUtil extends AbstractI18nKeyGenerator<Object> {
	private final String fallbackDialogName;
	private final String idPrefix;
	private final Set<Class<?>> excludedAncestors;

	@Inject
	public KeyGeneratorUtil(
			@FallbackDialogName final String fallbackDialogName,
			@IdPrefix final String idPrefix,
			@ExcludedAncestors final Set<Class<?>> excludedAncestors
	) {
		this.fallbackDialogName = fallbackDialogName;
		this.idPrefix = idPrefix;
		this.excludedAncestors = excludedAncestors;
	}

	public boolean isMagnoliaModule(final Object definition) {
		return !StringUtils.startsWith(getIdOrNameForUnknownRoot(definition, false), idPrefix);
	}

	public String getDialogName(final Object definition) {
		final String id = getIdOrNameForUnknownRoot(definition);
		return StringUtils.defaultIfEmpty(StringUtils.substringAfterLast(id, "."), getFallbackDialogName());
	}

	public String getFallbackDialogName() {
		return fallbackDialogName;
	}

	public Set<Class<?>> getExcludedAncestors() {
		return excludedAncestors;
	}

	@Override
	protected void keysFor(List<String> keys, Object object, AnnotatedElement el) {
	}
}
