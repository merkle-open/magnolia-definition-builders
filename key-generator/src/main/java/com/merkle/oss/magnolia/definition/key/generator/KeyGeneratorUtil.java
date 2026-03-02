package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.config.NamedDefinition;
import info.magnolia.i18nsystem.AbstractI18nKeyGenerator;
import info.magnolia.ui.api.app.AppDescriptor;
import info.magnolia.ui.api.app.registry.ConfiguredSubAppDescriptor;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.machinezoo.noexception.Exceptions;
import com.merkle.oss.magnolia.definition.key.generator.configuration.ExcludedAncestors;
import com.merkle.oss.magnolia.definition.key.generator.configuration.FallbackAppName;
import com.merkle.oss.magnolia.definition.key.generator.configuration.FallbackDialogName;
import com.merkle.oss.magnolia.definition.key.generator.configuration.IdRegexp;

public class KeyGeneratorUtil extends AbstractI18nKeyGenerator<Object> {
    private final String fallbackDialogName;
    private final String fallbackAppName;
    private final Pattern idPattern;
    private final Set<Class<?>> excludedAncestors;

    @Inject
    public KeyGeneratorUtil(
            @FallbackDialogName final String fallbackDialogName,
            @FallbackAppName final String fallbackAppName,
            @IdRegexp final String idRegexp,
            @ExcludedAncestors final Set<Class<?>> excludedAncestors
    ) {
        this.fallbackDialogName = fallbackDialogName;
        this.fallbackAppName = fallbackAppName;
        this.excludedAncestors = excludedAncestors;
        this.idPattern = Pattern.compile(idRegexp);
    }

    public boolean isMagnoliaModule(final Object definition) {
        return !idPattern.matcher(getIdOrNameForUnknownRoot(definition, false)).find();
    }

    public String getRootDefinitionModuleId(final Object definition) {
        return getIdPart(definition, 0).orElse("undefined");
    }
    public String getRootDefinitionPath(final Object definition) {
        return getIdPart(definition, 1).orElse("undefined");
    }
    public String getRootDefinitionName(final Object definition) {
        return getIdPart(definition, 2).orElse("undefined");
    }
    private Optional<String> getIdPart(final Object definition, final int index) {
        final String id = getIdOrNameForUnknownRoot(definition);
        final String[] split = id.split("\\.");
        if(split.length >= index) {
            return Optional.of(split[index]);
        }
        return Optional.empty();
    }

    public String getFallbackName(final Object definition) {
        if(getAncestors(definition).stream().anyMatch(o -> o instanceof AppDescriptor)) {
            return fallbackAppName;
        }
        return fallbackDialogName;
    }

    public Set<Class<?>> getExcludedAncestors() {
        return excludedAncestors;
    }

    public String[] prepend(final String[] values, final String... valuesToPrefix) {
        return Stream.concat(
                Arrays.stream(valuesToPrefix),
                Arrays.stream(values)
        ).toArray(String[]::new);
    }
    public String[] append(final String[] values, final String... valuesToPrefix) {
        return Stream.concat(
                Arrays.stream(values),
                Arrays.stream(valuesToPrefix)
        ).toArray(String[]::new);
    }

    public Stream<String> streamAncestorNames(final Predicate<Object> filter, final Object definition) {
        final List<Object> ancestors = getAncestors(definition);
        Collections.reverse(ancestors);
        return ancestors.stream()
                .filter(ancestor ->
                        filter.test(ancestor) && getExcludedAncestors().stream().noneMatch(excluded -> excluded.isInstance(ancestor))
                )
                .map(this::getName)
                .flatMap(Optional::stream)
                .distinct();
    }
    private Optional<String> getName(final Object definition) {
        if(definition instanceof NamedDefinition namedDefinition) {
            return Optional.of(namedDefinition.getName());
        }
        if(definition instanceof ConfiguredSubAppDescriptor subAppDescriptor) {
            return Optional.of(subAppDescriptor.getName());
        }
        return Optional.empty();
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
