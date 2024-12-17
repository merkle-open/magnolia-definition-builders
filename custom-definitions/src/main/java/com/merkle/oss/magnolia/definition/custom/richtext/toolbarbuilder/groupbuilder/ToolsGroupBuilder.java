package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class ToolsGroupBuilder extends AbstractToolbarGroupBuilder<ToolsGroupBuilder> {

	public ToolsGroupBuilder() {
		super("tools");
	}

	public ToolsGroupBuilder source() {
		return option("sourceEditing");
	}

	public ToolsGroupBuilder codeBlock() {
		return option("codeBlock");
	}
}