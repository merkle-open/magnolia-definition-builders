package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class ImageGroupBuilder extends AbstractToolbarGroupBuilder<ImageGroupBuilder> {

	public ImageGroupBuilder() {
		super("image");
	}

	public ImageGroupBuilder image() {
		return option("insertImage");
	}
}