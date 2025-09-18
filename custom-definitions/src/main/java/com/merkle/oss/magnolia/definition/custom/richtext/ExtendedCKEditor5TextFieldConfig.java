package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.Heading;
import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.ToolbarGroup;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.FontGroupBuilder;

public class ExtendedCKEditor5TextFieldConfig extends CKEditor5TextFieldConfig {
    public final Heading heading;
    public final LinkConfig link;
    public final LinkConfig mgnllink;
    public final HtmlSupport htmlSupport;

    public ExtendedCKEditor5TextFieldConfig(
            final String licenseKey,
            final List<ToolbarGroup> toolbarGroups,
            final List<HeadingOption> options,
            final LinkConfig link,
            final LinkConfig mgnllink,
            final HtmlSupport htmlSupport
    ) {
        super(licenseKey);
        this.link = link;
        this.mgnllink = mgnllink;
        this.htmlSupport = htmlSupport;
        this.toolbar = new ExtendedToolbar(toolbarGroups, true);
        this.heading = new Heading(options);
        getToolbarGroup(toolbarGroups, FontGroupBuilder.FontToolbarGroup.class).ifPresent(fontToolbarGroup -> {
            this.fontFamily = apply(new FontFamily(), fontFamily -> fontFamily.options = fontToolbarGroup.getFonts());
            this.fontSize = apply(new FontSize(), fontSize -> fontSize.options = fontToolbarGroup.getFontSizes());
            this.fontColor = apply(new FontColor(), fontColor -> fontColor.colors = fontToolbarGroup.getFontColors());
        });
    }

    private <T> T apply(final T t, final Consumer<T> consumer) {
        consumer.accept(t);
        return t;
    }

    private <T extends ToolbarGroup> Optional<T> getToolbarGroup(final List<ToolbarGroup> toolbarGroups, final Class<T> clazz) {
        return toolbarGroups.stream()
                .filter(clazz::isInstance)
                .findFirst()
                .map(clazz::cast);
    }

    public static class ExtendedToolbar extends Toolbar {
        public ExtendedToolbar(final List<ToolbarGroup> toolbarGroups, final boolean shouldNotGroupWhenFull) {
            super.shouldNotGroupWhenFull = shouldNotGroupWhenFull;
            super.items = toolbarGroups.stream().flatMap(group ->
               Stream.concat(
                       group.getItems().stream(),
                       Stream.of("|")
               )
            ).collect(Collectors.toList());
        }
    }
}
