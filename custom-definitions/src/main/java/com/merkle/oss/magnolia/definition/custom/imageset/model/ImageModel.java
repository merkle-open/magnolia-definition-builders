package com.merkle.oss.magnolia.definition.custom.imageset.model;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;
import com.merkle.oss.magnolia.powernode.PowerNode;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.commons.lang3.NotImplementedException;

public class ImageModel {
	private final String src;
	private final ImageType imageType;
	@Nullable
	private final String altText;

	public ImageModel(
			final String src,
			final ImageType imageType,
			@Nullable final String altText
	) {
		this.src = src;
		this.imageType = imageType;
		this.altText = altText;
	}

	public String getSrc() {
		return src;
	}

	public Optional<String> getAltText() {
		return Optional.ofNullable(altText);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ImageModel that)) {
			return false;
		}
        return Objects.equals(src, that.src) && Objects.equals(imageType, that.imageType) && Objects.equals(altText, that.altText);
	}

	@Override
	public int hashCode() {
		return Objects.hash(src, imageType, altText);
	}

	@Override
	public String toString() {
		return "Image{" +
				"src='" + src + '\'' +
				", imageType=" + imageType +
				", altText='" + altText + '\'' +
				'}';
	}

	public static class Factory {
		private final LocaleProvider localeProvider;
		private final ImageReferenceModel.Factory imageReferenceFactory;
		private final Set<ImageSourceTransformer> imageSourceTransformers;

		@Inject
		public Factory(
				final LocaleProvider localeProvider,
				final ImageReferenceModel.Factory imageReferenceFactory,
				final Set<ImageSourceTransformer> imageSourceTransformers
		) {
			this.localeProvider = localeProvider;
			this.imageReferenceFactory = imageReferenceFactory;
			this.imageSourceTransformers = imageSourceTransformers;
		}

		public Optional<ImageModel> create(final Locale locale, final String propertyName, final PowerNode image) {
			return create(locale, localeProvider.getDefaultLocale(image), propertyName, image);
		}

		public Optional<ImageModel> create(final Locale locale, final Locale dialogLocale, final String propertyName, final PowerNode image) {
			return imageReferenceFactory.create(propertyName, dialogLocale, image).flatMap(imageReference ->
					create(locale, imageReference)
			);
		}

		public Optional<ImageModel> create(final Locale locale, final ImageReferenceModel imageReference) {
			return getSourceTransformer(imageReference.getImageType()).transform(locale, imageReference.getAssetId()).map(imageSource ->
					new ImageModel(
							imageSource.src,
							imageReference.getImageType(),
							imageReference.getAltText().orElse(imageSource.altText)
					)
			);
		}

		private ImageSourceTransformer getSourceTransformer(final ImageType imageType) {
			return imageSourceTransformers.stream()
					.filter(transformer -> transformer.test(imageType)).findFirst()
					.orElseThrow(() ->
							new NotImplementedException("No source transformer configured for " + imageType)
					);
		}
	}

	public interface ImageSourceTransformer extends Predicate<ImageType> {
		Optional<ImageSource> transform(Locale locale, String assetId);
	}

	public static class ImageSource {
		private final String src;
		@Nullable
		private final String altText;

		public ImageSource(final String src, @Nullable final String altText) {
			this.src = src;
			this.altText = altText;
		}
	}
}
