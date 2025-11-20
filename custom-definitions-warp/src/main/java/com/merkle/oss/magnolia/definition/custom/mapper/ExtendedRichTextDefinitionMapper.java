package com.merkle.oss.magnolia.definition.custom.mapper;

import static info.magnolia.warp.engine.form.embedded.formschemapojos.PropertyType.STRING;

import info.magnolia.i18nsystem.FixedLocaleProvider;
import info.magnolia.i18nsystem.TranslationService;
import info.magnolia.init.MagnoliaConfigurationProperties;
import info.magnolia.ui.editor.LocaleContext;
import info.magnolia.ui.field.RichTextFieldDefinition;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5Config;
import info.magnolia.warp.engine.form.embedded.formschemapojos.Property;
import info.magnolia.warp.engine.form.embedded.formschemapojos.PropertyWidgetType;
import info.magnolia.warp.engine.form.embedded.mapper.field.scalar.RichTextFieldDefinitionMapper;
import info.magnolia.warp.engine.form.util.FormDefinitionProblemUtil;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.merkle.oss.magnolia.definition.custom.richtext.ExtendedCKEditor5TextFieldConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.ExtendedRichTextDefinition;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontColor;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontFamily;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontSize;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkDecoratorDefinition;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.table.TableConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;

import jakarta.inject.Inject;

public class ExtendedRichTextDefinitionMapper extends RichTextFieldDefinitionMapper {
    private final CKEditor5Config ckEditor5Config;
    private final TranslationService translationService;
    private final MagnoliaConfigurationProperties properties;

    @Inject
    protected ExtendedRichTextDefinitionMapper(
            final CKEditor5Config ckEditor5Config,
            final FormDefinitionProblemUtil formDefinitionProblemUtil,
            final TranslationService translationService,
            final MagnoliaConfigurationProperties properties
    ) {
        super(ckEditor5Config, formDefinitionProblemUtil);
        this.ckEditor5Config = ckEditor5Config;
        this.translationService = translationService;
        this.properties = properties;
    }

    @Override
    public Collection<Type> getSupportedClasses() {
        return Collections.singleton(ExtendedRichTextDefinition.class);
    }

    @Override
    public Property.PropertyBuilder addFieldProperties(RichTextFieldDefinition definition, Property.PropertyBuilder builder) {
        final ExtendedRichTextDefinition richTextFieldDefinition = (ExtendedRichTextDefinition) definition;
        return super.addFieldProperties(definition, builder)
                .type(STRING)
                .uiBuilder()
                .widget(PropertyWidgetType.RICHTEXT)
                .options(Map.of(
                        HEIGHT, definition.getHeight(),
                        CK_EDITOR_CONFIG, get(richTextFieldDefinition)
                )).uiDone();
    }

    private ExtendedCKEditor5TextFieldConfig get(final ExtendedRichTextDefinition definition) {
        final boolean printDebugLogs = properties.getBooleanProperty("magnolia.develop");
        final MgnlLinkConfig mgnlLinkConfig = definition.getMgnlLinkConfig().orElseGet(() -> new MgnlLinkConfig.Builder().build());
        final LinkConfig linkConfig = definition.getLinkConfig().orElseGet(() -> new LinkConfig.Builder().build());
        return new ExtendedCKEditor5TextFieldConfig(
                ckEditor5Config.getCkeditor5License(),
                definition.getToolbarConfig().orElseGet(() -> new ToolbarConfig.Builder().build()),
                definition.getHeadings(),
                definition.getFontFamily().orElseGet(() -> new FontFamily.Builder().build()),
                definition.getFontSize().orElseGet(() -> new FontSize.Builder().build()),
                definition.getFontColor().orElseGet(() -> new FontColor.Builder().build()),
                updateManualDecoratorLabels(mergeAutomaticDecorators(linkConfig, mgnlLinkConfig)),
                updateManualDecoratorLabels(removeAutomaticDecorators(mgnlLinkConfig)),
                definition.getTableConfig().orElseGet(() -> new TableConfig.Builder().build()),
                definition.getHtmlSupport().orElseGet(() -> new HtmlSupport.Builder().build()),
                printDebugLogs
        );
    }

    /*
     * Magnolia links only support manual decorators. However, they are also applied if they are added to normal links.
     */
    private LinkConfig mergeAutomaticDecorators(final LinkConfig linkConfig, final MgnlLinkConfig mgnlLinkConfig) {
        final LinkConfig.Builder builder = new LinkConfig.Builder(linkConfig);
        mgnlLinkConfig.decorators.entrySet().stream()
                .filter(entry -> Objects.equals(LinkDecoratorDefinition.AutomaticBuilder.MODE, entry.getValue().mode))
                .forEach(entry ->
                        builder.decorator("mgnl-" + entry.getKey(), entry.getValue())
                );
        return builder.build();
    }
    private MgnlLinkConfig removeAutomaticDecorators(final MgnlLinkConfig mgnlLinkConfig) {
        return new MgnlLinkConfig.Builder(mgnlLinkConfig).decorators(
                mgnlLinkConfig.decorators.entrySet().stream()
                        .filter(entry -> !Objects.equals(LinkDecoratorDefinition.AutomaticBuilder.MODE, entry.getValue().mode))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        ).build();
    }

    private LinkConfig updateManualDecoratorLabels(final LinkConfig linkConfig) {
        return new LinkConfig.Builder(linkConfig).decorators(updateManualDecoratorLabels(linkConfig.decorators)).build();
    }
    private MgnlLinkConfig updateManualDecoratorLabels(final MgnlLinkConfig mgnlLinkConfig) {
        return new MgnlLinkConfig.Builder(mgnlLinkConfig).decorators(updateManualDecoratorLabels(mgnlLinkConfig.decorators)).build();
    }
    private Map<String, LinkDecoratorDefinition> updateManualDecoratorLabels(final Map<String, LinkDecoratorDefinition> decorators) {
        final FixedLocaleProvider localeProvider = new FixedLocaleProvider(Locale.GERMAN); //TODO
        return decorators.entrySet().stream()
                .map(entry -> {
                    if(Objects.equals(LinkDecoratorDefinition.ManualBuilder.MODE, entry.getValue().mode)) {
                        return Map.entry(
                                entry.getKey(),
                                new LinkDecoratorDefinition.ManualBuilder(entry.getValue()).build(
                                        translationService.translate(localeProvider, new String[] { entry.getValue().label })
                                )
                        );
                    }
                    return entry;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
