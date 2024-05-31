package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class OptionsGroupBuilder extends AbstractToolbarGroupBuilder<OptionsGroupBuilder> {

	public OptionsGroupBuilder() {
		super("options");
	}

	public OptionsGroupBuilder showBlocks() {
		return option("ShowBlocks");
	}
}