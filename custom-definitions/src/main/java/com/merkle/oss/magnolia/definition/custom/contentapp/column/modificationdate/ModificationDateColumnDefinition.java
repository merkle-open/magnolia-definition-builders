package com.merkle.oss.magnolia.definition.custom.contentapp.column.modificationdate;

import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.ui.contentapp.configuration.column.ColumnType;

import com.merkle.oss.magnolia.definition.custom.contentapp.column.localdatetime.JcrLocalDateTimeColumnDefinition;

@ColumnType("modificationDateColumn")
public class ModificationDateColumnDefinition extends JcrLocalDateTimeColumnDefinition {
	public ModificationDateColumnDefinition() {
		setName("moddate");
		setPropertyName(NodeTypes.LastModified.NAME);
		setSortable(true);
	}
}