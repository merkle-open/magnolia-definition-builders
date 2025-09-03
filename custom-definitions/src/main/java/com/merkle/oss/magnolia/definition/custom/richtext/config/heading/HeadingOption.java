package com.merkle.oss.magnolia.definition.custom.richtext.config.heading;

import java.util.Optional;

import jakarta.annotation.Nullable;

public enum HeadingOption {
    PARAGRAPH("paragraph", null, "Paragraph", "ck-heading_paragraph"),
    HEADING_1("heading1", "h1", "Heading 1", "ck-heading_heading1"),
    HEADING_2("heading2", "h2", "Heading 2", "ck-heading_heading2"),
    HEADING_3("heading3", "h3", "Heading 3", "ck-heading_heading3"),
    HEADING_4("heading4", "h4", "Heading 4", "ck-heading_heading4"),
    HEADING_5("heading5", "h5", "Heading 5", "ck-heading_heading5"),
    HEADING_6("heading6", "h6", "Heading 6", "ck-heading_heading6"),
    FORMATTED("formatted", "pre", "Formatted", "ck-heading_formatted"),
    ADDRESS("address", "address", "Address", "ck-heading_address"),
    NORMAL_DIV("normalDiv", "div", "Normal (DIV)", "ck-heading_div"),
    ;

    private final String model;
    @Nullable
    private final String view;
    private final String title;
    private final String clazz;

    HeadingOption(final String model, @Nullable final String view, final String title, final String clazz) {
        this.model = model;
        this.view = view;
        this.title = title;
        this.clazz = clazz;
    }

    public String getModel() {
        return model;
    }

    public Optional<String> getView() {
        return Optional.ofNullable(view);
    }

    public String getTitle() {
        return title;
    }

    public String getClazz() {
        return clazz;
    }
}
