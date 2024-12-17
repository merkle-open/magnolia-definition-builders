package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.ToolbarGroup;

public abstract class AbstractToolbarGroupBuilder<T extends AbstractToolbarGroupBuilder<T>> {
	private final String label;
	@Nullable
    private List<String> options;

	public AbstractToolbarGroupBuilder(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public T option(final String option) {
		return options(Stream.concat(
				Stream.ofNullable(options).flatMap(Collection::stream),
				Stream.of(option)
		).collect(Collectors.toList()));
	}

	public T options(final List<String> options) {
		this.options = options;
		return self();
	}

	@SuppressWarnings("unchecked")
	protected T self() {
		return (T) this;
	}

	public ToolbarGroup build() {
		return new ToolbarGroup(
				label,
				Optional.ofNullable(options).orElseGet(Collections::emptyList)
		);
	}
}
