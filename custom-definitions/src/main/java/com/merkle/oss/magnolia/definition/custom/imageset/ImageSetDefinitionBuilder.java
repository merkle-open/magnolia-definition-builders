package com.merkle.oss.magnolia.definition.custom.imageset;

import com.merkle.oss.magnolia.definition.builder.simple.AssetLinkDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.SingleSwitchableForm;

public class ImageSetDefinitionBuilder extends AbstractImageSetDefinitionBuilder<ImageSetDefinitionBuilder> {
	protected static final String LABEL_PREFIX = "merkle.customDefinitions.imageSet.";

	public ImageSetDefinitionBuilder() {
		super(LABEL_PREFIX);
	}

	@Override
	protected FieldOption<ImageType> createFieldOption(final ImageType imageType) {
		if(ImageTypes.DAM.equals(imageType)) {
			return dam();
		}
		throw new IllegalArgumentException("Unsupported image type "+imageType);
	}

	protected FieldOption<ImageType> dam() {
		return new FieldOption<>(
				ImageTypes.DAM,
				n -> new SingleSwitchableForm<>(
						new AssetLinkDefinitionBuilder()
								.label(LABEL_PREFIX + ImageTypes.DAM.getLabel())
								.build(n)
				)
		);
	}
}
