package com.merkle.oss.magnolia.definition.custom.linkset;

import info.magnolia.ui.field.CheckBoxFieldDefinition;
import info.magnolia.ui.field.TextFieldDefinition;

import javax.annotation.Nullable;

public class LinkDefinitionBuilder extends LinkSetDefinitionBuilder {

	@Nullable
	protected CheckBoxFieldDefinition openInNewTab(final String name) {
		return null;
	}

	@Nullable
	protected TextFieldDefinition linkText(final String name) {
		return null;
	}
}
