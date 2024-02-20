package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class StylingGroupBuilder extends AbstractToolbarGroupBuilder<StylingGroupBuilder> {

	public StylingGroupBuilder() {
		super("styling");
	}

	public StylingGroupBuilder format() {
		return option("Format");
	}

	public StylingGroupBuilder styles() {
		return option("Styles");
	}

	public StylingGroupBuilder templates() {
		return option("Templates");
	}
}