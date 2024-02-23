package com.merkle.oss.magnolia.definition.custom.imageset;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;

import java.util.Optional;

public interface ImageType extends OptionEnum {
	interface Resolver {
		Optional<ImageType> resolve(String value);
	}
}
