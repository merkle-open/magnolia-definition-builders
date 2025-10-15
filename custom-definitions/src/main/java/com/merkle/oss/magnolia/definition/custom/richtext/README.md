# Extended Richtext
An extended richtext.

## Usage
### Toolbar config

### Dialog

```java
import com.merkle.oss.magnolia.definition.custom.richtext.ExtendedRichTextDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.config.heading.HeadingOption;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.ToolbarConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.toolbar.items.*;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.module.blossom.annotation.TabFactory;

import java.util.List;

@TabFactory("someTab")
public List<EditorPropertyDefinition> someTab() {
    return List.of(
            new ExtendedRichTextDefinitionBuilder()
                    .toolbarConfig(new ToolbarConfig.Builder()
                            .item(
                                new FormattingToolbarConfigItem.Builder()
                                    .bold()
                                    .italic()
                                    .superscript()
                                    .subscript()
                                    .specialChar()
                                    .build()
                            )
                            .item(
                                new ListToolbarConfigItem.Builder()
                                    .numberedList()
                                    .bulletedList()
                                    .build()
                            )
                            .item(
                                new LinksToolbarConfigItem.Builder()
                                    .link()
                                    .internalLink()
                                    .damLink()
                                    .build()
                            )
                            .item(
                                new FontToolbarConfigItem.Builder()
                                    .heading()
                                    .build()
                            )
                            .item(
                                new UndoToolbarConfigItem.Builder()
                                    .undo()
                                    .redo()
                                    .build()
                            )
                            .build()
                    )
                    .headings(List.of(HeadingOption.PARAGRAPH, HeadingOption.HEADING_3, HeadingOption.HEADING_4))
                    .build("someRichText")
    );
}
```

## Advanced Examples
### Link decorators
Adding attributes to links based on predefined rules (automatic) or via in an editing balloon as switch buttons (manual).
```java
import com.merkle.oss.magnolia.definition.custom.richtext.ExtendedRichTextDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkConfig;
import com.merkle.oss.magnolia.definition.custom.richtext.config.link.LinkDecoratorDefinition;import com.merkle.oss.magnolia.definition.custom.richtext.config.link.MgnlLinkConfig;

import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.module.blossom.annotation.TabFactory;

import java.util.List;

@TabFactory("someTab")
public List<EditorPropertyDefinition> someTab() {
    return List.of(
        new ExtendedRichTextDefinitionBuilder()
            .linkConfig(
                new LinkConfig.Builder()
                    .decorator("automaticDecoratorExample", new LinkDecoratorDefinition.AutomaticBuilder()
                        .attribute("hreflang", "en")
                        .build("^http(s|):\\/\\/")
                    )
                    .decorator("manualDecoratorExample", new LinkDecoratorDefinition.ManualBuilder()
                        .attribute("referrerpolicy", "no-referrer") // manual decorators setting the same attributes must have the exact same name!!
                        .build("some.i18n.dictionary.key") // label can be dictionary key or plain text
                    )
                    .addTargetToExternalLinks(true)
                    .allowCreatingEmptyLinks(false)
                    .build()
            )
            .mgnlLinkConfig(
                new MgnlLinkConfig.Builder()
                    .decorator("automaticDecoratorExample", new LinkDecoratorDefinition.AutomaticBuilder()
                            .attribute("hreflang", "en")
                            .build(".*")
                    )
                    .decorator("manualDecoratorExample", new LinkDecoratorDefinition.ManualBuilder()
                            .attribute("referrerpolicy", "no-referrer") // manual decorators setting the same attributes must have the exact same name!!
                            .build("don't send referrer") // label can be dictionary key or plain text
                    )
                    .build()
            )
            ...
    );
}
```

### I-Frame
Allows configuring an iframe in source field (ToolsGroupBuilder), with whitelisted attributes.
```java
import com.merkle.oss.magnolia.definition.builder.validator.SafeHtmlValidatorDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.ExtendedRichTextDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.HtmlSupport;
import com.merkle.oss.magnolia.definition.custom.richtext.config.html.Pattern;

import info.magnolia.objectfactory.Components;
import info.magnolia.ui.UiFrameworkModule;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.ui.field.RichTextFieldDefinition;
import info.magnolia.ui.field.SafeHtmlValidatorDefinition;

import java.util.List;

@TabFactory("someTab")
public List<EditorPropertyDefinition> someTab() {
    return List.of(
            new ExtendedRichTextDefinitionBuilder()
                    .toolbarConfig(new SomeToolbar())
                    .htmlSupport(
                            new HtmlSupport.Builder()
                                    .allow(new Pattern.Builder().build("iframe"))
                                    .build()
                    )
                    //Override global SafeHtmlValidator to allow iframes https://docs.magnolia-cms.com/product-docs/6.2/developing/templating/dialog-definition/field-definition/field-validators/#_global_validators
                    .validators(getGlobalSafeHtmlValidator().map(this::cloneWithAllowedIframe).stream().collect(Collectors.toList()))
                    .build("someRichText")
    );
}

private SafeHtmlValidatorDefinition cloneWithAllowedIframe(final SafeHtmlValidatorDefinition validator) {
    return new SafeHtmlValidatorDefinitionBuilder()
            .allowedTags(Stream.concat(
                    validator.getAllowedTags().stream(),
                    Stream.of("iframe")
            ).collect(Collectors.toList()))
            .allowedAttributes(Stream.concat(
                    validator.getAllowedAttributes().stream(),
                    Stream.of(new SafeHtmlValidatorDefinitionBuilder
                            .AttributeBuilder()
                            .attributes(List.of("allow", "allowfullscreen", "height", "loading", "name", "referrerpolicy", "sandbox", "src", "srcdoc", "width"))
                            .build("iframe")
                    )
            ).collect(Collectors.toList()))
            .allowedProtocols(validator.getAllowedProtocols())
            .globallyAllowedAttributes(validator.getGloballyAllowedAttributes())
            .build();
}

private Optional<SafeHtmlValidatorDefinition> getGlobalSafeHtmlValidator() {
    return Components.getComponent(UiFrameworkModule.class)
            .getDefaultFieldValidators()
            .get(RichTextFieldDefinition.class.getName())
            .stream()
            .filter(SafeHtmlValidatorDefinition.class::isInstance)
            .map(SafeHtmlValidatorDefinition.class::cast)
            .findFirst();
}
```
