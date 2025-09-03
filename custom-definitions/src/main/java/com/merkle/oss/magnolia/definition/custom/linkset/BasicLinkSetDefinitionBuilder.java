package com.merkle.oss.magnolia.definition.custom.linkset;

import com.merkle.oss.magnolia.definition.builder.complex.AbstractConfiguredComplexPropertyDefinitionBuilder;
import info.magnolia.ui.field.*;

import jakarta.annotation.Nullable;
import javax.jcr.Node;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicLinkSetDefinitionBuilder<L extends ConfiguredFieldDefinition<?>> extends AbstractConfiguredComplexPropertyDefinitionBuilder<Node, BasicLinkSetDefinition<L>, BasicLinkSetDefinitionBuilder<L>> {
	@Nullable
	private TextFieldDefinition anchorId;
	@Nullable
	private TextFieldDefinition linkText;
	@Nullable
	private CheckBoxFieldDefinition openInNewWindow;
	@Nullable
	private Boolean readOnly;
	@Nullable
	private Boolean required;
	@Nullable
	private List<FieldValidatorDefinition> validators;
	private boolean singleTree;

	public BasicLinkSetDefinitionBuilder<L> singleTree(final boolean singleTree) {
        this.singleTree = singleTree;
		return self();
	}

	public BasicLinkSetDefinitionBuilder<L> anchorId(@Nullable final TextFieldDefinition anchorId) {
		this.anchorId = anchorId;
		return self();
	}

	public BasicLinkSetDefinitionBuilder<L> linkText(@Nullable final TextFieldDefinition linkText) {
		this.linkText = linkText;
		return self();
	}

	public BasicLinkSetDefinitionBuilder<L> openInNewWindow(@Nullable final CheckBoxFieldDefinition openInNewWindow) {
		this.openInNewWindow = openInNewWindow;
		return self();
	}

	public BasicLinkSetDefinitionBuilder<L> readOnly() {
		return readOnly(true);
	}

	public BasicLinkSetDefinitionBuilder<L> readOnly(final boolean readOnly) {
		this.readOnly = readOnly;
		return self();
	}

	public BasicLinkSetDefinitionBuilder<L> required() {
		return required(true);
	}

	public BasicLinkSetDefinitionBuilder<L> required(final boolean required) {
		this.required = required;
		return self();
	}

	public BasicLinkSetDefinitionBuilder<L> validator(final FieldValidatorDefinition validator) {
		return validators(Stream.concat(
				Stream.ofNullable(validators).flatMap(Collection::stream),
				Stream.of(validator)
		).collect(Collectors.toList()));
	}

	public BasicLinkSetDefinitionBuilder<L> validators(final List<FieldValidatorDefinition> validators) {
		this.validators = validators;
		return self();
	}

	public BasicLinkSetDefinition<L> build(final String name, final L link) {
		final BasicLinkSetDefinition<L> definition = new BasicLinkSetDefinition<>(link, singleTree);
		Optional.ofNullable(anchorId).ifPresent(definition::setAnchorId);
		Optional.ofNullable(linkText).ifPresent(definition::setLinkText);
		Optional.ofNullable(openInNewWindow).ifPresent(definition::setOpenInNewWindow);
		Optional.ofNullable(readOnly).ifPresent(definition::setReadOnly);
		Optional.ofNullable(required).ifPresent(definition::setRequired);
		Stream.ofNullable(validators).flatMap(Collection::stream).forEach(definition.getValidators()::add);
		super.populate(definition, name);
		return definition;
	}
}
