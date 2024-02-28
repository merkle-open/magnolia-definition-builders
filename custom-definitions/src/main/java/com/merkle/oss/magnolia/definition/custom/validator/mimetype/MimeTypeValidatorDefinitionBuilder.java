package com.merkle.oss.magnolia.definition.custom.validator.mimetype;

import com.merkle.oss.magnolia.definition.builder.validator.AbstractConfiguredFieldValidatorDefinitionBuilder;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MimeTypeValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<MimeTypeValidatorDefinition, MimeTypeValidatorDefinitionBuilder> {
	@Nullable
	private Set<String> mimeTypes;

	public MimeTypeValidatorDefinitionBuilder mimeType(final String mimeType) {
		return mimeTypes(Stream.concat(
				Stream.ofNullable(mimeTypes).flatMap(Collection::stream),
				Stream.of(mimeType)
		).collect(Collectors.toSet()));
	}

	public MimeTypeValidatorDefinitionBuilder mimeTypes(final Set<String> mimeTypes) {
		this.mimeTypes = mimeTypes;
		return self();
	}

	public MimeTypeValidatorDefinition build() {
		return build("mimeTypeValidator");
	}
	public MimeTypeValidatorDefinition build(final String name) {
		final MimeTypeValidatorDefinition definition = new MimeTypeValidatorDefinition(Optional.ofNullable(mimeTypes).orElseGet(Collections::emptySet));
		super.populate(definition, name);
		return definition;
	}
}
