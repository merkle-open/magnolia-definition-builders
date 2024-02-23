package com.merkle.oss.magnolia.definition.custom.linkset;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;

import java.util.Optional;

public interface LinkType extends OptionEnum {
	interface Resolver {
		Optional<LinkType> resolve(String value);
	}
}
