package com.merkle.oss.magnolia.definition.custom.richtext.config.link;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

// See implementation here:
// https://gitlab.com/magnolia-ce/ckeditor5-plugins/-/blob/main/packages/ckeditor5-plugins/src/mgnllink/mgnllinkconfig.ts?ref_type=heads#L16
public class MgnlLinkConfig {
    public final Map<String, LinkDecoratorDefinition> decorators;

    public MgnlLinkConfig(final Map<String, LinkDecoratorDefinition> decorators) {
        this.decorators = decorators;
    }

    public static class Builder {
        @Nullable
        private Map<String, LinkDecoratorDefinition> decorators;

        public Builder() {}
        public Builder(final MgnlLinkConfig mgnlLinkConfig) {
            this.decorators = mgnlLinkConfig.decorators;
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

        public MgnlLinkConfig build() {
            return new MgnlLinkConfig(
                    Optional.ofNullable(decorators).orElseGet(Collections::emptyMap)
            );
        }
    }
}
