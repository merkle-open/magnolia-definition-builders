package com.merkle.oss.magnolia.definition.custom.qrcode;

import info.magnolia.ui.ValueContext;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.output.Java2DRenderer;
import uk.org.okapibarcode.output.SvgRenderer;

public class QrCodeField extends CustomField<String> {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final QrCodeFieldDefinition definition;
	private final PowerNodeService powerNodeService;
	private final ValueContext<Node> valueContext;

	public QrCodeField(final QrCodeFieldDefinition definition, final PowerNodeService powerNodeService, final ValueContext<Node> valueContext) {
		this.definition = definition;
		this.powerNodeService = powerNodeService;
		this.valueContext = valueContext;
	}

	private Image getImage(final String value) {
		final Image image = new Image();
		image.setWidth("50%");
		image.setSource(new StreamResource(
			(StreamResource.StreamSource) () -> generateQRCodeSvgStream(value).orElseGet(InputStream::nullInputStream),
			getFilename(value, "svg")
		));
		return image;
	}

	private Optional<Component> getCaption(final QrCodeFieldDefinition definition, final String value) {
		return switch (definition.getCaptionRenderType()) {
			case NONE -> Optional.empty();
			case LABEL -> Optional.of(new Label(value));
			case LINK -> {
				final Link link = new Link(value, new ExternalResource(value));
				link.setTargetName("_blank");
				yield Optional.of(link);
			}
		};
	}

	private Optional<Link> getDownloadLink(final QrCodeFieldDefinition definition, final String value) {
		return definition.getDownloadLinkConfig().map(config -> {
			final Link link = new Link(config.text(), new StreamResource(
				(StreamResource.StreamSource) () -> generateQRCodeImageStream(value, config.magnification(), config.imageFormat()).orElseGet(InputStream::nullInputStream),
				getFilename(value, config.imageFormat())
			));
			link.setTargetName("_blank");
			return link;
		});
	}

	@Override
	protected Component initContent() {
		final PowerNode node = valueContext.getSingle().map(powerNodeService::convertToPowerNode).orElseThrow(() ->
			new NullPointerException("node not present!")
		);
		final String value = definition.getValueProvider().apply(node);
		return new VerticalLayout(Stream.of(
			Stream.of(getImage(value)),
			getCaption(definition, value).stream(),
			getDownloadLink(definition, value).stream()
		).flatMap(Function.identity()).toArray(Component[]::new));
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	protected void doSetValue(@Nullable final String ignored) {
	}

	private String getFilename(@Nullable final String value, final String fileType) {
		return "qrcode-" + UUID.nameUUIDFromBytes(String.valueOf(value).getBytes()) + "."+fileType;
	}

	private Optional<InputStream> generateQRCodeSvgStream(final String value) {
		try {
			final QrCode qrCode = new QrCode();
			qrCode.setEmptyContentAllowed(true);
			qrCode.setContent(value);
			final ByteArrayOutputStream stream = new ByteArrayOutputStream();
			final SvgRenderer renderer = new SvgRenderer(stream, 1, Color.WHITE, Color.BLACK, true);
			renderer.render(qrCode);
			return Optional.of(new ByteArrayInputStream(stream.toByteArray()));
		} catch (final Exception e) {
			LOG.error("Failed to generate svg qrCode!", e);
			return Optional.empty();
		}
	}

	private Optional<InputStream> generateQRCodeImageStream(final String value, final int magnification, final String format) {
		try {
			final QrCode qrCode = new QrCode();
			qrCode.setEmptyContentAllowed(true);
			qrCode.setContent(value);
			final ByteArrayOutputStream stream = new ByteArrayOutputStream();
			final BufferedImage image = new BufferedImage(qrCode.getWidth() * magnification, qrCode.getHeight() * magnification, BufferedImage.TYPE_BYTE_GRAY);
			final Graphics2D g2d = image.createGraphics();
			final Java2DRenderer renderer = new Java2DRenderer(g2d, magnification, Color.WHITE, Color.BLACK);
			renderer.render(qrCode);
			ImageIO.write(image, format, stream);
			return Optional.of(new ByteArrayInputStream(stream.toByteArray()));
		} catch (final Exception e) {
			LOG.error("Failed to generate image qrCode!", e);
			return Optional.empty();
		}
	}
}
