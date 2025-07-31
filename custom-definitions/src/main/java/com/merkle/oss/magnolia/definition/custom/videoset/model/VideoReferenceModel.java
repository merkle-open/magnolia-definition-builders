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
	private final ImageReferenceModel previewImage;

	public VideoReferenceModel(
			final String assetId,
			final VideoType videoType,
			@Nullable final ImageReferenceModel previewImage
	) {
		this.assetId = assetId;
		this.videoType = videoType;
		this.previewImage = previewImage;
	}

	public String getAssetId() {
		return assetId;
	}

	public VideoType getVideoType() {
		return videoType;
	}

	public Optional<ImageReferenceModel> getPreviewImage() {
		return Optional.ofNullable(previewImage);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VideoReferenceModel that)) {
			return false;
		}
        return Objects.equals(assetId, that.assetId) && Objects.equals(videoType, that.videoType) && Objects.equals(previewImage, that.previewImage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assetId, videoType, previewImage);
	}

	@Override
	public String toString() {
		return "VideoReference{" +
				"assetId='" + assetId + '\'' +
				", videoType=" + videoType +
				", previewImage=" + previewImage +
				'}';
	}

	public static class Factory {
		private final Set<VideoType.Resolver> videoTypeResolvers;
		private final ImageReferenceModel.Factory imageReferenceFactory;
        private final boolean videoFieldI18n;
        private final LocaleProvider localeProvider;

		@Inject
		public Factory(
				final LocaleProvider localeProvider,
				final Set<VideoType.Resolver> videoTypeResolvers,
				final ImageReferenceModel.Factory imageReferenceFactory
		) {
			this(localeProvider, videoTypeResolvers, imageReferenceFactory, true);
		}

		protected Factory(
				final LocaleProvider localeProvider,
				final Set<VideoType.Resolver> videoTypeResolvers,
				final ImageReferenceModel.Factory imageReferenceFactory,
				final boolean videoFieldI18n
		) {
            this.localeProvider = localeProvider;
            this.videoTypeResolvers = videoTypeResolvers;
            this.imageReferenceFactory = imageReferenceFactory;
            this.videoFieldI18n = videoFieldI18n;
        }

		public Optional<VideoReferenceModel> create(final String propertyName, final PowerNode video) {
			return create(propertyName, localeProvider.getDefaultLocale(video), video);
		}

		public Optional<VideoReferenceModel> create(final String propertyName, final Locale dialogLocale, final PowerNode video) {
			final Locale assetIdDialogLocale = videoFieldI18n ? dialogLocale : localeProvider.getDefaultLocale(video);
			return video.getProperty(VideoSetDefinitionBuilder.SELECTION_PROPERTY_NAME_PROVIDER.apply(propertyName), assetIdDialogLocale, ValueConverter::getString).flatMap(this::resolve).flatMap(videoType ->
					video.getProperty(propertyName + videoType.getValue(), assetIdDialogLocale, ValueConverter::getString).map(assetId ->
							new VideoReferenceModel(
									assetId,
									videoType,
									imageReferenceFactory
											.create(VideoSetDefinitionBuilder.PREVIEW_IMAGE_PROPERTY_NAME_PROVIDER.apply(propertyName), dialogLocale, video)
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
