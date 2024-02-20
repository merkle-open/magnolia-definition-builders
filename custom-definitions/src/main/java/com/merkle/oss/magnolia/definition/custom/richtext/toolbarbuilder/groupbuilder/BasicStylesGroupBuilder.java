package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class BasicStylesGroupBuilder extends AbstractToolbarGroupBuilder<BasicStylesGroupBuilder> {

	public BasicStylesGroupBuilder() {
		super("basicstyles");
	}

	public BasicStylesGroupBuilder bold() {
		return option("Bold");
	}

	public BasicStylesGroupBuilder italic() {
		return option("Italic");
	}

	public BasicStylesGroupBuilder underline() {
		return option("Underline");
	}

	public BasicStylesGroupBuilder strike() {
		return option("Strike");
	}

	public BasicStylesGroupBuilder subscript() {
		return option("Subscript");
	}

	public BasicStylesGroupBuilder superscript() {
		return option("Superscript");
	}

	public BasicStylesGroupBuilder removeFormat() {
		return option("RemoveFormat");
	}

	public BasicStylesGroupBuilder specialChar() {
		return option("SpecialChar");
	}
}
