package com.merkle.oss.magnolia.definition.custom.imageset;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum ImageTypes implements ImageType {
	DAM("imageType.dam.label", "dam");

	private final String label;
	private final String value;

	ImageTypes(final String label, final String value) {
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

	public static class Resolver implements ImageType.Resolver {
		@Override
		public Optional<ImageType> resolve(String value) {
			return Arrays.stream(values())
					.filter(type -> Objects.equals(type.getValue(), value))
					.findFirst()
					.map(imageType -> imageType);
		}
	}
}
