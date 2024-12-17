package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class ListGroupBuilder extends AbstractToolbarGroupBuilder<ListGroupBuilder> {

	public ListGroupBuilder() {
		super("list");
	}

	public ListGroupBuilder bulletedList() {
		return option("bulletedList");
	}

	public ListGroupBuilder numberedList() {
		return option("numberedList");
	}

	public ListGroupBuilder todoList() {
		return option("todoList");
	}
}