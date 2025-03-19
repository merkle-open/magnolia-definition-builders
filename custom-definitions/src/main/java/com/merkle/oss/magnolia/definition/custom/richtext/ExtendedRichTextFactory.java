package com.merkle.oss.magnolia.definition.custom.richtext;

import static info.magnolia.ui.vaadin.ckeditor.MagnoliaCKEditorTextFieldEvents.*;

import info.magnolia.dam.api.AssetProviderRegistry;
import info.magnolia.dam.api.Item;
import info.magnolia.dam.app.field.factory.DamRichTextFieldFactory;
import info.magnolia.i18nsystem.I18nizer;
import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.repository.RepositoryConstants;
import info.magnolia.ui.dialog.DialogDefinitionRegistry;
import info.magnolia.ui.field.RichTextFieldDefinition;
import info.magnolia.ui.framework.ioc.UiComponentProvider;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5Config;
import info.magnolia.ui.vaadin.ckeditor.MagnoliaCKEditorConfig;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;
import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.machinezoo.noexception.Exceptions;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;
import com.vaadin.ui.Component;

public class ExtendedRichTextFactory extends DamRichTextFieldFactory {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final SimpleTranslator i18n;
    private final CKEditor5Config ckEditor5Config;

    @Inject
	public ExtendedRichTextFactory(
			final ExtendedRichTextDefinition definition,
			final UiComponentProvider componentProvider,
			final SimpleTranslator i18n,
			final DialogDefinitionRegistry dialogDefinitionRegistry,
			final I18nizer i18nizer,
			final AssetProviderRegistry assetProviderRegistry,
			final CKEditor5Config CKEditor5Config
	) {
		super(definition, componentProvider, i18n, dialogDefinitionRegistry, i18nizer, assetProviderRegistry, CKEditor5Config);
        this.i18n = i18n;
        ckEditor5Config = CKEditor5Config;
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
		if (getDefinition().getEditorType() != null) {
			return new ExtendedCKEditor5TextFieldConfig(
					ckEditor5Config.getCkeditor5License(),
					Collections.emptyList(),
					Collections.emptyList(),
					new LinkConfig.Builder().build(),
					new HtmlSupport.Builder().build()
			);
		}
		return new ExtendedCKEditor5TextFieldConfig(
				ckEditor5Config.getCkeditor5License(),
				getDefinition().getToolbarConfig().map(RichTextToolbarConfig::getConfig).orElseGet(Collections::emptyList),
				getDefinition().getHeadings(),
				getDefinition().getLinkConfig().orElseGet(() -> new LinkConfig.Builder().build()),
				getDefinition().getHtmlSupport().orElseGet(() -> new HtmlSupport.Builder().build())
		);
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
