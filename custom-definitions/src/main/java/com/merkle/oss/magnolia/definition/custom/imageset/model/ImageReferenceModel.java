package com.merkle.oss.magnolia.definition.custom.imageset.model;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ImageReferenceModel {
	private final String assetId;
	private final ImageType imageType;
	@Nullable
	private final String altText;

	public ImageReferenceModel(
			final String assetId,
			final ImageType imageType,
			@Nullable final String altText
	) {
		this.assetId = assetId;
		this.imageType = imageType;
		this.altText = altText;
	}

	public String getAssetId() {
		return assetId;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public Optional<String> getAltText() {
		return Optional.ofNullable(altText);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ImageReferenceModel that = (ImageReferenceModel) o;
		return Objects.equals(assetId, that.assetId) && Objects.equals(imageType, that.imageType) && Objects.equals(altText, that.altText);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assetId, imageType, altText);
	}

	@Override
	public String toString() {
		return "ImageReference{" +
				"assetId='" + assetId + '\'' +
				", imageType=" + imageType +
				", altText='" + altText + '\'' +
				'}';
	}

	public static class Factory {
		private final Set<ImageType.Resolver> imageTypeResolvers;
		private final LocaleProvider localeProvider;

		@Inject
		public Factory(
				final Set<ImageType.Resolver> imageTypeResolvers,
				final LocaleProvider localeProvider
		) {
			this.imageTypeResolvers = imageTypeResolvers;
			this.localeProvider = localeProvider;
		}

		public Optional<ImageReferenceModel> create(final String propertyName, final PowerNode image) {
			return create(propertyName, localeProvider.getDefaultLocale(image), image);
		}

		public Optional<ImageReferenceModel> create(final String propertyName, final Locale dialogLocale, final PowerNode image) {
			return image.getProperty(propertyName + ImageSetDefinitionBuilder.SELECTION_SUFFIX, dialogLocale, ValueConverter::getString).flatMap(this::resolve).flatMap(imageType ->
					image.getProperty(propertyName + imageType.getValue(), dialogLocale, ValueConverter::getString).map(assetId ->
							new ImageReferenceModel(
									assetId,
									imageType,
									image.getProperty(propertyName + ImageSetDefinitionBuilder.ALT_TEXT_SUFFIX, dialogLocale, ValueConverter::getString).orElse(null)
							)
					)
			);
		}

		private Optional<ImageType> resolve(final String type) {
			return imageTypeResolvers.stream()
					.map(resolver -> resolver.resolve(type))
					.flatMap(Optional::stream)
					.findAny();
		}
	}
}
