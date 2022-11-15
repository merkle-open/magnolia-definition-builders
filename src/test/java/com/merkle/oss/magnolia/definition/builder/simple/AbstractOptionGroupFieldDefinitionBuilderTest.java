package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.AbstractOptionGroupFieldDefinition;
import info.magnolia.ui.field.Layout;
import org.junit.jupiter.api.Assertions;

public class AbstractOptionGroupFieldDefinitionBuilderTest extends AbstractSelectFieldDefinitionBuilderTest{

	protected AbstractOptionGroupFieldDefinitionBuilder setup(AbstractOptionGroupFieldDefinitionBuilder builder) {
		builder = (AbstractOptionGroupFieldDefinitionBuilder) super.setup(builder);
		return builder
				.layout(Layout.horizontal);
	}

	public void testAbstractOptionGroupFieldDefinitionBuilder(AbstractOptionGroupFieldDefinition fieldDefinition) {
		super.testAbstractSelectFieldDefinitionBuilder(fieldDefinition);
		Assertions.assertEquals(Layout.horizontal, fieldDefinition.getLayout());
	}
}
