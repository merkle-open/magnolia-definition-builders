package com.merkle.oss.magnolia.definition.builder;

import com.merkle.oss.magnolia.definition.builder.simple.RichTextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import info.magnolia.ui.field.RichTextFieldDefinition;
import info.magnolia.ui.field.TextFieldDefinition;

public class BuilderExample {

	public TextFieldDefinition textFieldDefinitionExample() {
		return new TextFieldDefinitionBuilder()
				.label("Body")
				.rows(5)
				.maxLength(300)
				.build("text");
	}

	public RichTextFieldDefinition richTextFieldDefinitionExample() {
		return new RichTextFieldDefinitionBuilder()
				.label("Text editor")
				.height(500)
				.tables(true)
				.source(true)
				.build("richText");
	}
}
