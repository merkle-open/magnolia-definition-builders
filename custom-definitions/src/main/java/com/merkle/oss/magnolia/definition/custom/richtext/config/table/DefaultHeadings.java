package com.merkle.oss.magnolia.definition.custom.richtext.config.table;

import jakarta.annotation.Nullable;

public class DefaultHeadings {
    @Nullable
    public final Integer rows;
    @Nullable
    public final Integer columns;

    public DefaultHeadings(
            @Nullable final Integer rows,
            @Nullable final Integer columns
    ) {
        this.rows = rows;
        this.columns = columns;
    }

    public static class Builder {
        @Nullable
        private Integer rows;
        @Nullable
        private Integer columns;

        public Builder() {}
        public Builder(final DefaultHeadings defaultHeadings) {
            this.rows = defaultHeadings.rows;
            this.columns = defaultHeadings.columns;
        }

        public Builder rows(final int rows) {
            this.rows = rows;
            return this;
        }

        public Builder columns(final int columns) {
            this.columns = columns;
            return this;
        }

        public DefaultHeadings build() {
            return new DefaultHeadings(rows, columns);
        }
    }
}
