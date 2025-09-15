package com.merkle.oss.magnolia.definition.custom.videoset;

import com.merkle.oss.magnolia.definition.builder.simple.AssetLinkDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.AbstractImageSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.imageset.ImageSetDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.SingleSwitchableForm;

import javax.inject.Provider;

public class VideoSetDefinitionBuilder extends AbstractVideoSetDefinitionBuilder<VideoSetDefinitionBuilder> {
	protected static final String LABEL_PREFIX = "merkle.customDefinitions.videoSet.";
	protected static final String FIELD_LABEL_PREFIX = LABEL_PREFIX + "field.";

	public VideoSetDefinitionBuilder() {
		this(true, false);
	}

	public VideoSetDefinitionBuilder(final boolean videoFieldI18n, final boolean removePreviouslySelected) {
		this(new ImageSetDefinitionBuilder(true, removePreviouslySelected), videoFieldI18n, false, removePreviouslySelected);
	}

	public VideoSetDefinitionBuilder(
			final AbstractImageSetDefinitionBuilder<?> imageSetDefinitionBuilder,
			final boolean videoFieldI18n,
			final boolean previewImageRequired,
			final boolean removePreviouslySelected
	) {
		super(imageSetDefinitionBuilder, LABEL_PREFIX, videoFieldI18n, previewImageRequired, removePreviouslySelected);
	}

	@Override
	protected FieldOption<VideoType> createFieldOption(final VideoType videoType) {
		if(VideoTypes.DAM.equals(videoType)) {
			return dam();
		}
		if(VideoTypes.YOUTUBE.equals(videoType)) {
			return youtube();
		}
		if(VideoTypes.VIMEO.equals(videoType)) {
			return vimeo();
		}
		throw new IllegalArgumentException("Unsupported video type " + videoType);
	}

	private FieldOption<VideoType> vimeo() {
		return new FieldOption<>(
				VideoTypes.VIMEO,
				name -> new SingleSwitchableForm<>(
						new TextFieldDefinitionBuilder()
								.label(FIELD_LABEL_PREFIX + VideoTypes.VIMEO.getLabel())
								.description(FIELD_LABEL_PREFIX + VideoTypes.VIMEO.getLabel().replaceAll(".label$", ".description"))
								.converterClass(VimeoTextValueConverter.class)
								.build(name)
				)
		);
	}

	private FieldOption<VideoType> youtube() {
		return new FieldOption<>(
				VideoTypes.YOUTUBE,
				name -> new SingleSwitchableForm<>(
						new TextFieldDefinitionBuilder()
								.label(FIELD_LABEL_PREFIX + VideoTypes.YOUTUBE.getLabel())
								.description(FIELD_LABEL_PREFIX + VideoTypes.YOUTUBE.getLabel().replaceAll(".label$", ".description"))
								.converterClass(YoutubeTextValueConverter.class)
								.build(name)
				)
		);
	}

	private FieldOption<VideoType> dam() {
		return new FieldOption<>(
				VideoTypes.DAM,
				name -> new SingleSwitchableForm<>(
						new AssetLinkDefinitionBuilder()
								.label(FIELD_LABEL_PREFIX + VideoTypes.DAM.getLabel())
								.build(name)
				)
		);
	}
}
