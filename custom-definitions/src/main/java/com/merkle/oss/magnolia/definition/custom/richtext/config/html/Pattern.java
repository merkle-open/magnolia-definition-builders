package com.merkle.oss.magnolia.definition.custom.richtext.config.html;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

public class Pattern {
    public final String name;
    public final Map<String, ?> classes;
    public final Map<String, ?> styles;
    public final Map<String, ?> attributes;

    private Pattern(
            final String name,
            final Map<String, ?> classes,
            final Map<String, ?> styles,
            final Map<String, ?> attributes
    ) {
        this.name = name;
        this.classes = classes;
        this.styles = styles;
        this.attributes = attributes;
    }

    public static class Builder {
        @Nullable
        private Set<String> classes;
        @Nullable
        private Map<String, ?> styles;
        @Nullable
        private Map<String, ?> attributes;


        public Builder clazz(final String clazz) {
            return classes(Stream.concat(
                    Stream.ofNullable(this.classes).flatMap(Collection::stream),
                    Stream.of(clazz)
            ).collect(Collectors.toSet()));
        }
        public Builder classes(final Set<String> classes) {
            this.classes = classes;
            return this;
        }

        public Builder style(final String name, final String style) {
            return styles(Stream.concat(
                    Stream.ofNullable(this.styles).map(Map::entrySet).flatMap(Collection::stream),
                    Stream.of(Map.entry(name, style))
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }
        public Builder styles(final Map<String, ?> styles) {
            this.styles = styles;
            return this;
        }

        public Builder attribute(final String name, final String attribute) {
            return attributes(Stream.concat(
                    Stream.ofNullable(this.attributes).map(Map::entrySet).flatMap(Collection::stream),
                    Stream.of(Map.entry(name, attribute))
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }
        public Builder attributes(final Map<String, ?> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Pattern build(final String name) {
            return new Pattern(
                    name,
                    Stream.ofNullable(classes).flatMap(Collection::stream).collect(Collectors.toMap(Function.identity(), ignored -> true)),
                    Optional.ofNullable(styles).orElseGet(Collections::emptyMap),
                    Optional.ofNullable(attributes).orElseGet(Collections::emptyMap)
            );
        }
    }
}
