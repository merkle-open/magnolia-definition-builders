package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.ui.field.ConfiguredComplexPropertyDefinition;
import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/*
 * Uses EditorPropertyDefinition.styleName to pass keyPrefix to the key generators.
 * This hack is unfortunately necessary, because wrapping the definition is not an option since magnolia is heavily depending on instanceof checks, which would then be broken!
 * The only other alternative would be using a proxy / byteBuddy, but this would be even worse...
 */
public class KeyPrefixer {
    private static final String KEY_PREFIX = "_keyPrefix-";
    private static final Pattern KEY_PREFIX_PATTERN = Pattern.compile(KEY_PREFIX + "([^ ]+)");

    Stream<String> getKeyPrefix(final EditorPropertyDefinition definition) {
        return Optional
                .ofNullable(definition.getStyleName())
                .map(KEY_PREFIX_PATTERN::matcher)
                .stream()
                .flatMap(matcher -> {
                    final Stream.Builder<String> builder = Stream.builder();
                    while (matcher.find()) {
                        builder.add(matcher.group(1));
                    }
                    return builder.build();
                });
    }

    public static EditorPropertyDefinition keyPrefix(final EditorPropertyDefinition definition, final String prefix) {
        if(definition instanceof final ConfiguredFieldDefinition<?> configuredFieldDefinition) {
            keyPrefix(configuredFieldDefinition, prefix);
        } else if(definition instanceof final ConfiguredComplexPropertyDefinition<?> complexPropertyDefinition) {
            keyPrefix(complexPropertyDefinition, prefix);
        }
        return definition;
    }

    public static <D extends ConfiguredFieldDefinition<?>> D keyPrefix(final D definition, final String prefix) {
        definition.setStyleName(getStyleName(definition, prefix));
        return definition;
    }

    public static <D extends ConfiguredComplexPropertyDefinition<?>> D keyPrefix(final D definition, final String prefix) {
        definition.setStyleName(getStyleName(definition, prefix));
        return definition;
    }

    private static String getStyleName(final EditorPropertyDefinition definition, final String prefix) {
        if(prefix.contains(" ")) {
            throw new IllegalArgumentException("Key prefix must not contain spaces!");
        }
        return Optional
                .ofNullable(definition.getStyleName())
                .map(StringBuilder::new)
                .orElseGet(StringBuilder::new)
                .append(" ").append(KEY_PREFIX).append(prefix)
                .toString();
    }
}
