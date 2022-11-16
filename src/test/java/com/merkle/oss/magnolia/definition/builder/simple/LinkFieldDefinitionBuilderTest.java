package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkFieldDefinitionBuilderTest extends AbstractLinkFieldDefinitionBuilderTest {
	private static final String FIELDNAME = "LinkField";
	private LinkFieldDefinitionBuilder builder;

	@BeforeEach
	public void setup() {
		builder = new LinkFieldDefinitionBuilder<>();
		builder = (LinkFieldDefinitionBuilder)super.setup(builder);
	}

	@Test
	public void testLinkFieldDefinitionBuilder() {
		DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		LinkFieldDefinition fieldDefinition = builder.build(FIELDNAME, dataSourceDefinition);

		super.testAbstractLinkFieldDefinitionBuilder(fieldDefinition);
		super.testBooleanValues(fieldDefinition, true, true);
	}

	@Test
	public void testLinkFieldDefinitionBuilderBooleanTrue() {
		DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		super.setupBooleanValues(builder, true, true);
		LinkFieldDefinition fieldDefinition = builder.build(FIELDNAME, dataSourceDefinition);

		super.testAbstractLinkFieldDefinitionBuilder(fieldDefinition);
		super.testBooleanValues(fieldDefinition, true, true);
	}
	@Test
	public void testLinkFieldDefinitionBuilderBooleanFalse() {
		DatasourceDefinition dataSourceDefinition = new BaseDatasourceDefinition();
		super.setupBooleanValues(builder, false, false);
		LinkFieldDefinition fieldDefinition = builder.build(FIELDNAME, dataSourceDefinition);

		super.testAbstractLinkFieldDefinitionBuilder(fieldDefinition);
		super.testBooleanValues(fieldDefinition, false, false);
	}
}
