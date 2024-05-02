# Key Generators

## EditorPropertyDefinitionKeyGenerator  
```properties
<DIALOG_NAME>.field.<FIELDNAME>.label
<FALLBACK_DIALOG_NAME>.field.<FIELDNAME>.label
```

## FieldValidatorDefinitionKeyGenerator
```properties
<DIALOG_NAME>.field.<VALIDATOR_NAME>.errorMessage
<FALLBACK_DIALOG_NAME>.field.<VALIDATOR_NAME>.errorMessage
validators.<VALIDATOR_NAME>.errorMessage
```

## Setup
```java
import com.merkle.oss.magnolia.definition.key.generator.EditorPropertyDefinitionKeyGenerator;
import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;
import com.merkle.oss.magnolia.definition.key.generator.KeyGeneratorUpdater;
import info.magnolia.module.ModuleLifecycle;
import info.magnolia.module.ModuleLifecycleContext;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinition;

import javax.inject.Inject;

public class SomeModule implements ModuleLifecycle {
	private final KeyGeneratorUpdater keyGeneratorUpdater;

	@Inject
	public SomeModule(final KeyGeneratorUpdater keyGeneratorUpdater) {
		this.keyGeneratorUpdater = keyGeneratorUpdater;
	}

	@Override
	public void start(ModuleLifecycleContext moduleLifecycleContext) {
		keyGeneratorUpdater.update(FieldDefinition.class, EditorPropertyDefinitionKeyGenerator.class);
		keyGeneratorUpdater.update(EditorPropertyDefinition.class, EditorPropertyDefinitionKeyGenerator.class);
		keyGeneratorUpdater.update(FieldValidatorDefinition.class, FieldValidatorDefinitionKeyGenerator.class);
	}
}
```

### DI-Bindings
```xml
<module>
    <name>SomeModule</name>
    ...
    <components>
        <id>main</id>
        <configurer>
            <class>GuiceComponentConfigurer</class>
        </configurer>
    </components>
    ...
</module>
```

```java
import info.magnolia.objectfactory.guice.AbstractGuiceComponentConfigurer;
import com.merkle.oss.magnolia.definition.key.generator.configuration.IdPrefix;
import com.merkle.oss.magnolia.definition.key.generator.configuration.FallbackDialogName;

public class GuiceComponentConfigurer extends AbstractGuiceComponentConfigurer {
	@Override
	protected void configure() {
		binder().bind(String.class)
				.annotatedWith(IdPrefix.class)
				.toInstance("SomeModule");

		binder().bind(String.class)
				.annotatedWith(FallbackDialogName.class)
				.toInstance("CommonDialog");

		//Optional
		final Multibinder<Class<?>> excludedAncestorsMultibinder = Multibinder.newSetBinder(binder(), new TypeLiteral<Class<?>>(){}, ExcludedAncestors.class);
		excludedAncestorsMultibinder.addBinding().toInstance(SomeDefinitionOfAncestorThatShouldBeExcluded.class);
	}
}
```

## Custom key prefix
The generated keys can be prefixed, which can be useful if you e.g. have 2 different dialogs with same field names, but want to use the fallbackDialogName.

### Dialog
```java
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.module.blossom.annotation.TabFactory;

import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.validator.RegexpValidatorDefinitionBuilder;
import com.merkle.oss.magnolia.definition.key.generator.KeyPrefixer;

@TabFactory("someTab")
public List<EditorPropertyDefinition> someTab() {
    return List.of(
            KeyPrefixer.keyPrefix(
                    new TextFieldDefinitionBuilder()
                        .validator(new RegexpValidatorDefinitionBuilder().pattern("^[a-zA-Z0-9]*$").build())
                        .build("title"), 
                    "somePrefix"
            )
    );
}
```
### Key
```properties
<DIALOG_NAME>.somePrefix.field.title.label=Title
<DIALOG_NAME>.somePrefix.field.title.regexpValidator.errorMessage=Must be alphanumeric
<FALLBACK_DIALOG_NAME>.somePrefix.field.title.label=Title
<FALLBACK_DIALOG_NAME>.somePrefix.field.title.regexpValidator.errorMessage=Must be alphanumeric
```