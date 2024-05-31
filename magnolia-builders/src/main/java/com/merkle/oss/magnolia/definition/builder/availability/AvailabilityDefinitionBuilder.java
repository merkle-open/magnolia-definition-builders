package com.merkle.oss.magnolia.definition.builder.availability;

import info.magnolia.cms.security.operations.AccessDefinition;
import info.magnolia.cms.security.operations.ConfiguredAccessDefinition;
import info.magnolia.ui.api.availability.AvailabilityDefinition;
import info.magnolia.ui.api.availability.AvailabilityRuleDefinition;
import info.magnolia.ui.api.availability.ConfiguredAvailabilityDefinition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

public class AvailabilityDefinitionBuilder {
	@Nullable
	private Boolean root;
	@Nullable
	private Boolean properties;
	@Nullable
	private Boolean nodes;
	@Nullable
	private Boolean multiple;
	@Nullable
	private Collection<String> nodeTypes;
	@Nullable
	private AccessDefinition access;
	@Nullable
	private Collection<? extends AvailabilityRuleDefinition> rules;
	@Nullable
	private Boolean writePermissionRequired;

	public AvailabilityDefinitionBuilder() {}
	public AvailabilityDefinitionBuilder(final AvailabilityDefinition availability) {
		root(availability.isRoot());
		properties(availability.isProperties());
		nodes(availability.isNodes());
		multiple(availability.isMultiple());
		nodeTypes(availability.getNodeTypes());
		access(availability.getAccess());
		rules(availability.getRules());
		writePermissionRequired(availability.isWritePermissionRequired());
	}

	public AvailabilityDefinitionBuilder root(final boolean root) {
		this.root = root;
		return this;
	}

	public AvailabilityDefinitionBuilder properties(final boolean properties) {
		this.properties = properties;
		return this;
	}

	public AvailabilityDefinitionBuilder nodes(final boolean nodes) {
		this.nodes = nodes;
		return this;
	}

	public AvailabilityDefinitionBuilder multiple(final boolean multiple) {
		this.multiple = multiple;
		return this;
	}

	public AvailabilityDefinitionBuilder nodeType(final String nodeType) {
		return nodeTypes(Stream.concat(
				Stream.ofNullable(nodeTypes).flatMap(Collection::stream),
				Stream.of(nodeType)
		).collect(Collectors.toList()));
	}

	public AvailabilityDefinitionBuilder nodeTypes(final Collection<String> nodeTypes) {
		this.nodeTypes = nodeTypes;
		return this;
	}

	public AvailabilityDefinitionBuilder access(final AccessDefinition access) {
		this.access = access;
		return this;
	}

	public AvailabilityDefinitionBuilder access(final String... roles) {
		final ConfiguredAccessDefinition access = new ConfiguredAccessDefinition();
		access.setRoles(Arrays.asList(roles));
		return access(access);
	}


	public AvailabilityDefinitionBuilder rule(final AvailabilityRuleDefinition rule) {
		return rules(Stream.concat(
				Stream.ofNullable(rules).flatMap(Collection::stream),
				Stream.of(rule)
		).collect(Collectors.toList()));
	}

	public AvailabilityDefinitionBuilder rules(final Collection<? extends AvailabilityRuleDefinition> rules) {
		this.rules = rules;
		return this;
	}

	public AvailabilityDefinitionBuilder writePermissionRequired(final boolean writePermissionRequired) {
		this.writePermissionRequired = writePermissionRequired;
		return this;
	}

	public ConfiguredAvailabilityDefinition build() {
		final ConfiguredAvailabilityDefinition definition = new ConfiguredAvailabilityDefinition();
		Optional.ofNullable(root).ifPresent(definition::setRoot);
		Optional.ofNullable(properties).ifPresent(definition::setProperties);
		Optional.ofNullable(nodes).ifPresent(definition::setNodes);
		Optional.ofNullable(multiple).ifPresent(definition::setMultiple);
		Optional.ofNullable(nodeTypes).ifPresent(definition::setNodeTypes);
		Optional.ofNullable(access).ifPresent(definition::setAccess);
		Optional.ofNullable(rules).ifPresent(definition::setRules);
		Optional.ofNullable(writePermissionRequired).ifPresent(definition::setWritePermissionRequired);
		return definition;
	}
}
