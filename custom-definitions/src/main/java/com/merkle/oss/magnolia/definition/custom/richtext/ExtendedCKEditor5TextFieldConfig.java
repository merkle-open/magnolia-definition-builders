package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontColor;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontFamily;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontSize;

import java.util.List;
import java.util.function.Consumer;

import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.Heading;
import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.table.TableConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.FontToolbarConfigItem;

public class ExtendedCKEditor5TextFieldConfig {
    public final ToolbarConfig toolbar;
    public final FontFamily fontFamily;
    public final FontSize fontSize;
    public final FontColor fontColor;
    public final String licenseKey;
    public final Heading heading;
    public final LinkConfig link;
    public final TableConfig table;
    public final MgnlLinkConfig mgnllink;
    public final HtmlSupport htmlSupport;
    public final boolean printDebugLogs;

    public ExtendedCKEditor5TextFieldConfig(
            final String licenseKey,
            final ToolbarConfig toolbar,
            final List<HeadingOption> options,
            final LinkConfig link,
            final MgnlLinkConfig mgnllink,
            final TableConfig table,
            final HtmlSupport htmlSupport,
            final boolean printDebugLogs
    ) {
        this.licenseKey = licenseKey;
        this.link = link;
        this.table = table;
        this.mgnllink = mgnllink;
        this.htmlSupport = htmlSupport;
        this.printDebugLogs = printDebugLogs;
        this.toolbar = toolbar;
        this.heading = new Heading(options);
        this.fontFamily = toolbar.getToolbarConfigItem(FontToolbarConfigItem.class).map(fontToolbarGroup ->
            apply(new FontFamily(), fontFamily -> fontFamily.options = fontToolbarGroup.getFonts())
        ).orElseGet(FontFamily::new);
        this.fontSize = toolbar.getToolbarConfigItem(FontToolbarConfigItem.class).map(fontToolbarGroup ->
                apply(new FontSize(), fontSize -> fontSize.options = fontToolbarGroup.getFontSizes())
        ).orElseGet(FontSize::new);
        this.fontColor = toolbar.getToolbarConfigItem(FontToolbarConfigItem.class).map(fontToolbarGroup ->
                apply(new FontColor(), fontColor -> fontColor.colors = fontToolbarGroup.getFontColors())
        ).orElseGet(FontColor::new);
    }

    private <T> T apply(final T t, final Consumer<T> consumer) {
        consumer.accept(t);
        return t;
    }
}
