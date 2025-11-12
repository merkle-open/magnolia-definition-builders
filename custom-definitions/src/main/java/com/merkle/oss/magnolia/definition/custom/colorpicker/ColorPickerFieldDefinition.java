package com.merkle.oss.magnolia.definition.custom.colorpicker;

import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.FieldType;

import jakarta.annotation.Nullable;

@FieldType("colorPickerField")
public class ColorPickerFieldDefinition extends ConfiguredFieldDefinition<Integer> {
	private boolean rgb = false;
	private boolean hsvv = false;
	private boolean swatches = true;
	private boolean history = false;
	private boolean textField = true;
	private String noColorCheckboxLabelKey;

	public ColorPickerFieldDefinition() {
		setType(Integer.class);
		setFactoryClass(ColorPickerFieldFactory.class);
	}

	public boolean isRgb() {
		return rgb;
	}

	public void setRgb(final boolean rgb) {
		this.rgb = rgb;
	}

	public boolean isHsvv() {
		return hsvv;
	}

	public void setHsvv(final boolean hsvv) {
		this.hsvv = hsvv;
	}

	public boolean isSwatches() {
		return swatches;
	}

	public void setSwatches(final boolean swatches) {
		this.swatches = swatches;
	}

	public boolean isHistory() {
		return history;
	}

	public void setHistory(final boolean history) {
		this.history = history;
	}

	public boolean isTextField() {
		return textField;
	}

	public void setTextField(final boolean textField) {
		this.textField = textField;
	}

	@Nullable
	public String getNoColorCheckboxLabelKey() {
		return noColorCheckboxLabelKey;
	}

	public void setNoColorCheckboxLabelKey(@Nullable final String disableCheckboxLabelKey) {
		this.noColorCheckboxLabelKey = disableCheckboxLabelKey;
	}
}
