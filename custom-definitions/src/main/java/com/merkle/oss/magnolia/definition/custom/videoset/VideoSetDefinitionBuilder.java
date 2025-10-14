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
		this(new ImageSetDefinitionBuilder(), false);
	}

	public VideoSetDefinitionBuilder(final AbstractImageSetDefinitionBuilder<?> imageSetDefinitionBuilder, final boolean removePreviouslySelected) {
		super(imageSetDefinitionBuilder, LABEL_PREFIX, removePreviouslySelected);
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
