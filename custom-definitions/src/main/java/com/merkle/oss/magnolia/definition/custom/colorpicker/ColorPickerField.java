package com.merkle.oss.magnolia.definition.custom.colorpicker;

import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.colorpicker.ColorPickerPopup;

public class ColorPickerField extends CustomField<Integer> {
	private final ColorPicker colorPicker;
	@Nullable
	private final CheckBox noColorCheckbox;

	public ColorPickerField(final ColorPickerFieldDefinition definition, final String noColorCheckboxLabel) {
		if(!definition.isRequired()) {
			noColorCheckbox = new CheckBox();
			noColorCheckbox.setCaption(noColorCheckboxLabel);
		} else {
			noColorCheckbox = null;
		}
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
	protected void doSetValue(@Nullable final Integer color) {
		Optional.ofNullable(color).map(Color::new).ifPresent(colorPicker::setValue);
		Optional.ofNullable(noColorCheckbox).ifPresent(checkbox -> checkbox.setValue(color == null));
	}

	@Override
	protected Component initContent() {
		return new HorizontalLayout(Stream.concat(
				Stream.of(colorPicker),
				Stream.ofNullable(noColorCheckbox)
		).toArray(Component[]::new));
	}

	@Override
	public Integer getValue() {
		if(Optional.ofNullable(noColorCheckbox).map(CheckBox::getValue).orElse(false)) {
			return null;
		}
		return this.colorPicker.getValue().getRGB();
	}
}
