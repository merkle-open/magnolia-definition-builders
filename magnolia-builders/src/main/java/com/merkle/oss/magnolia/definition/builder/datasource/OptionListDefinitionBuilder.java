package com.merkle.oss.magnolia.definition.builder.datasource;

import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;

import jakarta.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * builds a {@link OptionListDefinition} - Option list data source
 *
 * @author Merkle DACH
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Apps/App-configuration/Data-source-definition/Option-list-data-source.html">magnolia Docs - Option list data source </a>
 */
public class OptionListDefinitionBuilder extends AbstractBaseDatasourceDefinitionBuilder<OptionListDefinition, OptionListDefinitionBuilder> {
	@Nullable
	private String name;
	@Nullable
	private List<Option> options;
	@Nullable
	private Boolean sort;

	public OptionListDefinitionBuilder() {}
	public OptionListDefinitionBuilder(final OptionListDefinition definition) {
		super(definition);
		name(definition.getName());
		options(definition.getOptions());
		sort(definition.isSort());
	}

	public OptionListDefinitionBuilder name(final String name) {
		this.name = name;
		return self();
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

	public OptionListDefinitionBuilder option(final OptionEnum option) {
		return option(create(option));
	}

	public OptionListDefinitionBuilder options(final OptionEnum... options) {
		return options(Arrays
				.stream(options)
				.map(this::create)
				.collect(Collectors.toList())
		);
	}

	public <T extends OptionEnum> OptionListDefinitionBuilder options(final Class<T> optionsClass, final T... excludeOptions) {
		final Set<T> excludes = Set.of(excludeOptions);
		return options(Arrays
				.stream(optionsClass.getEnumConstants())
				.filter(Predicate.not(excludes::contains))
				.map(this::create)
				.collect(Collectors.toList())
		);
	}

	private Option create(final OptionEnum option) {
		return new OptionBuilder()
				.label(option.getLabel())
				.build(option.getValue(), option.getValue());
	}

	public OptionListDefinition build() {
		final OptionListDefinition definition = new OptionListDefinition();
		super.populate(definition);
		Optional.ofNullable(name).ifPresent(definition::setName);
		Optional.ofNullable(sort).ifPresent(definition::setSort);
		Stream.ofNullable(options).flatMap(Collection::stream).forEach(definition.getOptions()::add);
		return definition;
	}

	public interface OptionEnum {
		String getLabel();
		String getValue();
	}
}
