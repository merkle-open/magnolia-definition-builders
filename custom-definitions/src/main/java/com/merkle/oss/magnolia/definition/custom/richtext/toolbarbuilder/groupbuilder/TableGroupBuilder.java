package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

public class TableGroupBuilder extends AbstractToolbarGroupBuilder<TableGroupBuilder> {

	public TableGroupBuilder() {
		super("table");
	}

	public TableGroupBuilder table() {
		return option("insertTable");
	}
}