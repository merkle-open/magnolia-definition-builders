package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.DateFieldDefinition;

import java.util.Optional;

import jakarta.annotation.Nullable;

import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.shared.ui.datefield.DateTimeResolution;

/**
 * builds a {@link DateFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Date-field.html">magnolia Docs - Date field </a>
 * @author Merkle DACH
 */
public class DateFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<Object, DateFieldDefinition, DateFieldDefinitionBuilder> {
	@Nullable
	private Boolean time;
	@Nullable
	private Boolean inISO8061Format;
	@Nullable
	private String dateFormat;
	@Nullable
	private String timeFormat;
	@Nullable
	private String resolution;

	public DateFieldDefinitionBuilder() {}
	public DateFieldDefinitionBuilder(final DateFieldDefinition definition) {
		super(definition);
		time(definition.isTime());
		inISO8061Format(definition.isInISO8061Format());
		dateFormat(definition.getDateFormat());
		timeFormat(definition.getTimeFormat());
		resolution(definition.getResolution());
	}

	public DateFieldDefinitionBuilder time(final boolean time) {
		this.time = time;
		return self();
	}

	public DateFieldDefinitionBuilder inISO8061Format(final boolean inISO8061Format) {
		this.inISO8061Format = inISO8061Format;
		return self();
	}

	public DateFieldDefinitionBuilder dateFormat(final String dateFormat) {
		this.dateFormat = dateFormat;
		return self();
	}

	public DateFieldDefinitionBuilder timeFormat(final String timeFormat) {
		this.timeFormat = timeFormat;
		return self();
	}

	private DateFieldDefinitionBuilder resolution(final String resolution) {
		this.resolution = resolution;
		return self();
	}

	public DateFieldDefinitionBuilder resolution(final DateTimeResolution resolution) {
		return resolution(resolution.name());
	}

	public DateFieldDefinitionBuilder resolution(final DateResolution resolution) {
		return resolution(resolution.name());
	}

	public DateFieldDefinition build(final String name) {
		final DateFieldDefinition definition = new DateFieldDefinition();
		super.populate(definition, name);
		Optional.ofNullable(time).ifPresent(definition::setTime);
		Optional.ofNullable(inISO8061Format).ifPresent(definition::setInISO8061Format);
		Optional.ofNullable(dateFormat).ifPresent(definition::setDateFormat);
		Optional.ofNullable(timeFormat).ifPresent(definition::setTimeFormat);
		Optional.ofNullable(resolution).ifPresent(definition::setResolution);
		return definition;
	}
}
