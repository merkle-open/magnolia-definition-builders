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
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbar;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.ToolbarGroup;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.FontGroupBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.FormattingGroupBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.LinksGroupBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.ListGroupBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.ToolsGroupBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder.UndoGroupBuilder;
import com.merkle.oss.magnolia.definition.custom.separator.SeparatorFieldDefinition;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoSetDefinitionBuilder;

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

    public static class SomeToolbar implements RichTextToolbarConfig {
        @Override
        public List<ToolbarGroup> getConfig() {
            return RichTextToolbar.builder()
                    .add(new FormattingGroupBuilder()
                            .bold()
                            .italic()
                            .superscript()
                            .specialChar()
                    )
                    .add(new ListGroupBuilder()
                            .numberedList()
                            .bulletedList()
                    )
                    .add(new LinksGroupBuilder()
                            .link()
                            .internalLink()
                            .damLink()
                    )
                    .add(new FontGroupBuilder()
                            .heading()
                    )
                    .add(new UndoGroupBuilder()
                            .undo()
                            .redo()
                    )
                    .add(new ToolsGroupBuilder().source())
                    .build()
                    .getConfig();
        }
    }
}