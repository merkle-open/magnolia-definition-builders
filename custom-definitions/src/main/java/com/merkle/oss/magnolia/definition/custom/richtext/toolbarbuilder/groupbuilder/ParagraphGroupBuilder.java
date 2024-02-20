package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class ParagraphGroupBuilder extends AbstractToolbarGroupBuilder<ParagraphGroupBuilder> {

	public ParagraphGroupBuilder() {
		super("paragraph");
	}

	public ParagraphGroupBuilder numberedList() {
		return option("NumberedList");
	}

	public ParagraphGroupBuilder bulletedList() {
		return option("BulletedList");
	}

	public ParagraphGroupBuilder justifyLeft() {
		return option("JustifyLeft");
	}

	public ParagraphGroupBuilder justifyCenter() {
		return option("JustifyCenter");
	}

	public ParagraphGroupBuilder justifyRight() {
		return option("JustifyRight");
	}

	public ParagraphGroupBuilder justifyBlock() {
		return option("JustifyBlock");
	}

	public ParagraphGroupBuilder image() {
		return option("Image");
	}

	public ParagraphGroupBuilder table() {
		return option("Table");
	}
}