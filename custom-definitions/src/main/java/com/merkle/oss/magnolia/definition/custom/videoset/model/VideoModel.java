package com.merkle.oss.magnolia.definition.custom.videoset.model;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageReferenceModel;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.powernode.PowerNode;

import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.commons.lang3.NotImplementedException;

public class VideoModel {
	private final String src;
	private final VideoType videoType;
	@Nullable
	private final ImageReferenceModel previewImage;

	public VideoModel(
			final String src,
			final VideoType videoType,
			@Nullable final ImageReferenceModel previewImage
	) {
		this.src = src;
		this.videoType = videoType;
		this.previewImage = previewImage;
	}

	public String getSrc() {
		return src;
	}

	public VideoType getVideoType() {
		return videoType;
	}


	public Optional<ImageReferenceModel> getPreviewImage() {
		return Optional.ofNullable(previewImage);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VideoModel that)) {
			return false;
		}
        return Objects.equals(src, that.src) && Objects.equals(videoType, that.videoType) && Objects.equals(previewImage, that.previewImage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(src, videoType, previewImage);
	}

	@Override
	public String toString() {
		return "Video{" +
				"src='" + src + '\'' +
				", videoType=" + videoType +
				", previewImage=" + previewImage +
				'}';
	}

	public static class Factory {
		private final LocaleProvider localeProvider;
		private final VideoReferenceModel.Factory videoReferenceFactory;
		private final Set<VideoSourceTransformer> videoSourceTransformers;

		@Inject
		public Factory(
				final LocaleProvider localeProvider,
				final VideoReferenceModel.Factory videoReferenceFactory,
				final Set<VideoSourceTransformer> videoSourceTransformers
		) {
			this.localeProvider = localeProvider;
			this.videoReferenceFactory = videoReferenceFactory;
			this.videoSourceTransformers = videoSourceTransformers;
		}

		public Optional<VideoModel> create(final Locale locale, final String propertyName, final PowerNode video) {
			return create(locale, localeProvider.getDefaultLocale(video), propertyName, video);
		}

		public Optional<VideoModel> create(final Locale locale, final Locale dialogLocale, final String propertyName, final PowerNode image) {
			return videoReferenceFactory.create(propertyName, dialogLocale, image).flatMap(videoReference ->
					create(locale, videoReference)
			);
		}

		public Optional<VideoModel> create(final Locale locale, final VideoReferenceModel videoReference) {
			return getSourceTransformer(videoReference.getVideoType()).transform(locale, videoReference.getAssetId()).map(videoSource ->
					new VideoModel(
							videoSource.src,
							videoReference.getVideoType(),
							videoReference.getPreviewImage().orElse(null)
					)
			);
		}

		protected VideoSourceTransformer getSourceTransformer(final VideoType videoType) {
			return videoSourceTransformers.stream()
					.filter(transformer -> transformer.test(videoType)).findFirst()
					.orElseThrow(() ->
							new NotImplementedException("No source transformer configured for " + videoType)
					);
		}
	}

	public interface VideoSourceTransformer extends Predicate<VideoType> {
		Optional<VideoSource> transform(Locale locale, String assetId);
	}

	public static class VideoSource {
		private final String src;

		public VideoSource(final String src) {
			this.src = src;
		}
	}
}
