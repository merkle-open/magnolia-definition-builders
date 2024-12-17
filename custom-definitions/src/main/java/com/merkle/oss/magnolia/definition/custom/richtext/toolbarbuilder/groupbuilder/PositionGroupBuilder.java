package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class PositionGroupBuilder extends AbstractToolbarGroupBuilder<PositionGroupBuilder> {

	public PositionGroupBuilder() {
		super("position");
	}

	public PositionGroupBuilder alignment() {
		return option("alignment");
	}

	public PositionGroupBuilder outdent() {
		return option("outdent");
	}

	public PositionGroupBuilder indent() {
		return option("indent");
	}
}
