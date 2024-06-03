package com.merkle.oss.magnolia.definition.custom.contentapp.column.localdatetime;

import info.magnolia.ui.contentapp.configuration.column.ColumnType;
import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.inject.Inject;
import javax.jcr.Item;
import javax.jcr.Node;

import com.merkle.oss.magnolia.definition.custom.contentapp.column.TimeZoneTooltip;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.merkle.oss.magnolia.powernode.ValueConverter;
import com.vaadin.data.ValueProvider;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;

@ColumnType("localDateTimeColumn")
public class JcrLocalDateTimeColumnDefinition extends ConfiguredColumnDefinition<Item> {
	private String propertyName;

	public JcrLocalDateTimeColumnDefinition() {
		setRenderer(LocalDateTimeRenderer.class);
		setValueProvider(LocalDateTimeValueProvider.class);
		setDescriptionGenerator(TimeZoneTooltip.class);
		setWidth(180d);
	}

	public void setPropertyName(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public static class LocalDateTimeValueProvider implements ValueProvider<Item, LocalDateTime> {
		private final JcrLocalDateTimeColumnDefinition columnDefinition;
		private final PowerNodeService powerNodeService;

		@Inject
		public LocalDateTimeValueProvider(
				final JcrLocalDateTimeColumnDefinition columnDefinition,
				final PowerNodeService powerNodeService
		) {
			this.columnDefinition = columnDefinition;
			this.powerNodeService = powerNodeService;
		}

		@Override
		public LocalDateTime apply(final Item item) {
			return Optional
					.of(item)
					.filter(Item::isNode)
					.map(Node.class::cast)
					.map(powerNodeService::convertToPowerNode)
					.flatMap(node ->
							node.getProperty(columnDefinition.getPropertyName(), ValueConverter::getLocalDateTime)
					)
					.orElse(null);
		}
	}

}
