# Magnolia AI Definition Builders

The Definition Builders module is a builder for Magnolia AI field definitions (Magnolia Renivo Module). Instead of using YAML, it allows to define 
field definitions in Java,  which is less error-prone than using YAML, especially for big apps.

## Requirements
* Java 21
* Magnolia >= 6.4

## Installation

* Add Maven dependency:
```xml
<dependency>
    <groupId>com.merkle.oss.magnolia</groupId>
    <artifactId>magnolia-ai-definition-builders</artifactId>
    <version>1.4.7</version>
</dependency>
```

## Examples
The following class shows some example usages:

```java
package com.merkle.oss.magnolia.definition.builder;

import com.merkle.oss.magnolia.definition.builder.simple.*;
import info.magnolia.ai.automations.ui.fields.AiAssistedTextFieldDefinition;
import info.magnolia.ai.automations.ui.fields.AiRichTextFieldDefinition;
import info.magnolia.ai.automations.ui.fields.AiTextFieldDefinition;
import info.magnolia.ui.field.*;

public class BuilderExample {

    public AiAssistedTextFieldDefinition aiAssistedTextFieldDefinitionExample() {
        return new AiAssistedTextFieldDefinitionBuilder()
                .label("Title")
                .extension("SeoMetadataTitleBlockTool")
                .build("title");
    }

    public AiTextFieldDefinition aiTextFieldDefinitionExample() {
        return new AiTextFieldDefinitionBuilder()
                .label("Text editor")
                .build("title");
    }

    public AiRichTextFieldDefinition aiRichTextFieldDefinitionExample() {
        return new AiRichTextFieldDefinitionBuilder()
                .label("Text editor")
                .height(500)
                .tables(true)
                .source(true)
                .build("richText");
    }
}

```
