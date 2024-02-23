package com.merkle.oss.magnolia.definition.custom.switchable;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder.OptionEnum;

import java.util.Objects;
import java.util.function.Function;

public class FieldOption<T extends OptionEnum> {
	private final T type;
	private final SwitchableForm field;

	public FieldOption(
			final T type,
			final Function<String, SwitchableForm> fieldFactory
	) {
		this.type = type;
		this.field = fieldFactory.apply(type.getValue());
	}

	public SwitchableForm getField() {
		return field;
	}

	public T getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FieldOption<?> that = (FieldOption<?>) o;
		return Objects.equals(type, that.type) && Objects.equals(field, that.field);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, field);
	}

	@Override
	public String toString() {
		return "FieldOption{" +
				"type=" + type +
				", field=" + field +
				'}';
	}
}
