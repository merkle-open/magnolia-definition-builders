package com.merkle.oss.magnolia.definition.custom.richtext;

import static info.magnolia.ui.vaadin.ckeditor.MagnoliaCKEditorTextFieldEvents.*;

import info.magnolia.dam.api.AssetProviderRegistry;
import info.magnolia.dam.api.Item;
import info.magnolia.dam.app.field.factory.DamRichTextFieldFactory;
import info.magnolia.i18nsystem.FixedLocaleProvider;
import info.magnolia.i18nsystem.I18nizer;
import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.i18nsystem.TranslationService;
import info.magnolia.init.MagnoliaConfigurationProperties;
import info.magnolia.repository.RepositoryConstants;
import info.magnolia.ui.dialog.DialogDefinitionRegistry;
import info.magnolia.ui.editor.LocaleContext;
import info.magnolia.ui.field.RichTextFieldDefinition;
import info.magnolia.ui.framework.ioc.UiComponentProvider;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5Config;
import info.magnolia.ui.vaadin.ckeditor.MagnoliaCKEditorConfig;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.machinezoo.noexception.Exceptions;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontColor;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontFamily;
import com.merkle.oss.magnolia.definition.custom.richtext.config.font.FontSize;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkDecoratorDefinition;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.table.TableConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;
import com.vaadin.ui.Component;

public class ExtendedRichTextFactory extends DamRichTextFieldFactory {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final SimpleTranslator i18n;
    private final CKEditor5Config ckEditor5Config;
	private final LocaleContext localeContext;
	private final TranslationService translationService;
    private final MagnoliaConfigurationProperties properties;

    @Inject
	public ExtendedRichTextFactory(
            final ExtendedRichTextDefinition definition,
            final UiComponentProvider componentProvider,
            final SimpleTranslator i18n,
            final DialogDefinitionRegistry dialogDefinitionRegistry,
            final I18nizer i18nizer,
            final AssetProviderRegistry assetProviderRegistry,
            final CKEditor5Config CKEditor5Config,
			final LocaleContext localeContext,
			final TranslationService translationService,
            final MagnoliaConfigurationProperties properties
    ) {
		super(definition, componentProvider, i18n, dialogDefinitionRegistry, i18nizer, assetProviderRegistry, CKEditor5Config);
        this.i18n = i18n;
        ckEditor5Config = CKEditor5Config;
		this.localeContext = localeContext;
		this.translationService = translationService;
        this.properties = properties;
    }

	@Override
	protected Component createFieldComponent() {
		final ExtendedCKEditor5TextFieldConfig config = getConfig();

		final ExtendedCKEditor5TextField field = new ExtendedCKEditor5TextField();
		field.setConfig(config);

		//TODO ask magnolia to expose field instance creation in separate method
		field.addListener((eventName, value) -> {
			if (eventName.equals(EVENT_GET_MAGNOLIA_LINK)) {
				try {
					final Gson gson = new Gson();
					final PluginData pluginData = gson.fromJson(value, PluginData.class);
					openLinkDialog(field, pluginData, node -> {
						final MagnoliaLink mlink = createMagnoliaLink(node);
						field.firePluginEvent(EVENT_SEND_MAGNOLIA_LINK, new Gson().toJson(mlink));
					});
				} catch (Exception e) {
					LOG.error("openLinkDialog failed", e);
					field.firePluginEvent(EVENT_CANCEL_LINK, i18n.translate("ui-framework-core.richtexteditorexception.opentargetappfailure"));
				}
			} else if (eventName.equals("chooseAsset")) {
				openLinkDialog(field, new PluginData(DAM_WORKSPACE, value), item ->
						field.firePluginEvent("assetChosen", toAssetJson((Item) item))
				);
			}
		});
		return field;
	}

	@Override
	protected MagnoliaLink createMagnoliaLink(final Object object) {
		//TODO broken in magnolia info.magnolia.ui.field.factory.RichTextFieldFactory
		if (object instanceof Node) {
			return Exceptions.wrap().get(() -> {
				final Node node = (Node) object;
				final MagnoliaLink mlink = new MagnoliaLink();
				mlink.identifier = node.getIdentifier();
				mlink.repository = RepositoryConstants.WEBSITE;
				mlink.path = node.getPath();
				mlink.caption = node.getName();
				return mlink;
			});
		}
		return super.createMagnoliaLink(object);
	}

	private ExtendedCKEditor5TextFieldConfig getConfig() {
        final boolean printDebugLogs = properties.getBooleanProperty("magnolia.develop");
		if (getDefinition().getEditorType() != null) {
			return new ExtendedCKEditor5TextFieldConfig(
					ckEditor5Config.getCkeditor5License(),
					new ToolbarConfig.Builder().build(),
					Collections.emptyList(),
                    new FontFamily.Builder().build(),
                    new FontSize.Builder().build(),
                    new FontColor.Builder().build(),
					new LinkConfig.Builder().build(),
					new MgnlLinkConfig.Builder().build(),
					new TableConfig.Builder().build(),
					new HtmlSupport.Builder().build(),
                    printDebugLogs
			);
		}
        final MgnlLinkConfig mgnlLinkConfig = getDefinition().getMgnlLinkConfig().orElseGet(() -> new MgnlLinkConfig.Builder().build());
        final LinkConfig linkConfig = getDefinition().getLinkConfig().orElseGet(() -> new LinkConfig.Builder().build());
        return new ExtendedCKEditor5TextFieldConfig(
				ckEditor5Config.getCkeditor5License(),
				getDefinition().getToolbarConfig().orElseGet(() -> new ToolbarConfig.Builder().build()),
				getDefinition().getHeadings(),
                getDefinition().getFontFamily().orElseGet(() -> new FontFamily.Builder().build()),
                getDefinition().getFontSize().orElseGet(() -> new FontSize.Builder().build()),
                getDefinition().getFontColor().orElseGet(() -> new FontColor.Builder().build()),
				updateManualDecoratorLabels(mergeAutomaticDecorators(linkConfig, mgnlLinkConfig)),
				updateManualDecoratorLabels(removeAutomaticDecorators(mgnlLinkConfig)),
				getDefinition().getTableConfig().orElseGet(() -> new TableConfig.Builder().build()),
				getDefinition().getHtmlSupport().orElseGet(() -> new HtmlSupport.Builder().build()),
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
        final FixedLocaleProvider localeProvider = new FixedLocaleProvider(localeContext.getCurrent());
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

	@Override
	protected MagnoliaCKEditorConfig initializeCKEditorConfig() {
		throw new UnsupportedOperationException("ExtendedRichText only supports "+ RichTextFieldDefinition.CKEditorVersion.CKEDITOR_5);
	}

	@Override
	protected ExtendedRichTextDefinition getDefinition() {
		return (ExtendedRichTextDefinition)super.getDefinition();
	}
}
