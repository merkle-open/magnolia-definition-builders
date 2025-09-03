package com.merkle.oss.magnolia.definition.custom.qrcode;

import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.FieldType;

import java.util.Optional;
import java.util.function.Function;

import jakarta.annotation.Nullable;

import com.merkle.oss.magnolia.powernode.PowerNode;

@FieldType("qrCodeField")
public class QrCodeFieldDefinition extends ConfiguredFieldDefinition<String> {
	private Function<PowerNode, String> valueProvider;
	private CaptionRenderType captionRenderType = CaptionRenderType.NONE;
	@Nullable
	private DownloadLinkConfig downloadLinkConfig;

	public QrCodeFieldDefinition(final Function<PowerNode, String> valueProvider) {
		this.valueProvider = valueProvider;
		setType(String.class);
		setFactoryClass(QrCodeFieldFactory.class);
	}

	public Function<PowerNode, String> getValueProvider() {
		return valueProvider;
	}

	public CaptionRenderType getCaptionRenderType() {
		return captionRenderType;
	}

	public void setCaptionRenderType(final CaptionRenderType captionRenderType) {
		this.captionRenderType = captionRenderType;
	}

	public Optional<DownloadLinkConfig> getDownloadLinkConfig() {
		return Optional.ofNullable(downloadLinkConfig);
	}

	public void setDownloadLinkConfig(final DownloadLinkConfig downloadLinkConfig) {
		this.downloadLinkConfig = downloadLinkConfig;
	}

	public enum CaptionRenderType {
		NONE,
		LABEL,
		LINK
	}

	public record DownloadLinkConfig(String text, int magnification, String imageFormat){}
}
