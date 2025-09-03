package com.merkle.oss.magnolia.definition.custom.imageset.model;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.inject.Inject;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;

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
		if (!(o instanceof ImageReferenceModel that)) {
			return false;
		}
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
        private final boolean imageFieldI18n;

        @Inject
		public Factory(
				final Set<ImageType.Resolver> imageTypeResolvers,
				final LocaleProvider localeProvider
		) {
			this(imageTypeResolvers, localeProvider, true);
		}

		protected Factory(
				final Set<ImageType.Resolver> imageTypeResolvers,
				final LocaleProvider localeProvider,
				final boolean imageFieldI18n
		) {
            this.imageTypeResolvers = imageTypeResolvers;
            this.localeProvider = localeProvider;
            this.imageFieldI18n = imageFieldI18n;
        }

		public Optional<ImageReferenceModel> create(final String propertyName, final PowerNode image) {
			return create(propertyName, localeProvider.getDefaultLocale(image), image);
		}

		public Optional<ImageReferenceModel> create(final String propertyName, final Locale dialogLocale, final PowerNode image) {
			final Locale assetIdDialogLocale = imageFieldI18n ? dialogLocale : localeProvider.getDefaultLocale(image);
			return image.getProperty(ImageSetDefinitionBuilder.SELECTION_PROPERTY_NAME_PROVIDER.apply(propertyName), assetIdDialogLocale, ValueConverter::getString).flatMap(this::resolve).flatMap(imageType ->
					image.getProperty(propertyName + imageType.getValue(), assetIdDialogLocale, ValueConverter::getString).map(assetId ->
							new ImageReferenceModel(
									assetId,
									imageType,
									image.getProperty(ImageSetDefinitionBuilder.ALT_TEXT_PROPERTY_NAME_PROVIDER.apply(propertyName), dialogLocale, ValueConverter::getString).orElse(null)
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
