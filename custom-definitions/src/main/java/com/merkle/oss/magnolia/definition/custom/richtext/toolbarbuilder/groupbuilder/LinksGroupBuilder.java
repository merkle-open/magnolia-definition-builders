package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class LinksGroupBuilder extends AbstractToolbarGroupBuilder<LinksGroupBuilder> {

	public LinksGroupBuilder() {
		super("links");
	}

	public LinksGroupBuilder link() {
		return option("Link");
	}

	public LinksGroupBuilder internalLink() {
		return option("InternalLink");
	}

	public LinksGroupBuilder damLink() {
		return option("DamLink");
	}

	public LinksGroupBuilder unlink() {
		return option("Unlink");
	}
}