package com.merkle.oss.magnolia.definition.custom.imageset;

import com.merkle.oss.magnolia.definition.builder.simple.AssetLinkDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import com.merkle.oss.magnolia.definition.custom.switchable.SingleSwitchableForm;
import com.merkle.oss.magnolia.definition.custom.validator.ValidateEmptyFieldBinder;

public class ImageSetDefinitionBuilder extends AbstractImageSetDefinitionBuilder<ImageSetDefinitionBuilder> {
	protected static final String LABEL_PREFIX = "merkle.customDefinitions.imageSet.";
	protected static final String FIELD_LABEL_PREFIX = LABEL_PREFIX + "field.";

	public ImageSetDefinitionBuilder() {
		this(true, false);
	}
	public ImageSetDefinitionBuilder(final boolean imageFieldI18n, final boolean removePreviouslySelected) {
		super(LABEL_PREFIX, imageFieldI18n, removePreviouslySelected);
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
								.label(FIELD_LABEL_PREFIX + ImageTypes.DAM.getLabel())
                                .fieldBinderClass((Class)ValidateEmptyFieldBinder.Link.class)
								.build(n)
				)
		);
	}
}
