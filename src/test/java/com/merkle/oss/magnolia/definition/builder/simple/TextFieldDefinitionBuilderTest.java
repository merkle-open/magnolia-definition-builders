package com.merkle.oss.magnolia.definition.builder.simple;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.FieldValidatorFactory;
import info.magnolia.ui.field.TextFieldDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextFieldDefinitionBuilderTest {

	@Test
	public void testTextFieldDefinition() {
		TextFieldDefinitionBuilder builder = new TextFieldDefinitionBuilder();
		TextFieldDefinition fieldDefinition = builder
				.maxLength(100)
				.rows(2)
				.readOnly()
				.placeholder("placeholder")
				.defaultValue("default")
				.build("textfield");
		assertEquals(100, fieldDefinition.getMaxLength());
		assertEquals(2, fieldDefinition.getRows());
		assertFalse(fieldDefinition.isI18n());
		assertEquals("placeholder", fieldDefinition.getPlaceholder());
		assertEquals("default", fieldDefinition.getDefaultValue());
		assertEquals("textfield", fieldDefinition.getName());
		assertTrue(fieldDefinition.isReadOnly());
	}

	@Test
	public void testAbstractConfiguredFieldDefinitionBuilder() {
		FieldValidatorDefinition validator = new FakeFieldValidatorDefinition();
		TextFieldDefinitionBuilder builder = new TextFieldDefinitionBuilder();
		TextFieldDefinition fieldDefinition = builder
				.maxLength(100)
				.rows(2)
				.i18n()
				.placeholder("placeholder")
				.defaultValue("default")
				.readOnly(false)
				.label("label")
				.description("description")
				.required()
				.requiredErrorMessage("required error msg")
				.conversionErrorMessage("conversion error msg")
				.styleName("style")
				.converterClass(FakeConverter.class)
				.validator(validator)
				.build("textfield");
		assertEquals(100, fieldDefinition.getMaxLength());
		assertEquals(2, fieldDefinition.getRows());
		assertTrue(fieldDefinition.isI18n());
		assertEquals("placeholder", fieldDefinition.getPlaceholder());
		assertEquals("default", fieldDefinition.getDefaultValue());
		assertEquals("textfield", fieldDefinition.getName());
		assertFalse(fieldDefinition.isReadOnly());
		assertEquals("label", fieldDefinition.getLabel());
		assertEquals("description", fieldDefinition.getDescription());
		assertTrue(fieldDefinition.isRequired());
		assertEquals("required error msg", fieldDefinition.getRequiredErrorMessage());
		assertEquals("conversion error msg", fieldDefinition.getConversionErrorMessage());
		assertEquals("style", fieldDefinition.getStyleName());
		assertEquals(FakeConverter.class, fieldDefinition.getConverterClass());
		assertEquals(validator, fieldDefinition.getValidators().stream().findFirst().get());
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
