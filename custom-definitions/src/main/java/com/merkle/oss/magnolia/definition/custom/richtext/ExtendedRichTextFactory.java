package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.dam.api.AssetProviderRegistry;
import info.magnolia.dam.app.field.factory.DamRichTextFieldFactory;
import info.magnolia.i18nsystem.I18nizer;
import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.ui.dialog.DialogDefinitionRegistry;
import info.magnolia.ui.framework.ioc.UiComponentProvider;
import info.magnolia.ui.vaadin.ckeditor.MagnoliaCKEditorConfig;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.RichTextToolbarConfig;
import com.vaadin.server.VaadinService;

public class ExtendedRichTextFactory extends DamRichTextFieldFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ExtendedRichTextFactory.class);

	@Inject
	public ExtendedRichTextFactory(
			final ExtendedRichTextDefinition definition,
			final UiComponentProvider componentProvider,
			final SimpleTranslator i18n,
			final DialogDefinitionRegistry dialogDefinitionRegistry,
			final I18nizer i18nizer,
			final AssetProviderRegistry assetProviderRegistry
	) {
		super(definition, componentProvider, i18n, dialogDefinitionRegistry, i18nizer, assetProviderRegistry);
	}

	@Override
	protected List<MagnoliaCKEditorConfig.ToolbarGroup> initializeToolbarConfig() {
		return Optional.ofNullable(getDefinition())
				.flatMap(ExtendedRichTextDefinition::getToolbarConfig)
				.map(RichTextToolbarConfig::getConfig)
				.orElseGet(() -> {
					LOG.info("No toolbar configuration set.");
					return Collections.emptyList();
				});
	}

	@Override
	protected MagnoliaCKEditorConfig initializeCKEditorConfig() {
		final String path = VaadinService.getCurrentRequest().getContextPath();
		@Nullable
		final String configJsFile = getDefinition().getConfigJsFile();
		getDefinition().setConfigJsFile(null); //super.initializeCKEditorConfig is not applying other configs if configJs file is present
		final MagnoliaCKEditorConfig config = super.initializeCKEditorConfig();
		if(configJsFile != null) {
			config.addExtraConfig("customConfig", "'" + path + configJsFile + "'");
		}

		// by default is allowedContent = true
		config.setAllowedContentAll();

		getDefinition().getExtraConfig().forEach(config::addExtraConfig);
		getDefinition().getExternalPlugins().forEach(config::addExternalPlugin);
		getDefinition().getExternalPlugins().keySet().forEach(config::addToExtraPlugins);
		config.setForcePasteAsPlainText(getDefinition().isForcePasteAsPlainText());
		config.setPasteFromWordRemoveFontStyles(getDefinition().isPasteFromWordRemoveFontStyles());
		config.setPasteFromWordPromptCleanup(getDefinition().isPasteFromWordPromptCleanup());
		// set enter mode (default is <p>):
		getDefinition().getEnterMode().ifPresent(config::setEnterMode);
		getDefinition().getExtraAllowedContent().ifPresent(config::setExtraAllowedContent);
		getDefinition().getContentCss().ifPresent(config::setContentsCss);
		getDefinition().getBodyClass().ifPresent(config::setBodyClass);
		getDefinition().getFormatTags().ifPresent(formatTags ->
				config.addExtraConfig("format_tags", "'" + formatTags + "'")
		);
		getDefinition().getCustomStyleSet().ifPresent(customStyleSet ->
				config.setStylesSet(customStyleSet + "?uncache= " + System.currentTimeMillis())
		);
		getDefinition().getCustomTemplates().ifPresent(customTemplates -> {
			config.addTemplatesFiles(customTemplates + "?uncache= " + System.currentTimeMillis());
			config.setTemplatesReplaceContent(false);
			getDefinition().getTemplate().ifPresent(templates ->
					config.addExtraConfig("templates", "'" + templates + "'")
			);
		});
		return config;
	}

	@Override
	protected ExtendedRichTextDefinition getDefinition() {
		return (ExtendedRichTextDefinition)super.getDefinition();
	}
}
