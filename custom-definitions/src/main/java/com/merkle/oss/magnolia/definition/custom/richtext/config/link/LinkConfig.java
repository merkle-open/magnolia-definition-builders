package com.merkle.oss.magnolia.definition.custom.richtext.config.link;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

// See implementation here:
// https://github.com/ckeditor/ckeditor5/blob/v41.4.2/packages/ckeditor5-link/src/linkconfig.ts
public class LinkConfig {
    @Nullable
    public final String defaultProtocol;
    public final Set<String> allowedProtocols;
    @Nullable
    public final Boolean allowCreatingEmptyLinks;
    @Nullable
    public final Boolean addTargetToExternalLinks;
    public final Map<String, LinkDecoratorDefinition> decorators;

    public LinkConfig(
            @Nullable final String defaultProtocol,
            final Set<String> allowedProtocols,
            @Nullable final Boolean allowCreatingEmptyLinks,
            @Nullable final Boolean addTargetToExternalLinks,
            final Map<String, LinkDecoratorDefinition> decorators
    ) {
        this.defaultProtocol = defaultProtocol;
        this.allowedProtocols = allowedProtocols;
        this.allowCreatingEmptyLinks = allowCreatingEmptyLinks;
        this.addTargetToExternalLinks = addTargetToExternalLinks;
        this.decorators = decorators;
    }

    public static class Builder {
        @Nullable
        private String defaultProtocol;
        @Nullable
        private Set<String> allowedProtocols;
        @Nullable
        private Boolean allowCreatingEmptyLinks;
        @Nullable
        private Boolean addTargetToExternalLinks;
        @Nullable
        private Map<String, LinkDecoratorDefinition> decorators;

        public Builder defaultProtocol(@Nullable final String defaultProtocol) {
            this.defaultProtocol = defaultProtocol;
            return this;
        }

        public Builder allowedProtocol(final String allowedProtocol) {
            return allowedProtocols(Stream.concat(
                    Stream.ofNullable(allowedProtocols).flatMap(Collection::stream),
                    Stream.of(allowedProtocol)
            ).collect(Collectors.toSet()));
        }

        public Builder allowedProtocols(final Set<String> allowedProtocols) {
            this.allowedProtocols = allowedProtocols;
            return this;
        }

        public Builder allowCreatingEmptyLinks(final boolean allowCreatingEmptyLinks) {
            this.allowCreatingEmptyLinks = allowCreatingEmptyLinks;
            return this;
        }

        public Builder addTargetToExternalLinks(final boolean addTargetToExternalLinks) {
            this.addTargetToExternalLinks = addTargetToExternalLinks;
            return this;
        }

        public Builder decorator(final String name, final LinkDecoratorDefinition decorator) {
            return decorators(Stream.concat(
                    Stream.ofNullable(decorators).map(Map::entrySet).flatMap(Collection::stream),
                    Stream.of(Map.entry(name, decorator))
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        public Builder decorators(final Map<String, LinkDecoratorDefinition> decorators) {
            this.decorators = decorators;
            return this;
        }

        public LinkConfig build() {
            return new LinkConfig(
                    defaultProtocol,
                    Optional.ofNullable(allowedProtocols).orElseGet(Collections::emptySet),
                    Optional.ofNullable(allowCreatingEmptyLinks).orElse(false),
                    Optional.ofNullable(addTargetToExternalLinks).orElse(false),
                    Optional.ofNullable(decorators).orElseGet(Collections::emptyMap)
            );
        }
    }
}
