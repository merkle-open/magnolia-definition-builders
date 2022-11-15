package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.contentapp.FilteringMode;
import info.magnolia.ui.field.AbstractSelectFieldDefinition;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractSelectFieldDefinitionBuilderTest extends AbstractConfiguredFieldDefinitionBuilderTest {

	protected AbstractSelectFieldDefinitionBuilder setup (AbstractSelectFieldDefinitionBuilder builder) {
		builder = (AbstractSelectFieldDefinitionBuilder)super.setup(builder);
		return builder
				.filteringMode(FilteringMode.CONTAINS);
	}

	protected void testAbstractSelectFieldDefinitionBuilder(AbstractSelectFieldDefinition fieldDefinition) {
		super.testAbstractConfiguredFieldDefinitionBuilder(fieldDefinition);
		Assertions.assertEquals(FilteringMode.CONTAINS, fieldDefinition.getFilteringMode());
	}
}
