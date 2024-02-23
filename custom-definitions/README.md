# Custom Definitions
## fields

 - Extened richtext
 - Separator 
 - [LinkSet](src/main/java/com/merkle/oss/magnolia/definition/custom/linkset/README.md)

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