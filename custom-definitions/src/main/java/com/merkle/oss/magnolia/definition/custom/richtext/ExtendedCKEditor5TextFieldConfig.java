package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.ToolbarGroup;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.FontGroupBuilder;

public class ExtendedCKEditor5TextFieldConfig extends CKEditor5TextFieldConfig {
    public final Heading heading;
    public final LinkConfig link;

    public ExtendedCKEditor5TextFieldConfig(
            final String licenseKey,
            final List<ToolbarGroup> toolbarGroups,
            final List<HeadingOption> options,
            final LinkConfig link
    ) {
        super(licenseKey);
        this.link = link;
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

    /**
     * Represents a heading configuration.
     */
    //   See implementation here:
    //   https://github.com/ckeditor/ckeditor5/blob/v41.4.2/packages/ckeditor5-heading/src/utils.ts#L34
    public static class Heading {
        public final List<Option> options;

        public Heading(final List<HeadingOption> options) {
            this.options = options.stream().map(Option::new).collect(Collectors.toList());
        }

        /**
         * Represents a heading option.
         */
        //   See implementation here:
        // https://github.com/ckeditor/ckeditor5/blob/v41.4.2/packages/ckeditor5-heading/src/headingconfig.ts#L76
        public static class Option {
            public final String model;
            public final String view;
            public final String title;
            public final String clazz;

            public Option(final HeadingOption option) {
                this.model = option.getModel();
                this.view = option.getView().orElse(null);
                this.title = option.getTitle();
                this.clazz = option.getClazz();
            }
        }
    }
}
