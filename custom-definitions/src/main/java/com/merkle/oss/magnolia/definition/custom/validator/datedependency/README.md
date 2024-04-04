# DateDependency Validator
Validator to define dependencies between multiple date fields. E.g. endDate must be after startDate.

## Usage
### Dialog
```java
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.module.blossom.annotation.TabFactory;
import com.merkle.oss.magnolia.definition.builder.simple.DateFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.validator.datedependency.DateDependencyValidatorDefinitionBuilder;

@TabFactory("someTab")
public List<EditorPropertyDefinition> someTab() {
    final DateDependencyValidatorDefinitionBuilder dateDependencyValidatorBuilder = new DateDependencyValidatorDefinitionBuilder(state ->
            state.get("startDate").flatMap(startDate ->
                    state.get("endDate").map(startDate::isBefore)
            ).orElse(true)
    );
    return List.of(
            //Option1 specifying dateField name & i18n 
            new DateFieldDefinitionBuilder().validator(dateDependencyValidatorBuilder.build("startDate")).build("startDate"),
            new DateFieldDefinitionBuilder().i18n().validator(dateDependencyValidatorBuilder.build("endDate", true)).build("endDate")
            
            //Option2 Taking name & i18n from DateFieldDefinition
//            dateDependencyValidatorBuilder.buildAndAdd(new DateFieldDefinitionBuilder().build("startDate")),
//            dateDependencyValidatorBuilder.buildAndAdd(new DateFieldDefinitionBuilder().i18n().build("endDate"))
    );
}
```