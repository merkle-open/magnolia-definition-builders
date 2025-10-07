package com.merkle.oss.magnolia.definition.custom.example;

import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;

import java.util.List;

import com.merkle.oss.magnolia.definition.builder.simple.AssetLinkDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.childnodewrapper.ChildNodeWrapper;
import com.merkle.oss.magnolia.definition.custom.colorpicker.ColorPickerFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.linkset.LinkSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.ExtendedRichTextDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.FontToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.FormattingToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.LinksToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.ListToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.ToolsToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.UndoToolbarConfigItem;
import com.merkle.oss.magnolia.definition.custom.separator.SeparatorFieldDefinition;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoSetDefinitionBuilder;

import jakarta.inject.Provider;

/**
 * TODO remove, this is just for testing.
 */
public class CustomFormDefinitionExample<T> extends ConfiguredFormDefinition<T> {

    @Override
    public List<EditorPropertyDefinition> getProperties() {
        List<EditorPropertyDefinition> editorPropertyDefinitions = ChildNodeWrapper.wrap("teaser",
                new TextFieldDefinitionBuilder().i18n().build("title"),
                new TextFieldDefinitionBuilder().i18n().build("text"),
                new AssetLinkDefinitionBuilder().build("image")
        );

        editorPropertyDefinitions.add(new ColorPickerFieldDefinitionBuilder().history().hsvv().rgb().swatches().textField()
                        .defaultValue("#ff0000")
                .build("color")
        );

// TODO this fails already in Vaadin framework
//        editorPropertyDefinitions.add( new QrCodeFieldDefinitionBuilder()
//                .build("someName", "someQrCode"));

        editorPropertyDefinitions.add(new SeparatorFieldDefinition());

        editorPropertyDefinitions.add(new ExtendedRichTextDefinitionBuilder()
                .toolbarConfig(new SomeToolbar())
                .headings(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_3, HeadingOption.HEADING_4))
                .build("someRichText")
        );

        editorPropertyDefinitions.add(new ImageSetDefinitionBuilder().build("image"));
        editorPropertyDefinitions.add(new VideoSetDefinitionBuilder().build("video"));
        editorPropertyDefinitions.add(new LinkSetDefinitionBuilder().build("cta"));
        return editorPropertyDefinitions;
    }

    public class SomeToolbar extends ToolbarConfig {
        public static final Provider<Builder> BUILDER = () -> new ToolbarConfig.Builder()
                .item(
                        new FormattingToolbarConfigItem.Builder()
                                .bold()
                                .italic()
                                .superscript()
                                .subscript()
                                .build()
                )
                .item(
                        new ListToolbarConfigItem.Builder()
                                .numberedList()
                                .bulletedList()
                                .build()
                )
                .item(
                        new LinksToolbarConfigItem.Builder()
                                .link()
                                .internalLink()
                                .damLink()
                                .build()
                )
                .item(
                        new FontToolbarConfigItem.Builder()
                                .fontSize()
                                .fontFamily()
                                .fontColor()
                                .heading()
                                .build()
                )
                .item(
                        new UndoToolbarConfigItem.Builder()
                                .undo()
                                .redo()
                                .build()
                )
                .item(
                        new ToolsToolbarConfigItem.Builder()
                                .source()
                                .build()
                );

        public SomeToolbar() {
            super(BUILDER.get().build());
        }
    }
}
