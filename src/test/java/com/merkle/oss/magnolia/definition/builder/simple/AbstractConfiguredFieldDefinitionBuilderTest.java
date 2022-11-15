package com.merkle.oss.magnolia.definition.builder.simple;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import info.magnolia.ui.field.ConfiguredFieldDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractConfiguredFieldDefinitionBuilderTest {

	private FieldValidatorDefinition validator;

	protected AbstractConfiguredFieldDefinitionBuilder setup(AbstractConfiguredFieldDefinitionBuilder builder) {
		validator = new FakeFieldValidatorDefinition();
		return builder
				.i18n()
				.defaultValue("default")
				.readOnly()
				.label("label")
				.description("description")
				.required()
				.requiredErrorMessage("required error msg")
				.conversionErrorMessage("conversion error msg")
				.styleName("style")
				.converterClass(FakeConverter.class)
				.validator(validator);
	}

	protected AbstractConfiguredFieldDefinitionBuilder setupBooleanValues(AbstractConfiguredFieldDefinitionBuilder builder, boolean i18n, boolean readOnly, boolean required) {
		return builder
				.i18n(i18n)
				.readOnly(readOnly)
				.required(required);
	}

	protected void testAbstractConfiguredFieldDefinitionBuilder(ConfiguredFieldDefinition fieldDefinition) {
		assertTrue(fieldDefinition.isI18n());
		assertEquals("default", fieldDefinition.getDefaultValue());
		assertEquals("textfield", fieldDefinition.getName());
		assertTrue(fieldDefinition.isReadOnly());
		assertEquals("label", fieldDefinition.getLabel());
		assertEquals("description", fieldDefinition.getDescription());
		assertTrue(fieldDefinition.isRequired());
		assertEquals("required error msg", fieldDefinition.getRequiredErrorMessage());
		assertEquals("conversion error msg", fieldDefinition.getConversionErrorMessage());
		assertEquals("style", fieldDefinition.getStyleName());
		assertEquals(FakeConverter.class, fieldDefinition.getConverterClass());
		assertEquals(validator, fieldDefinition.getValidators().stream().findFirst().get());
	}

	protected void testBooleanValues(ConfiguredFieldDefinition fieldDefinition, boolean i18n, boolean readOnly, boolean required) {
		assertEquals(i18n, fieldDefinition.isI18n());
		assertEquals(readOnly, fieldDefinition.isReadOnly());
		assertEquals(required, fieldDefinition.isRequired());
	}

	private static class FakeFieldValidatorDefinition implements FieldValidatorDefinition {
		@Override
		public String getErrorMessage() {
			return null;
		}

		@Override
		public Class<? extends FieldValidatorFactory> getFactoryClass() {
			return null;
		}
	}

	private static class FakeConverter implements Converter<String, String> {
		@Override
		public Result<String> convertToModel(String m, ValueContext valueContext) {
			return null;
		}

		@Override
		public String convertToPresentation(String t, ValueContext valueContext) {
			return null;
		}
	}
}
