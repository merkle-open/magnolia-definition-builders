# Magnolia Definition Builders

The Definition Builders module is a builder for Magnolia field definitions. Instead of using YAML, it allows to define 
field definitions in Java,  which is less error-prone than using YAML, especially for big apps.

## Requirements
* Java 11
* Magnolia >= 6.0

## Installation

* Add Maven dependency:
```xml
<dependency>
    <groupId>com.namics.oss.magnolia</groupId>
    <artifactId>magnolia-definition-builders</artifactId>
    <version>1.0.9</version>
</dependency>
```

## Examples
The following class shows some example usages:

```java
package com.merkle.oss.magnolia.definition.builder;

import com.merkle.oss.magnolia.definition.builder.complex.CompositeFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.complex.ConfiguredSwitchableFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.complex.JcrMultiFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.datasource.OptionBuilder;
import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.ComboBoxFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.RichTextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.JcrChildNodeProviderDefinition;
import info.magnolia.ui.field.*;

import javax.jcr.Node;
import java.util.List;

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

	public JcrMultiFieldDefinition multiFieldDefinitionExample() {
		return new JcrMultiFieldDefinitionBuilder()
				.itemProvider(new JcrChildNodeProviderDefinition())
				.build("multi", new CompositeFieldDefinitionBuilder<>()
						.properties(List.of(
								new TextFieldDefinitionBuilder().build("text")
						))
						.build("composite")
				);
	}

	public ConfiguredSwitchableFieldDefinition<Node> switchableFieldDefinitionExample() {
		final Option optionA = new OptionBuilder().label("A").build("a", "a");
		final Option optionB = new OptionBuilder().label("B").build("b", "b");
		final OptionListDefinition optionsDefinition = new OptionListDefinitionBuilder()
				.options(List.of(optionA, optionB))
				.sort(false)
				.build();
		final ComboBoxFieldDefinition<Option> options = new ComboBoxFieldDefinitionBuilder<Option>()
				.defaultValue(optionA.getValue())
				.build("switchable", optionsDefinition);

		// this cast is necessary, because Magnolia definition generics are incorrect...
		final AbstractSelectFieldDefinition<Option, OptionListDefinition> castedOptions = (AbstractSelectFieldDefinition)options;
		return new ConfiguredSwitchableFieldDefinitionBuilder<Node>()
				.itemProvider(new CurrentItemProviderDefinition<>())
				.propertyNameDecorator(PrefixNameDecorator.class)
				.forms(List.of(
						new CompositeFieldDefinitionBuilder<Node>()
								.properties(List.of(
										new TextFieldDefinitionBuilder().build("text")
								))
								.build(optionA.getValue()),
						new CompositeFieldDefinitionBuilder<Node>()
								.properties(List.of(
										new RichTextFieldDefinitionBuilder().build("richText")
								))
								.build(optionB.getValue())
				))
				.build("switchable", castedOptions);
	}
}

```
