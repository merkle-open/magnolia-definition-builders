package com.merkle.oss.magnolia.definition.custom.validator.template;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.merkle.oss.magnolia.definition.builder.validator.AbstractConfiguredFieldValidatorDefinitionBuilder;

public class TemplateValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<TemplateValidatorDefinition, TemplateValidatorDefinitionBuilder> {

    public TemplateValidatorDefinition build(final String templateId, final String... additionalTemplateIds) {
        return build("templateValidator", Stream.concat(
                Stream.of(templateId),
                Arrays.stream(additionalTemplateIds)
        ).collect(Collectors.toSet()));
    }

    public TemplateValidatorDefinition build(final String name, final Set<String> templateIds) {
        final TemplateValidatorDefinition definition = new TemplateValidatorDefinition(templateIds);
        super.populate(definition, name);
        return definition;
    }
}
