package com.merkle.oss.magnolia.definition.custom.separator;

import com.vaadin.data.BinderValidationStatus;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import info.magnolia.ui.editor.EditorView;

import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;

public class SeparatorField extends Label implements EditorView<Object> {
	private static final String VALUE = """
			<hr style='margin-top: 30px;
			border: 0;
			height: 0;
			border-top: 1px solid rgba(0, 0, 0, 0.1);
			border-bottom: 1px solid rgba(255, 255, 255, 0.3);'>""";

	@Inject
	public SeparatorField() {
		super(VALUE, ContentMode.HTML);
		setWidth("100%");
	}

	@Override
	public List<BinderValidationStatus<?>> validate() {
		return Collections.emptyList();
	}

	@Override
	public void populate(final Object item) {
	}

	@Override
	public void write(final Object item) {
	}
}
