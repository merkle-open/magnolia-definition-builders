package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * builds a {@link OptionListDefinition} - Option list data source
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Apps/App-configuration/Data-source-definition/Option-list-data-source.html">magnolia Docs - Option list data source </a>
 * @author Merkle DACH
 */
public class OptionListDefinitionBuilder extends AbstractBaseDatasourceDefinitionBuilder<OptionListDefinition, OptionListDefinitionBuilder>{
	@Nullable
	private List<Option> options;
	@Nullable
	private Boolean sort;

	public OptionListDefinitionBuilder() {
		super(OptionListDefinition::new);
	}

	public OptionListDefinitionBuilder sort(final boolean sort) {
		this.sort = sort;
		return self();
	}

	public OptionListDefinitionBuilder option(final Option option) {
		return options(Stream.concat(
				Stream.ofNullable(options).flatMap(Collection::stream),
				Stream.of(option)
		).collect(Collectors.toList()));
	}

	public OptionListDefinitionBuilder options(final List<Option> options) {
		this.options = options;
		return self();
	}


	@Override
	public OptionListDefinition build() {
		final OptionListDefinition definition = super.build();
		Optional.ofNullable(sort).ifPresent(definition::setSort);
		Stream.ofNullable(options).flatMap(Collection::stream).forEach(definition.getOptions()::add);
		return definition;
	}
}
