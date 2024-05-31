package com.merkle.oss.magnolia.definition.custom.availability;

import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.api.availability.AvailabilityDefinition;
import info.magnolia.ui.api.availability.AvailabilityRule;
import info.magnolia.ui.api.availability.AvailabilityRuleDefinition;
import info.magnolia.ui.api.availability.ConfiguredAvailabilityRuleDefinition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.merkle.oss.magnolia.definition.builder.availability.AbstractAvailabilityRuleDefinitionBuilder;

public class AndAvailabilityRule implements AvailabilityRule {
	private final AvailabilityDefinition availabilityDefinition;
	private final Definition ruleDefinition;
	private final ComponentProvider componentProvider;

	@Inject
	public AndAvailabilityRule(
			final AvailabilityDefinition availabilityDefinition,
			final Definition ruleDefinition,
			final ComponentProvider componentProvider
	) {
		this.availabilityDefinition = availabilityDefinition;
		this.ruleDefinition = ruleDefinition;
		this.componentProvider = componentProvider;
	}

	@Override
	public boolean isAvailable(final Collection<?> items) {
		return ruleDefinition.rules
				.stream()
				.map(definition -> componentProvider.newInstance(definition.getImplementationClass(), availabilityDefinition, definition))
				.allMatch(rule -> rule.isAvailable(items));
	}

	public static class Definition extends ConfiguredAvailabilityRuleDefinition {
		private final Set<AvailabilityRuleDefinition> rules;

		public Definition(final Set<AvailabilityRuleDefinition> rules) {
			setImplementationClass(AndAvailabilityRule.class);
			this.rules = rules;
		}

		public static class Builder extends AbstractAvailabilityRuleDefinitionBuilder<Definition, Builder> {
			private Set<AvailabilityRuleDefinition> rules = new HashSet<>();

			public Builder() {}
			public Builder(final Definition definition) {
				super(definition);
			}

			public Builder rule(final AvailabilityRuleDefinition rule) {
				return rules(Stream.concat(
						Stream.ofNullable(rules).flatMap(Collection::stream),
						Stream.of(rule)
				).collect(Collectors.toSet()));
			}

			public Builder rules(final Set<AvailabilityRuleDefinition> rules) {
				this.rules = rules;
				return self();
			}

			public Definition build() {
				final Definition definition = new Definition(rules);
				populate(definition);
				return definition;
			}
		}
	}
}
