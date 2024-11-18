package com.merkle.oss.magnolia.definition.custom.colorpicker;

import com.merkle.oss.magnolia.definition.builder.simple.AbstractConfiguredFieldDefinitionBuilder;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * builds a {@link ColorPickerFieldDefinition}
 * @see <a href="https://vaadin.com/api/framework/current/com/vaadin/ui/ColorPicker.html">vaadin Docs - ColorPicker</a>
 * @author Merkle DACH
 */
public class ColorPickerFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<Integer, ColorPickerFieldDefinition, ColorPickerFieldDefinitionBuilder> {
	@Nullable
	private Boolean rgb;
	@Nullable
	private Boolean hsvv;
	@Nullable
	private Boolean swatches;
	@Nullable
	private Boolean history;
	@Nullable
	private Boolean textField;

	public ColorPickerFieldDefinitionBuilder() {}
	public ColorPickerFieldDefinitionBuilder(final ColorPickerFieldDefinition definition) {
		super(definition);
		rgb(definition.isRgb());
		hsvv(definition.isHsvv());
		swatches(definition.isSwatches());
		history(definition.isHistory());
		textField(definition.isTextField());
	}

	public ColorPickerFieldDefinitionBuilder rgb(final boolean rgb) {
		this.rgb = rgb;
		return self();
	}

	public ColorPickerFieldDefinitionBuilder rgb() {
		return rgb(true);
	}

	public ColorPickerFieldDefinitionBuilder hsvv(final boolean hsvv) {
		this.hsvv = hsvv;
		return self();
	}

	public ColorPickerFieldDefinitionBuilder hsvv() {
		return hsvv(true);
	}

	public ColorPickerFieldDefinitionBuilder swatches(final boolean swatches) {
		this.swatches = swatches;
		return self();
	}

	public ColorPickerFieldDefinitionBuilder swatches() {
		return swatches(true);
	}

	public ColorPickerFieldDefinitionBuilder history(final boolean history) {
		this.history = history;
		return self();
	}

	public ColorPickerFieldDefinitionBuilder history() {
		return history(true);
	}

	public ColorPickerFieldDefinitionBuilder textField(final boolean textField) {
		this.textField = textField;
		return self();
	}

	public ColorPickerFieldDefinitionBuilder textField() {
		return textField(true);
	}

	public ColorPickerFieldDefinition build(final String name) {
		final ColorPickerFieldDefinition definition = new ColorPickerFieldDefinition();
		super.populate(definition, name);
		Optional.ofNullable(rgb).ifPresent(definition::setRgb);
		Optional.ofNullable(hsvv).ifPresent(definition::setHsvv);
		Optional.ofNullable(swatches).ifPresent(definition::setSwatches);
		Optional.ofNullable(history).ifPresent(definition::setHistory);
		Optional.ofNullable(textField).ifPresent(definition::setTextField);
		return definition;
	}
}
