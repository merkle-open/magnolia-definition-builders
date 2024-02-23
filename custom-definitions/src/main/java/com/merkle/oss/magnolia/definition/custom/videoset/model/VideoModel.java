package com.merkle.oss.magnolia.definition.custom.videoset.model;

import com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider;
import com.merkle.oss.magnolia.definition.custom.imageset.model.ImageReferenceModel;
import com.merkle.oss.magnolia.definition.custom.videoset.VideoType;
import com.merkle.oss.magnolia.powernode.PowerNode;
import org.apache.commons.lang.NotImplementedException;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class VideoModel {
	private final String src;
	private final VideoType videoType;
	@Nullable
	private final String altText;
	@Nullable
	private final ImageReferenceModel previewImage;

	public VideoModel(
			final String src,
			final VideoType videoType,
			@Nullable final String altText,
			@Nullable final ImageReferenceModel previewImage
	) {
		this.src = src;
		this.videoType = videoType;
		this.altText = altText;
		this.previewImage = previewImage;
	}

	public String getSrc() {
		return src;
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
		VideoModel video = (VideoModel) o;
		return Objects.equals(src, video.src) && Objects.equals(videoType, video.videoType) && Objects.equals(altText, video.altText) && Objects.equals(previewImage, video.previewImage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(src, videoType, altText, previewImage);
	}

	@Override
	public String toString() {
		return "Video{" +
				"src='" + src + '\'' +
				", videoType=" + videoType +
				", altText='" + altText + '\'' +
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
							videoReference.getAltText().orElse(videoSource.altText),
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
		@Nullable
		private final String altText;

		public VideoSource(final String src, @Nullable final String altText) {
			this.src = src;
			this.altText = altText;
		}
	}
}