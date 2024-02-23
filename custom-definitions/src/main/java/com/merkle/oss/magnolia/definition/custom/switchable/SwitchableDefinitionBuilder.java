package com.merkle.oss.magnolia.definition.custom.switchable;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;

public class SwitchableDefinitionBuilder<T extends OptionEnum> extends AbstractSwitchableDefinitionBuilder<T, SwitchableDefinitionBuilder<T>> {
	public SwitchableDefinitionBuilder(final String labelPrefix) {
		super(labelPrefix);
	}
}
