package com.merkle.oss.magnolia.definition.builder.availability;

import info.magnolia.ui.api.availability.ConfiguredAvailabilityRuleDefinition;

public class AvailabilityRuleDefinitionBuilder extends AbstractAvailabilityRuleDefinitionBuilder<ConfiguredAvailabilityRuleDefinition, AvailabilityRuleDefinitionBuilder> {
    public AvailabilityRuleDefinitionBuilder() {}
    public AvailabilityRuleDefinitionBuilder(final ConfiguredAvailabilityRuleDefinition definition) {
        super(definition);
    }

    public ConfiguredAvailabilityRuleDefinition build() {
        final ConfiguredAvailabilityRuleDefinition definition = new ConfiguredAvailabilityRuleDefinition();
        populate(definition);
        return definition;
    }
}
