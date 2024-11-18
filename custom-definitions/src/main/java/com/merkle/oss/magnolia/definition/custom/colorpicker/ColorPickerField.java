package com.merkle.oss.magnolia.definition.custom.colorpicker;

import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.colorpicker.ColorPickerPopup;

public class ColorPickerField extends CustomField<Integer> {
	private final ColorPicker colorPicker;

	public ColorPickerField(final ColorPickerFieldDefinition definition) {
		colorPicker = new ColorPicker() {
			@Override
			protected void showPopup(final boolean open) {
				super.showPopup(open);
				if(open) {
					getUI().getWindows().stream().filter(ColorPickerPopup.class::isInstance).forEach(this::addPopupStyles);
				}
			}

			private void addPopupStyles(final Window window) {
				window.addStyleName("dialog");
				window.addStyleName("v-window-dialog");
				window.addStyleName("framed");
				window.addStyleName("v-window-framed");
				window.addStyleName("small");
			}
		};
		colorPicker.setModal(true);
		colorPicker.setHSVVisibility(definition.isHsvv());
		colorPicker.setTextfieldVisibility(definition.isTextField());
		colorPicker.setRGBVisibility(definition.isRgb());
		colorPicker.setSwatchesVisibility(definition.isSwatches());
		colorPicker.setHistoryVisibility(definition.isHistory());
		colorPicker.addValueChangeListener(event -> setValue(event.getValue().getRGB()));
	}

	@Override
	protected void doSetValue(final Integer color) {
		if(color != null) {
			colorPicker.setValue(new Color(color));
		}
	}

	@Override
	protected Component initContent() {
		return colorPicker;
	}

	@Override
	public Integer getValue() {
		return this.colorPicker.getValue().getRGB();
	}
}
