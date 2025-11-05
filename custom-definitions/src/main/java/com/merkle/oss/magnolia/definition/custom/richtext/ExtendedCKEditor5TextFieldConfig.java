package com.merkle.oss.magnolia.definition.custom.richtext;


import java.util.List;

import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontColor;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontFamily;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontSize;
import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.Heading;
import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.table.TableConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;

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
            final FontFamily fontFamily,
            final FontSize fontSize,
            final FontColor fontColor,
            final LinkConfig link,
            final MgnlLinkConfig mgnllink,
            final TableConfig table,
            final HtmlSupport htmlSupport,
            final boolean printDebugLogs
    ) {
        this.licenseKey = licenseKey;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.link = link;
        this.table = table;
        this.mgnllink = mgnllink;
        this.htmlSupport = htmlSupport;
        this.printDebugLogs = printDebugLogs;
        this.toolbar = toolbar;
        this.heading = new Heading(options);
    }
}
