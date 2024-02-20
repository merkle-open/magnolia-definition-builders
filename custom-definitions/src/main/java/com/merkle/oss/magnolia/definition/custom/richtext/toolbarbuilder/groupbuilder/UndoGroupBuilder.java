package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class UndoGroupBuilder extends AbstractToolbarGroupBuilder<UndoGroupBuilder> {

	public UndoGroupBuilder() {
		super("undo");
	}

	public UndoGroupBuilder undo() {
		return option("Undo");
	}

	public UndoGroupBuilder redo() {
		return option("Redo");
	}
}