package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class FormattingGroupBuilder extends AbstractToolbarGroupBuilder<FormattingGroupBuilder> {

	public FormattingGroupBuilder() {
		super("formatting");
	}

	public FormattingGroupBuilder bold() {
		return option("bold");
	}

	public FormattingGroupBuilder italic() {
		return option("italic");
	}

	public FormattingGroupBuilder underline() {
		return option("underline");
	}

	public FormattingGroupBuilder strike() {
		return option("strikethrough");
	}

	public FormattingGroupBuilder subscript() {
		return option("subscript");
	}

	public FormattingGroupBuilder superscript() {
		return option("superscript");
	}

	public FormattingGroupBuilder removeFormat() {
		return option("removeFormat");
	}

	public FormattingGroupBuilder specialChar() {
		return option("specialCharacters");
	}
}
