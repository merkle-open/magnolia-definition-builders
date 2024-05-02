package com.merkle.oss.magnolia.definition.custom.contentapp.column.modificationdate;

import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.ui.contentapp.configuration.column.ColumnType;
import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;
import info.magnolia.ui.contentapp.configuration.column.renderer.DefaultDateRenderer;
import info.magnolia.ui.contentapp.configuration.column.renderer.TimeZoneTooltip;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.jcr.Item;
import javax.jcr.Node;

import com.vaadin.data.ValueProvider;

@ColumnType("modificationDateColumn")
public class ModificationDateColumnDefinition extends ConfiguredColumnDefinition<Item> {

	public ModificationDateColumnDefinition() {
		setRenderer(DefaultDateRenderer.class);
		setDescriptionGenerator((Class) TimeZoneTooltip.class);
		setName("moddate");
		setWidth(160d);
		setSortable(true);
		setValueProvider(ModificationDateValueProvider.class);
	}

	public static class ModificationDateValueProvider implements ValueProvider<Item, Date> {
		@Override
		public Date apply(final Item node) {
			try {
				return Optional
						.ofNullable(NodeTypes.LastModified.getLastModified((Node) node))
						.map(Calendar::getTime)
						.orElse(null);
			} catch (Exception e) {
				return null;
			}
		}
	}
}