package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.RadioButtonGroupFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RadioButtonGroupFieldDefinitionBuilderTest extends AbstractOptionGroupFieldDefinitionBuilderTest {

	private static final String FIELDNAME = "ComboBoxField";
	private RadioButtonGroupFieldDefinitionBuilder builder;

	@BeforeEach
	public void setup() {
		builder = new RadioButtonGroupFieldDefinitionBuilder<>();
		builder = (RadioButtonGroupFieldDefinitionBuilder)super.setup(builder);
	}

	@Test
	public void testRadioButtonGroupFieldDefinitionBuilder() {
		DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		RadioButtonGroupFieldDefinition fieldDefinition = builder.build(FIELDNAME, dataSourceDefinition);

		super.testAbstractOptionGroupFieldDefinitionBuilder(fieldDefinition);

	}
}
