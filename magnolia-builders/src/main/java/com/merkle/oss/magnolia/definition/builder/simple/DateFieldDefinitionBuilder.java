package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.DateFieldDefinition;
import info.magnolia.ui.field.TextFieldDefinition;

import javax.annotation.Nullable;
import java.util.Optional;

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

	public DateFieldDefinitionBuilder() {}
	public DateFieldDefinitionBuilder(final DateFieldDefinition definition) {
		super(definition);
		time(definition.isTime());
		inISO8061Format(definition.isInISO8061Format());
		dateFormat(definition.getDateFormat());
		timeFormat(definition.getTimeFormat());
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

	public DateFieldDefinition build(final String name) {
		final DateFieldDefinition definition = new DateFieldDefinition();
		super.populate(definition, name);
		Optional.ofNullable(time).ifPresent(definition::setTime);
		Optional.ofNullable(inISO8061Format).ifPresent(definition::setInISO8061Format);
		Optional.ofNullable(dateFormat).ifPresent(definition::setDateFormat);
		Optional.ofNullable(timeFormat).ifPresent(definition::setTimeFormat);
		return definition;
	}
}
