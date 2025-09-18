package com.merkle.oss.magnolia.definition.custom.richtext.config.link;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

//vaadin can't handle inheritance work around with nullable fields - https://stackoverflow.com/questions/70238854/vaadin-binder-writing-to-an-instance-of-an-inherited-abstract-class
public class LinkDecoratorDefinition {
    public final String mode;
    @Nullable
    public final String urlPredicateRegex; //automatic
    @Nullable
    public final String label; //manual
    @Nullable
    public final Boolean defaultValue; //manual
    public final Map<String, String> attributes;
    public final Map<String, String> styles;
    public final Set<String> classes;

    public LinkDecoratorDefinition(
            final String mode,
            @Nullable final String urlPredicateRegex,
            @Nullable final String label,
            @Nullable final Boolean defaultValue,
            final Map<String, String> attributes,
            final Map<String, String> styles,
            final Set<String> classes
    ) {
        this.mode = mode;
        this.urlPredicateRegex = urlPredicateRegex;
        this.label = label;
        this.defaultValue = defaultValue;
        this.attributes = attributes;
        this.styles = styles;
        this.classes = classes;
    }

    private static abstract class Builder<B extends Builder<B>> {
        @Nullable
        protected Map<String, String> attributes;
        @Nullable
        protected Map<String, String> styles;
        @Nullable
        protected Set<String> classes;

        public B attribute(final String key, final String value) {
            return attributes(Stream.concat(
                    Stream.ofNullable(attributes).map(Map::entrySet).flatMap(Collection::stream),
                    Stream.of(Map.entry(key, value))
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        public B attributes(final Map<String, String> attributes) {
            this.attributes = attributes;
            return self();
        }

        public B style(final String key, final String value) {
            return styles(Stream.concat(
                    Stream.ofNullable(styles).map(Map::entrySet).flatMap(Collection::stream),
                    Stream.of(Map.entry(key, value))
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        public B styles(final Map<String, String> styles) {
            this.styles = styles;
            return self();
        }

        public B clazz(final String clazz) {
            return classes(Stream.concat(
                    Stream.ofNullable(classes).flatMap(Collection::stream),
                    Stream.of(clazz)
            ).collect(Collectors.toSet()));
        }

        public B classes(final Set<String> classes) {
            this.classes = classes;
            return self();
        }

        @SuppressWarnings("unchecked")
        protected B self() {
            return (B) this;
        }
    }

    public static class AutomaticBuilder extends Builder<AutomaticBuilder> {
        public static final String MODE = "automatic";

        public LinkDecoratorDefinition build(final String urlPredicateRegex) {
            return new LinkDecoratorDefinition(
                    MODE,
                    urlPredicateRegex,
                    null,
                    null,
                    Optional.ofNullable(attributes).orElseGet(Collections::emptyMap),
                    Optional.ofNullable(styles).orElseGet(Collections::emptyMap),
                    Optional.ofNullable(classes).orElseGet(Collections::emptySet)
            );
        }
    }

    public static class ManualBuilder extends Builder<ManualBuilder> {
        public static final String MODE = "manual";
        @Nullable
        private Boolean defaultValue;

        public ManualBuilder defaultValue(final boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public LinkDecoratorDefinition build(final String label) {
            return new LinkDecoratorDefinition(
                    MODE,
                    null,
                    label,
                    defaultValue,
                    Optional.ofNullable(attributes).orElseGet(Collections::emptyMap),
                    Optional.ofNullable(styles).orElseGet(Collections::emptyMap),
                    Optional.ofNullable(classes).orElseGet(Collections::emptySet)
            );
        }
    }
}
