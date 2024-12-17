package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class LinksGroupBuilder extends AbstractToolbarGroupBuilder<LinksGroupBuilder> {

	public LinksGroupBuilder() {
		super("links");
	}

	public LinksGroupBuilder link() {
		return option("link");
	}

	public LinksGroupBuilder internalLink() {
		return option("mgnlPagesLink");
	}

	public LinksGroupBuilder damLink() {
		return option("mgnlAssetsLink");
	}
}