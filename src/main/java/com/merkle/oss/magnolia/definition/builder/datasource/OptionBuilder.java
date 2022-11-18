package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.optionlist.Option;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * builds a {@link Option}
 * @see <a href="https://nexus.magnolia-cms.com/service/local/repositories/magnolia.public.releases/archive/info/magnolia/ui/magnolia-ui-framework/6.2.26/magnolia-ui-framework-6.2.26-javadoc.jar/!/info/magnolia/ui/datasource/optionlist/Option.html">magnolia Docs - Option</a>
 * @author Merkle DACH
 */
public class OptionBuilder {
	@Nullable
	private String label;
	@Nullable
	private String iconSrc;

	public OptionBuilder label(final String label) {
		this.label = label;
		return this;
	}

	public OptionBuilder iconSrc(final String iconSrc) {
		this.iconSrc = iconSrc;
		return this;
	}

	public Option build(final String name, final String value) {
		final Option definition = new Option();
		definition.setName(name);
		definition.setValue(value);
		Optional.ofNullable(label).ifPresent(definition::setLabel);
		Optional.ofNullable(iconSrc).ifPresent(definition::setIconSrc);
		return definition;
	}
}
