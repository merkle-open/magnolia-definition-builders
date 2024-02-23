package com.merkle.oss.magnolia.definition.custom.videoset;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;

import java.util.Optional;

public interface VideoType extends OptionEnum {
	interface Resolver {
		Optional<VideoType> resolve(String value);
	}
}
