package com.merkle.oss.magnolia.definition.custom.videoset;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum VideoTypes implements VideoType {
	DAM("videoType.dam.label", "dam"),
	YOUTUBE("videoType.youtube.label", "youtube"),
	VIMEO("videoType.vimeo.label", "vimeo"),
	;

	private final String label;
	private final String value;

	VideoTypes(final String label, final String value) {
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

	public static Optional<VideoType> fromValue(final String value) {
		return Arrays.stream(values())
				.filter(type -> Objects.equals(type.getValue(), value))
				.findFirst()
				.map(videoType -> videoType);
	}
}