package com.merkle.oss.magnolia.definition.builder;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.complex.AbstractMultiFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.*;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.ComboBox;
import info.magnolia.ui.contentapp.FilteringMode;
import info.magnolia.ui.editor.ItemPreviewDefinition;
import info.magnolia.ui.editor.ItemProviderDefinition;
import info.magnolia.ui.editor.MultiFormDefinition;
import info.magnolia.ui.editor.MultiFormView;
import info.magnolia.ui.field.*;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public abstract class AbstractFieldDefinitionBuilderTestCase {

	public <T, D extends MultiFieldDefinition<T>, B extends AbstractMultiFieldDefinitionBuilder<T, D, B>> B assertMultiField(final B builder, final BiFunction<String, B, D> buildFunction) {
		final MultiFormView.EntryResolution.Definition<T> entryResolution = mock(MultiFormView.EntryResolution.Definition.class);
		final MultiFormDefinition.OrderHandlerDefinition<T> orderHandler = mock(MultiFormDefinition.OrderHandlerDefinition.class);

		final D definition = buildFunction.apply(
				"someName",
				assertComplexField(builder, buildFunction)
						.entryResolution(entryResolution)
						.orderHandler(orderHandler)
						.canRemoveItems(true)
						.buttonSelectAddLabel("someSelectAddLabel")
						.buttonSelectRemoveLabel("someSelectRemoveLabel")
						.required(true)
						.requiredErrorMessage("someRequiredErrorMessage")
						.itemCountErrorMessage("someItemCountErrorMessage")
						.minItems(10)
						.maxItems(42)
		);

		assertEquals(entryResolution, definition.getEntryResolution());
		assertEquals(orderHandler, definition.getOrderHandler());
		assertTrue(definition.isCanRemoveItems());
		assertEquals("someSelectAddLabel", definition.getButtonSelectAddLabel());
		assertEquals("someSelectRemoveLabel", definition.getButtonSelectRemoveLabel());
		assertTrue(definition.isRequired());
		assertEquals("someRequiredErrorMessage", definition.getRequiredErrorMessage());
		assertEquals("someItemCountErrorMessage", definition.getItemCountErrorMessage());
		assertEquals(10, definition.getMinItems());
		assertEquals(42, definition.getMaxItems());
		return builder;
	}

	public <T, D extends ConfiguredComplexPropertyDefinition<T>, B extends AbstractConfiguredComplexPropertyDefinitionBuilder<T, D, B>> B assertComplexField(final B builder, final BiFunction<String, B, D> buildFunction) {
		final ItemProviderDefinition<T, T> itemProvider = mock(ItemProviderDefinition.class);
		final ConfiguredComplexPropertyDefinition<T> definition = buildFunction.apply(
				"someName",
				builder
						.label("someLable")
						.description("someDescription")
						.i18n(true)
						.styleName("someStyleName")
						.itemProvider(itemProvider)
		);
		assertEquals("someLable", definition.getLabel());
		assertEquals("someDescription", definition.getDescription());
		assertTrue(definition.isI18n());
		assertEquals("someStyleName", definition.getStyleName());
		assertEquals(itemProvider, definition.getItemProvider());
		return builder;
	}

	public <T, D extends AbstractOptionGroupFieldDefinition<T>, B extends AbstractOptionGroupFieldDefinitionBuilder<T, D, B>> B assertOptionGroupField(final B builder, final BiFunction<String, B, D> buildFunction) {
		final D definition = buildFunction.apply(
				"someName",
				assertSelectField(builder, buildFunction)
						.layout(Layout.horizontal)
		);

		assertEquals(Layout.horizontal, definition.getLayout());
		return builder;
	}

	public <T, D extends LinkFieldDefinition<T>, B extends AbstractLinkFieldDefinitionBuilder<T, D, B>> B assertLinkField(final B builder, final BiFunction<String, B, D> buildFunction) {
		final ItemPreviewDefinition<T> itemPreviewDefinition = new ItemPreviewDefinition<>();
		final D definition = buildFunction.apply(
				"someName",
				assertComboBoxField(builder, buildFunction)
						.chooserId("chooserId")
						.buttonSelectNewLabel("buttonSelectNewLabel")
						.buttonSelectOtherLabel("buttonSelectOtherLabel")
						.preview(itemPreviewDefinition)
						.editable()
						.showOptions()
		);
		assertEquals("chooserId", definition.getChooserId());
		assertEquals("buttonSelectNewLabel", definition.getButtonSelectNewLabel());
		assertEquals("buttonSelectOtherLabel", definition.getButtonSelectOtherLabel());
		assertEquals(itemPreviewDefinition, definition.getPreview());
		assertTrue(definition.isEditable());
		assertTrue(definition.isShowOptions());
		return builder;
	}

	public <T, D extends ComboBoxFieldDefinition<T>, B extends AbstractComboBoxFieldDefinitionBuilder<T, D, B>> B assertComboBoxField(final B builder, final BiFunction<String, B, D> buildFunction) {
		final Class<? extends ComboBox.NewItemProvider<T>> newItemProviderClass = newItemProviderClass();

		final D definition = buildFunction.apply(
				"someName",
				assertField(builder, buildFunction, null)
						.textInputAllowed()
						.pageLength(10)
						.emptySelectionCaption("emptySelectionCaption")
						.emptySelectionAllowed()
						.scrollToSelectedItem()
						.popWidth("100")
						.placeholder("placeholder")
						.newItemProviderClass(newItemProviderClass)
		);
		assertTrue(definition.isTextInputAllowed());
		assertEquals(10, definition.getPageLength());
		assertEquals("emptySelectionCaption", definition.getEmptySelectionCaption());
		assertTrue(definition.isEmptySelectionAllowed());
		assertTrue(definition.isScrollToSelectedItem());
		assertEquals("100", definition.getPopWidth());
		assertEquals("placeholder", definition.getPlaceholder());
		assertEquals(newItemProviderClass, definition.getNewItemProviderClass());
		return builder;
	}

	private <T> Class<? extends ComboBox.NewItemProvider<T>> newItemProviderClass() {
		return new ComboBox.NewItemProvider<T>() {
			@Override
			public Optional<T> apply(String s) {
				return Optional.empty();
			}
		}.getClass();
	}


	public <T, D extends AbstractSelectFieldDefinition<T, ?>, B extends AbstractSelectFieldDefinitionBuilder<T, ?, D, B>> B assertSelectField(final B builder, final BiFunction<String, B, D> buildFunction) {
		final D definition = buildFunction.apply(
				"someName",
				assertField(builder, buildFunction, null)
						.filteringMode(FilteringMode.CONTAINS)
		);
		assertEquals(FilteringMode.CONTAINS, definition.getFilteringMode());
		return builder;
	}

	public <D extends ConfiguredFieldDefinition<String>, B extends AbstractConfiguredFieldDefinitionBuilder<String, D, B>> B assertField(final B builder, final BiFunction<String, B, D> buildFunction) {
		return assertField(builder, buildFunction, "42");
	}

	public <T, D extends ConfiguredFieldDefinition<T>, B extends AbstractConfiguredFieldDefinitionBuilder<T, D, B>> B assertField(final B builder, final BiFunction<String, B, D> buildFunction, final T defaultValue) {
		final FieldValidatorDefinition validator1 = mock(FieldValidatorDefinition.class);
		final FieldValidatorDefinition validator2 = mock(FieldValidatorDefinition.class);
		final Class<? extends Converter<T, Object>> converterClass = converterClass();

		final D definition = buildFunction.apply(
				"someName",
				builder
						.label("someLable")
						.i18n(true)
						.description("someDescription")
						.required(true)
						.requiredErrorMessage("someRequiredErrorMessage")
						.conversionErrorMessage("someConversionErrorMessage")
						.readOnly(true)
						.defaultValue(defaultValue)
						.styleName("someStyleName")
						.validator(validator1)
						.validator(validator2)
						.converterClass(converterClass)
		);
		assertEquals("someName", definition.getName());
		assertEquals("someLable", definition.getLabel());
		assertTrue(definition.isI18n());
		assertEquals("someDescription", definition.getDescription());
		assertTrue(definition.isRequired());
		assertEquals("someRequiredErrorMessage", definition.getRequiredErrorMessage());
		assertEquals("someConversionErrorMessage", definition.getConversionErrorMessage());
		assertTrue(definition.isReadOnly());
		assertEquals(defaultValue, definition.getDefaultValue());
		assertEquals("someStyleName", definition.getStyleName());
		assertTrue(definition.getValidators().containsAll(List.of(validator1, validator2)));
		assertEquals(converterClass, definition.getConverterClass());
		return builder;
	}

	private <T> Class<? extends Converter<T, Object>> converterClass() {
		return new Converter<T, Object>() {
			@Override
			public Result<Object> convertToModel(T value, ValueContext context) {
				return null;
			}

			@Override
			public T convertToPresentation(Object value, ValueContext context) {
				return null;
			}
		}.getClass();
	}
}
