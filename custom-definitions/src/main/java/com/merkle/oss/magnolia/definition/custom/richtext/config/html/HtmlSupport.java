package com.merkle.oss.magnolia.definition.custom.richtext.config.html;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

/**
 * Represents a HtmlSupport configuration.
 */
//   See implementation here:
//   https://github.com/ckeditor/ckeditor5/blob/v41.4.2/packages/ckeditor5-html-support/src/generalhtmlsupportconfig.ts#L27
public class HtmlSupport {
    public final List<Pattern> allow;
    public final List<Pattern> disallow;
    public final List<String> allowEmpty;

    public HtmlSupport(
            final List<Pattern> allow,
            final List<Pattern> disallow,
            final List<String> allowEmpty
    ) {
        this.allow = allow;
        this.disallow = disallow;
        this.allowEmpty = allowEmpty;
    }

    public static class Builder {
        @Nullable
        private List<Pattern> allow;
        @Nullable
        private List<Pattern> disallow;
        @Nullable
        private List<String> allowEmpty;


        public Builder allow(final Pattern allow) {
            return allow(Stream.concat(
                    Stream.ofNullable(this.allow).flatMap(Collection::stream),
                    Stream.of(allow)
            ).collect(Collectors.toList()));
        }
        public Builder allow(final List<Pattern> allow) {
            this.allow = allow;
            return this;
        }

        public Builder disallow(final Pattern disallow) {
            return disallow(Stream.concat(
                    Stream.ofNullable(this.disallow).flatMap(Collection::stream),
                    Stream.of(disallow)
            ).collect(Collectors.toList()));
        }
        public Builder disallow(final List<Pattern> disallow) {
            this.disallow = disallow;
            return this;
        }

        public Builder allowEmpty(final String allowEmpty) {
            return allowEmpty(Stream.concat(
                    Stream.ofNullable(this.allowEmpty).flatMap(Collection::stream),
                    Stream.of(allowEmpty)
            ).collect(Collectors.toList()));
        }
        public Builder allowEmpty(final List<String> allowEmpty) {
            this.allowEmpty = allowEmpty;
            return this;
        }

        public HtmlSupport build() {
            return new HtmlSupport(
                    Optional.ofNullable(allow).orElseGet(Collections::emptyList),
                    Optional.ofNullable(disallow).orElseGet(Collections::emptyList),
                    Optional.ofNullable(allowEmpty).orElseGet(Collections::emptyList)
            );
        }
    }
}
