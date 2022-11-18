# Magnolia Definition Builders

The Definition Builders module is a builder for Magnolia field definitions. Instead of using YAML, it allows to define 
field definitions in Java,  which is less error-prone than using YAML, especially for big apps.

## Requirements
* Java 11
* Spring >=5
* Magnolia >= 6.0
* Blossom >= 3.2

## Installation

* Add Maven dependency:
```xml
<dependency>
    <groupId>com.namics.oss.magnolia</groupId>
    <artifactId>magnolia-definition-builders</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Examples
The following class shows some examples found on 
[magnolia Docs - Text field](https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Text-field.html)
and [magnolia Docs - Rich text field](https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Rich-text-field.html)

```java
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

```
