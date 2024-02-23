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