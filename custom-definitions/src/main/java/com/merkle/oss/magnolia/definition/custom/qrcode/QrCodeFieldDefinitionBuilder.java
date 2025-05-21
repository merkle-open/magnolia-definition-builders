package com.merkle.oss.magnolia.definition.custom.qrcode;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.builder.simple.AbstractConfiguredFieldDefinitionBuilder;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;

import com.merkle.oss.magnolia.definition.custom.qrcode.QrCodeFieldDefinition.CaptionRenderType;
import com.merkle.oss.magnolia.definition.custom.qrcode.QrCodeFieldDefinition.DownloadLinkConfig;

/**
 * builds a {@link QrCodeFieldDefinition}
 * @author Merkle DACH
 */
public class QrCodeFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<String, QrCodeFieldDefinition, QrCodeFieldDefinitionBuilder> {
	@Nullable
	private CaptionRenderType captionRenderType;
	@Nullable
	private DownloadLinkConfig downloadLink;

	public QrCodeFieldDefinitionBuilder() {}
	public QrCodeFieldDefinitionBuilder(final QrCodeFieldDefinition definition) {
		super(definition);
		captionRenderType(definition.getCaptionRenderType());
		definition.getDownloadLinkConfig().ifPresent(downloadLinkConfig ->
			downloadLink(ignored -> Optional.of(downloadLinkConfig))
		);
	}

	public QrCodeFieldDefinitionBuilder captionRenderType(final CaptionRenderType captionRenderType) {
		this.captionRenderType = captionRenderType;
		return self();
	}

	public QrCodeFieldDefinitionBuilder downloadLink(final Function<DownloadLinkConfigBuilder, Optional<DownloadLinkConfig>> builder) {
		this.downloadLink = builder.apply(new DownloadLinkConfigBuilder()).orElse(null);
		return self();
	}

	public QrCodeFieldDefinition build(final String name, final String propertyName) {
		return build(name, node -> node.getProperty(propertyName, ValueConverter::getString).orElseThrow(() ->
			new NullPointerException("node " + node.getPath() + " has no property " + propertyName)
		));
	}
	public QrCodeFieldDefinition build(final String name, final Function<PowerNode, String> valueProvider) {
		final QrCodeFieldDefinition definition = new QrCodeFieldDefinition(valueProvider);
		super.populate(definition, name);
		Optional.ofNullable(captionRenderType).ifPresent(definition::setCaptionRenderType);
		Optional.ofNullable(downloadLink).ifPresent(definition::setDownloadLinkConfig);
		return definition;
	}

	public static class DownloadLinkConfigBuilder {
		private int magnification = 10;
		private String imageFormat = "png";

		private DownloadLinkConfigBuilder() {}

		public DownloadLinkConfigBuilder magnification(final int magnification) {
			this.magnification = magnification;
			return this;
		}

		public DownloadLinkConfigBuilder imageFormat(final String imageFormat) {
			this.imageFormat = imageFormat;
			return this;
		}

		public DownloadLinkConfig build(final String linkText) {
			return new DownloadLinkConfig(
				linkText,
				magnification,
				imageFormat
			);
		}
	}
}
