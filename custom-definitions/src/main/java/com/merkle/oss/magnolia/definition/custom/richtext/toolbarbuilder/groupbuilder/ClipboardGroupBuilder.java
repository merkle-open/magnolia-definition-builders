package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class ClipboardGroupBuilder extends AbstractToolbarGroupBuilder<ClipboardGroupBuilder> {

	public ClipboardGroupBuilder() {
		super("clipboard");
	}

	public ClipboardGroupBuilder cut() {
		return option("Cut");
	}

	public ClipboardGroupBuilder copy() {
		return option("Copy");
	}

	public ClipboardGroupBuilder paste() {
		return option("Paste");
	}

	public ClipboardGroupBuilder pasteText() {
		return option("PasteText");
	}

	public ClipboardGroupBuilder pasteFromWord() {
		return option("PasteFromWord");
	}
}