package com.merkle.oss.magnolia.definition.custom.linkset;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum LinkTypes implements LinkType {
	INTERNAL("linkType.internal.label", "type_internal"),
	EXTERNAL("linkType.external.label", "type_external"),
	ASSET_DAM("linkType.asset_dam.label", "type_asset");

	private final String label;
	private final String value;

	LinkTypes(final String label, final String value) {
		this.label = label;
		this.value = value;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String getValue() {
		return value;
	}

	public static class Resolver implements LinkType.Resolver {
		@Override
		public Optional<LinkType> resolve(final String value) {
			return Arrays.stream(values())
					.filter(type -> Objects.equals(type.getValue(), value))
					.findFirst()
					.map(linkType -> linkType);
		}
	}
}
