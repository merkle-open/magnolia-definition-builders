package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.LinkFieldDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractLinkFieldDefinitionBuilderTest extends AbstractComboBoxFieldDefinitionBuilderTest {

	private static final String CHOOSER_ID = "chooserId";
	private static final String SELECT_NEW_LABEL = "Select New";
	private static final String SELECT_OTHER_LABEL = "Select other";

	protected AbstractLinkFieldDefinitionBuilder setup(AbstractLinkFieldDefinitionBuilder builder) {
		builder = (AbstractLinkFieldDefinitionBuilder) super.setup(builder);
		return builder
				.chooserId(CHOOSER_ID)
				.buttonSelectNewLabel(SELECT_NEW_LABEL)
				.buttonSelectOtherLabel(SELECT_OTHER_LABEL)
				.editable()
				.showOptions();
	}

	protected void testAbstractLinkFieldDefinitionBuilder(LinkFieldDefinition fieldDefinition) {
		assertEquals(CHOOSER_ID, fieldDefinition.getChooserId());
		assertEquals(SELECT_NEW_LABEL, fieldDefinition.getButtonSelectNewLabel());
		assertEquals(SELECT_OTHER_LABEL, fieldDefinition.getButtonSelectOtherLabel());
	}

	protected AbstractLinkFieldDefinitionBuilder setupBooleanValues(AbstractLinkFieldDefinitionBuilder builder, boolean editable, boolean showOptions) {
		return builder
				.editable(editable)
				.showOptions(showOptions);
	}

	protected void testBooleanValues(LinkFieldDefinition fieldDefinition, boolean editable, boolean showOptions) {
		assertEquals(editable, fieldDefinition.isEditable());
		assertEquals(showOptions, fieldDefinition.isShowOptions());
	}

}
