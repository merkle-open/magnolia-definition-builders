package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.i18nsystem.AbstractI18nKeyGenerator;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.machinezoo.noexception.Exceptions;
import com.merkle.oss.magnolia.definition.key.generator.configuration.ExcludedAncestors;
import com.merkle.oss.magnolia.definition.key.generator.configuration.FallbackDialogName;
import com.merkle.oss.magnolia.definition.key.generator.configuration.IdRegexp;

public class KeyGeneratorUtil extends AbstractI18nKeyGenerator<Object> {
    private final String fallbackDialogName;
    private final Pattern idPattern;
    private final Set<Class<?>> excludedAncestors;

    @Inject
    public KeyGeneratorUtil(
            @FallbackDialogName final String fallbackDialogName,
            @IdRegexp final String idRegexp,
            @ExcludedAncestors final Set<Class<?>> excludedAncestors
    ) {
        this.fallbackDialogName = fallbackDialogName;
        this.excludedAncestors = excludedAncestors;
        this.idPattern = Pattern.compile(idRegexp);
    }

    public boolean isMagnoliaModule(final Object definition) {
        return !idPattern.matcher(getIdOrNameForUnknownRoot(definition, false)).find();
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

    //Same as parent, but has defined order id -> name if object has both id and name
    @Override
    protected String getIdOrNameForUnknownRoot(final Object obj, final boolean keyify) {
        final Object root = getParentViaCast(obj) != null ? getRoot(obj) : obj;
        return Stream
                .concat(
                        getMethod(root.getClass(), "getId").stream(),
                        getMethod(root.getClass(), "getName").stream()
                )
                .map(method ->
                        Exceptions.wrap().get(() -> (String)method.invoke(root))
				)
                .filter(StringUtils::isNotBlank)
                .findFirst()
                .map(idOrName -> keyify ? keyify(idOrName) : idOrName)
                .orElse(null);
    }

    private Optional<Method> getMethod(final Class<?> type, final String name) {
        return Arrays.stream(type.getMethods())
                .filter(method -> name.equals(method.getName()))
                .findFirst();
    }

    @Override
    protected void keysFor(List<String> keys, Object object, AnnotatedElement el) {}
}
