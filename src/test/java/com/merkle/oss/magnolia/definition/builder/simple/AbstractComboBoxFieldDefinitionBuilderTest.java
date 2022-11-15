package com.merkle.oss.magnolia.definition.builder.simple;

import com.vaadin.ui.ComboBox;
import info.magnolia.ui.field.ComboBoxFieldDefinition;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractComboBoxFieldDefinitionBuilderTest extends AbstractSelectFieldDefinitionBuilderTest {

	private final static int PAGE_LENGTH = 42;
	private static final String POP_WIDTH = "300px";
	private static final String PLACEHOLDER = "placeholder string";
	private static final String EMPTY_SELECTION_CAPTION = "empty selection caption";

	protected AbstractComboBoxFieldDefinitionBuilder setup(AbstractComboBoxFieldDefinitionBuilder builder) {
		builder = (AbstractComboBoxFieldDefinitionBuilder) super.setup(builder);
		return builder
				.textInputAllowed()
				.emptySelectionAllowed()
				.scrollToSelectedItem()
				.pageLength(PAGE_LENGTH)
				.popWidth(POP_WIDTH)
				.placeholder(PLACEHOLDER)
				.emptySelectionCaption(EMPTY_SELECTION_CAPTION)
				.newItemProviderClass(TestItemProvider.class);
	}

	protected void testAbstractComboBoxFieldDefinitionBuilder(ComboBoxFieldDefinition fieldDefinition) {
		super.testAbstractSelectFieldDefinitionBuilder(fieldDefinition);
		assertTrue(fieldDefinition.isTextInputAllowed());
		assertTrue(fieldDefinition.isEmptySelectionAllowed());
		assertTrue(fieldDefinition.isScrollToSelectedItem());
		assertEquals(PAGE_LENGTH, fieldDefinition.getPageLength());
		assertEquals(POP_WIDTH, fieldDefinition.getPopWidth());
		assertEquals(PLACEHOLDER, fieldDefinition.getPlaceholder());
		assertEquals(EMPTY_SELECTION_CAPTION, fieldDefinition.getEmptySelectionCaption());
		assertEquals(fieldDefinition.getNewItemProviderClass(), TestItemProvider.class);
	}

	private static class TestItemProvider implements ComboBox.NewItemProvider<String> {
		@Override
		public Optional<String> apply(String s) {
			return Optional.empty();
		}

		@Override
		public <V> Function<V, Optional<String>> compose(Function<? super V, ? extends String> before) {
			return ComboBox.NewItemProvider.super.compose(before);
		}

		@Override
		public <V> Function<String, V> andThen(Function<? super Optional<String>, ? extends V> after) {
			return ComboBox.NewItemProvider.super.andThen(after);
		}
	}
}
