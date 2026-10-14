package com.merkle.oss.magnolia.definition.custom.richtext;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.FontToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.FormattingToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.ImageToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.LinksToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.ListToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.PositionToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.TableToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.ToolsToolbarConfigItem;

public class ExtendedCKEditor5TextFieldConfig {
    private static final Map<ToolbarConfigItem.NestedToolbarConfigItem, Set<String>> PLUGIN_MAPPING = Stream.of(
            map(new FontToolbarConfigItem.Builder().heading(), "Heading"),
            map(new FontToolbarConfigItem.Builder().fontColor(), "FontColor", "FontBackgroundColor"),
            map(new FontToolbarConfigItem.Builder().fontFamily(), "FontFamily"),
            map(new FontToolbarConfigItem.Builder().fontSize(), "FontSize"),
            map(new PositionToolbarConfigItem.Builder().alignment(), "Alignment"),
            map(new FormattingToolbarConfigItem.Builder().bold(), "Bold"),
            map(new FormattingToolbarConfigItem.Builder().italic(), "Italic"),
            map(new FormattingToolbarConfigItem.Builder().underline(), "Underline"),
            map(new FormattingToolbarConfigItem.Builder().strike(), "Strikethrough"),
            map(new FormattingToolbarConfigItem.Builder().subscript(), "Subscript"),
            map(new FormattingToolbarConfigItem.Builder().superscript(), "Superscript"),
            map(new FormattingToolbarConfigItem.Builder().specialChar(), "SpecialCharacters", "SpecialCharactersEssentials"),
            map(new ListToolbarConfigItem.Builder().bulletedList().numberedList().todoList(), "List"),
            map(new TableToolbarConfigItem.Builder().table(), "Table", "TableToolbar", "TableColumnResize", "TableProperties", "TableCellProperties", "TableCaption"),
            map(new ImageToolbarConfigItem.Builder().image(), "Image", "ImageToolbar", "ImageCaption", "ImageStyle", "ImageResize", "LinkImage", "ImageInsert", "ImageTextAlternative", "MgnlImageInsert"),
            map(new ToolsToolbarConfigItem.Builder().source(), "SourceEditing"),
            map(new LinksToolbarConfigItem.Builder().damLink().internalLink(), "MgnlLink"),
            map(new LinksToolbarConfigItem.Builder().link(), "Link")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    private static Map.Entry<ToolbarConfigItem.NestedToolbarConfigItem, Set<String>> map(final ToolbarConfigItem.NestedToolbarConfigItem.AbstractBuilder<?> builder, final String... plugins) {
        return Map.entry(builder.build(), Set.of(plugins));
    }

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
    public final Set<String> removePlugins;

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
        this.removePlugins = PLUGIN_MAPPING.entrySet().stream()
                .filter(entry -> !toolbar.containsToolbarConfigItem(entry.getKey()))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
