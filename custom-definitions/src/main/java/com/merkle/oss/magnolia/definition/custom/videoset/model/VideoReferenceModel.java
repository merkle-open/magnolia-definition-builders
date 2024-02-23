package com.merkle.oss.magnolia.definition.custom.videoset.model;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageReferenceModel;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.ValueConverter;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class VideoReferenceModel {
	private final String assetId;
	private final VideoType videoType;
	@Nullable
	private final String altText;
	@Nullable
	private final ImageReferenceModel previewImage;

	public VideoReferenceModel(
			final String assetId,
			final VideoType videoType,
			@Nullable final String altText,
			@Nullable final ImageReferenceModel previewImage
	) {
		this.assetId = assetId;
		this.videoType = videoType;
		this.altText = altText;
		this.previewImage = previewImage;
	}

	public String getAssetId() {
		return assetId;
	}

	public VideoType getVideoType() {
		return videoType;
	}

	public Optional<String> getAltText() {
		return Optional.ofNullable(altText);
	}

	public Optional<ImageReferenceModel> getPreviewImage() {
		return Optional.ofNullable(previewImage);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VideoReferenceModel that = (VideoReferenceModel) o;
		return Objects.equals(assetId, that.assetId) && Objects.equals(videoType, that.videoType) && Objects.equals(altText, that.altText) && Objects.equals(previewImage, that.previewImage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assetId, videoType, altText, previewImage);
	}

	@Override
	public String toString() {
		return "VideoReference{" +
				"assetId='" + assetId + '\'' +
				", videoType=" + videoType +
				", altText='" + altText + '\'' +
				", previewImage=" + previewImage +
				'}';
	}

	public static class Factory {
		private final Set<VideoType.Resolver> videoTypeResolvers;
		private final ImageReferenceModel.Factory imageReferenceFactory;
		private final LocaleProvider localeProvider;

		@Inject
		public Factory(
				final LocaleProvider localeProvider,
				final Set<VideoType.Resolver> videoTypeResolvers,
				final ImageReferenceModel.Factory imageReferenceFactory
		) {
			this.localeProvider = localeProvider;
			this.videoTypeResolvers = videoTypeResolvers;
			this.imageReferenceFactory = imageReferenceFactory;
		}

		public Optional<VideoReferenceModel> create(final String propertyName, final PowerNode video) {
			return create(propertyName, localeProvider.getDefaultLocale(video), video);
		}

		public Optional<VideoReferenceModel> create(final String propertyName, final Locale dialogLocale, final PowerNode video) {
			return video.getProperty(propertyName + VideoSetDefinitionBuilder.SELECTION_SUFFIX, dialogLocale, ValueConverter::getString).flatMap(this::resolve).flatMap(videoType ->
					video.getProperty(propertyName + videoType.getValue(), dialogLocale, ValueConverter::getString).map(assetId ->
							new VideoReferenceModel(
									assetId,
									videoType,
									video.getProperty(propertyName + VideoSetDefinitionBuilder.ALT_TEXT_SUFFIX, dialogLocale, ValueConverter::getString).orElse(null),
									imageReferenceFactory
											.create(propertyName + VideoSetDefinitionBuilder.PREVIEW_IMAGE_SUFFIX, dialogLocale, video)
											.orElse(null)
							)
					)
			);
		}

		private Optional<VideoType> resolve(final String type) {
			return videoTypeResolvers.stream()
					.map(resolver -> resolver.resolve(type))
					.flatMap(Optional::stream)
					.findAny();
		}
	}
}