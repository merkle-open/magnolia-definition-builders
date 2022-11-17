package com.merkle.oss.magnolia.definition.builder.complex;

import info.magnolia.ui.editor.MultiFormDefinition;
import info.magnolia.ui.editor.MultiFormView;
import info.magnolia.ui.field.MultiFieldDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AbstractMultiFieldDefinitionBuilderTest {

	private static final String ADD_LABEL = "addLabel";
	private static final String REQUIRED_ERROR_MESSAGE = "requiredErrorMessage";
	private static final String ITEM_COUNT_ERROR_MESSAGE = "itemCountErrorMessage";
	private static final int MIN_ITEMS = 1;
	private static final int MAX_ITEMS = 99;
	private static final String REMOVE_LABEL = "removeLabel";

	private MultiFormView.EntryResolution.Definition<String> entryResolution;
	MultiFormDefinition.OrderHandlerDefinition<String> orderHandler;


	protected AbstractMultiFieldDefinitionBuilder setup(AbstractMultiFieldDefinitionBuilder builder) {
		entryResolution = new MultiFormView.EntryResolution.Definition<>();
		orderHandler = new MultiFormDefinition.OrderHandlerDefinition<>();
		return builder
				.buttonSelectAddLabel(ADD_LABEL)
				.buttonSelectRemoveLabel(REMOVE_LABEL)
				.requiredErrorMessage(REQUIRED_ERROR_MESSAGE)
				.itemCountErrorMessage(ITEM_COUNT_ERROR_MESSAGE)
				.minItems(MIN_ITEMS)
				.maxItems(MAX_ITEMS)
				.entryResolution(entryResolution)
				.orderHandler(orderHandler)
				.canRemoveItems()
				.required();
	}

	protected void testAbstractMultiFieldDefinitionBuilder(MultiFieldDefinition fieldDefinition) {
		assertEquals(ADD_LABEL, fieldDefinition.getButtonSelectAddLabel());
		assertEquals(REMOVE_LABEL, fieldDefinition.getButtonSelectRemoveLabel());
		assertEquals(REQUIRED_ERROR_MESSAGE, fieldDefinition.getRequiredErrorMessage());
		assertEquals(ITEM_COUNT_ERROR_MESSAGE, fieldDefinition.getItemCountErrorMessage());
		assertEquals(MIN_ITEMS, fieldDefinition.getMinItems());
		assertEquals(MAX_ITEMS, fieldDefinition.getMaxItems());
		assertEquals(entryResolution, fieldDefinition.getEntryResolution());
		assertEquals(orderHandler, fieldDefinition.getOrderHandler());
		testBoolean(fieldDefinition, true, true);
	}

	protected AbstractMultiFieldDefinitionBuilder setupBoolean(AbstractMultiFieldDefinitionBuilder builder, boolean required, boolean canRemoveItems) {
		return builder
				.canRemoveItems(canRemoveItems)
				.required(required);
	}
	protected void testBoolean(MultiFieldDefinition fieldDefinition, boolean required, boolean canRemoveItems) {
		assertEquals(required, fieldDefinition.isRequired());
		assertEquals(canRemoveItems, fieldDefinition.isCanRemoveItems() );
	}

}
