package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractToolbarGroupBuilder<T extends AbstractToolbarGroupBuilder<T>> {
	private final String name;
	private final List<String> options = new ArrayList<>();

	public AbstractToolbarGroupBuilder(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public T option(final String option) {
		options.add(option);
		return (T)this;
	}

	public List<String> getOptions() {
		return options;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractToolbarGroupBuilder<?> that = (AbstractToolbarGroupBuilder<?>) o;
		return Objects.equals(name, that.name) && Objects.equals(options, that.options);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, options);
	}

	@Override
	public String toString() {
		return "ToolbarGroupBuilder{" +
				"name='" + name + '\'' +
				", options=" + options +
				'}';
	}
}
