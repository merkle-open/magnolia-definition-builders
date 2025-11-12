package com.merkle.oss.magnolia.definition.custom.contentapp.column.localdate;

import info.magnolia.ui.contentapp.configuration.column.ColumnType;
import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.inject.Inject;
import javax.jcr.Item;
import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.custom.contentapp.column.TimeZoneTooltip;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.merkle.oss.magnolia.powernode.ValueConverter;
import com.vaadin.data.ValueProvider;
import com.vaadin.ui.renderers.LocalDateRenderer;

@ColumnType("localDateColumn")
public class JcrLocalDateColumnDefinition extends ConfiguredColumnDefinition<Item> {
	private String propertyName;

	public JcrLocalDateColumnDefinition() {
		setRenderer(LocalDateRenderer.class);
		setValueProvider(LocalDateValueProvider.class);
		setDescriptionGenerator(TimeZoneTooltip.class);
		setWidth(140d);
	}

	public void setPropertyName(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public static class LocalDateValueProvider implements ValueProvider<Item, LocalDate> {
		private final JcrLocalDateColumnDefinition columnDefinition;
		private final PowerNodeService powerNodeService;

		@Inject
		public LocalDateValueProvider(
				final JcrLocalDateColumnDefinition columnDefinition,
				final PowerNodeService powerNodeService
		) {
			this.columnDefinition = columnDefinition;
			this.powerNodeService = powerNodeService;
		}

		@Override
		public LocalDate apply(final Item item) {
			return Optional
					.of(item)
					.filter(Item::isNode)
					.map(Node.class::cast)
					.map(powerNodeService::convertToPowerNode)
					.flatMap(node ->
							node.getProperty(columnDefinition.getPropertyName(), ValueConverter::getLocalDate)
					)
					.orElse(null);
		}
	}
}
