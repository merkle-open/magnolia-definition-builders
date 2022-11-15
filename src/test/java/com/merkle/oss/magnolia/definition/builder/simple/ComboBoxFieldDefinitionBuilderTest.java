package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComboBoxFieldDefinitionBuilderTest extends AbstractComboBoxFieldDefinitionBuilderTest {
	private static final String FIELDNAME = "ComboBoxField";
	private ComboBoxFieldDefinitionBuilder builder;

	@BeforeEach
	public void setup() {
		builder = new ComboBoxFieldDefinitionBuilder<>();
		builder = (ComboBoxFieldDefinitionBuilder)super.setup(builder);
	}

	@Test
	public void testComboBoxFieldDefinitionBuilder() {
		DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		ComboBoxFieldDefinition fieldDefinition = builder.build(FIELDNAME, dataSourceDefinition);
		super.testAbstractComboBoxFieldDefinitionBuilder(fieldDefinition);

		Assertions.assertEquals(FIELDNAME, fieldDefinition.getName());
		Assertions.assertEquals(dataSourceDefinition, fieldDefinition.getDatasource());
	}
}
