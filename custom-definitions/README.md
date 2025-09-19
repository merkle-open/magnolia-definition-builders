# Custom Definitions
## fields

 - [Extened richtext](src/main/java/com/merkle/oss/magnolia/definition/custom/richtext/README.md)
 - Separator 
 - [LinkSet](src/main/java/com/merkle/oss/magnolia/definition/custom/linkset/README.md)
 - [ImageSet](src/main/java/com/merkle/oss/magnolia/definition/custom/imageset/README.md)
 - [VideoSet](src/main/java/com/merkle/oss/magnolia/definition/custom/videoset/README.md)
 - [ChildNodeWrapper](src/main/java/com/merkle/oss/magnolia/definition/custom/childnodewrapper/README.md)
 - [ColorPicker](src/main/java/com/merkle/oss/magnolia/definition/custom/colorpicker/README.md)
 - [QR-Code](src/main/java/com/merkle/oss/magnolia/definition/custom/qrcode/README.md)

## validators
 - MimeType
 - NodeType
 - Template
 - [DateDependency](src/main/java/com/merkle/oss/magnolia/definition/custom/validator/datedependency/README.md)
 - HasProperty

## Configuration

```java
import com.merkle.oss.magnolia.definition.custom.configuration.CustomDefinitionBuildersConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@Import({
		CustomDefinitionBuildersConfiguration.class
})
public class Configuration { }
```

```xml
<components>
    <id>main</id>
    <component>
        <!-- Optional defaults to com.merkle.oss.magnolia.definition.custom.configuration.DefaultLocaleProvider -->
        <type>com.merkle.oss.magnolia.definition.custom.configuration.LocaleProvider</type>
        <implementation>com.some.package.CustomLocaleProvider</implementation>
    </component>
    <component>
        <!-- Optional defaults to com.merkle.oss.magnolia.definition.custom.configuration.DefaultLinkUtil -->
        <type>com.merkle.oss.magnolia.definition.custom.configuration.LinkUtil</type>
        <implementation>com.some.package.CustomLinkUtil</implementation>
    </component>
</components>
```
