package com.merkle.oss.magnolia.definition.custom.richtext.config.table;

public class TableLayoutConfig {
    public final String preferredExternalTableType;

    public TableLayoutConfig(final TableType preferredExternalTableType) {
        this.preferredExternalTableType = preferredExternalTableType.value;
    }

    public enum TableType {
        CONTENT("content"),
        LAYOUT("layout");
        private final String value;
        TableType(final String value) {
            this.value = value;
        }
    }
}
