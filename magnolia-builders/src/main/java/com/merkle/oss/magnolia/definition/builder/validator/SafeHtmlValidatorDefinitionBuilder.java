package com.merkle.oss.magnolia.definition.builder.validator;

import info.magnolia.ui.field.SafeHtmlValidatorDefinition;
import info.magnolia.ui.field.SafeHtmlValidatorDefinition.Attribute;
import info.magnolia.ui.field.SafeHtmlValidatorDefinition.Protocol;

import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SafeHtmlValidatorDefinitionBuilder extends AbstractConfiguredFieldValidatorDefinitionBuilder<SafeHtmlValidatorDefinition, SafeHtmlValidatorDefinitionBuilder>{
	@Nullable
	private List<String> allowedTags;
	@Nullable
	private List<String> globallyAllowedAttributes;
	@Nullable
	private List<Attribute> allowedAttributes;
	@Nullable
	private List<Protocol> allowedProtocols;

	public SafeHtmlValidatorDefinitionBuilder() {}
	public SafeHtmlValidatorDefinitionBuilder(final SafeHtmlValidatorDefinition definition) {
		super(definition);
		allowedTags(definition.getAllowedTags());
		globallyAllowedAttributes(definition.getGloballyAllowedAttributes());
		allowedAttributes(definition.getAllowedAttributes());
		allowedProtocols(definition.getAllowedProtocols());
	}

	public SafeHtmlValidatorDefinitionBuilder allowedTag(final String allowedTag) {
		return item(this::allowedTags, allowedTags, allowedTag);
	}

	public SafeHtmlValidatorDefinitionBuilder allowedTags(final List<String> allowedTags) {
		this.allowedTags = allowedTags;
		return self();
	}

	public SafeHtmlValidatorDefinitionBuilder globallyAllowedAttribute(final String globallyAllowedAttribute) {
		return item(this::globallyAllowedAttributes, globallyAllowedAttributes, globallyAllowedAttribute);
	}

	public SafeHtmlValidatorDefinitionBuilder globallyAllowedAttributes(final List<String> globallyAllowedAttributes) {
		this.globallyAllowedAttributes = globallyAllowedAttributes;
		return self();
	}

	public SafeHtmlValidatorDefinitionBuilder allowedAttribute(final String tag, final String... attributes) {
		return allowedAttribute(new AttributeBuilder().attributes(List.of(attributes)).build(tag));
	}

	public SafeHtmlValidatorDefinitionBuilder allowedAttribute(final Attribute allowedAttribute) {
		return item(this::allowedAttributes, allowedAttributes, allowedAttribute);
	}

	public SafeHtmlValidatorDefinitionBuilder allowedAttributes(final List<Attribute> allowedAttributes) {
		this.allowedAttributes = allowedAttributes;
		return self();
	}

	public SafeHtmlValidatorDefinitionBuilder allowedProtocol(final String tag, final String attribute, final String... protocols) {
		return allowedProtocol(new ProtocolBuilder().protocols(List.of(protocols)).build(tag, attribute));
	}

	public SafeHtmlValidatorDefinitionBuilder allowedProtocol(final Protocol allowedProtocol) {
		return item(this::allowedProtocols, allowedProtocols, allowedProtocol);
	}

	public SafeHtmlValidatorDefinitionBuilder allowedProtocols(final List<Protocol> allowedProtocols) {
		this.allowedProtocols = allowedProtocols;
		return self();
	}

	private <T> SafeHtmlValidatorDefinitionBuilder item(final Consumer<List<T>> consumer, final List<T> items, final T item) {
		consumer.accept(Stream.concat(
				Stream.ofNullable(items).flatMap(Collection::stream),
				Stream.of(item)
		).collect(Collectors.toList()));
		return self();
	}

	public SafeHtmlValidatorDefinition build() {
		return build("safeHtmlValidator");
	}
	public SafeHtmlValidatorDefinition build(final String name) {
		final SafeHtmlValidatorDefinition definition = new SafeHtmlValidatorDefinition();
		super.populate(definition, name);
		Stream.ofNullable(allowedTags).flatMap(Collection::stream).forEach(definition.getAllowedTags()::add);
		Stream.ofNullable(globallyAllowedAttributes).flatMap(Collection::stream).forEach(definition.getGloballyAllowedAttributes()::add);
		Stream.ofNullable(allowedAttributes).flatMap(Collection::stream).forEach(definition.getAllowedAttributes()::add);
		Stream.ofNullable(allowedProtocols).flatMap(Collection::stream).forEach(definition.getAllowedProtocols()::add);
		return definition;
	}

	public static class AttributeBuilder {
		private List<String> attributes = new ArrayList<>();

		public AttributeBuilder attribute(final String attribute) {
			this.attributes.add(attribute);
			return this;
		}

		public AttributeBuilder attributes(final List<String> attributes) {
			this.attributes = attributes;
			return this;
		}

		public Attribute build(final String tag) {
			final Attribute attribute = new Attribute();
			attribute.setTag(tag);
			attribute.setAttributes(attributes.toArray(String[]::new));
			return attribute;
		}
	}

	public static class ProtocolBuilder {
		private List<String> protocols = new ArrayList<>();

		public ProtocolBuilder protocol(final String protocol) {
			this.protocols.add(protocol);
			return this;
		}

		public ProtocolBuilder protocols(final List<String> protocols) {
			this.protocols = protocols;
			return this;
		}

		public Protocol build(final String tag, final String attribute) {
			final Protocol protocol = new Protocol();
			protocol.setTag(tag);
			protocol.setAttribute(attribute);
			protocol.setProtocols(protocols.toArray(String[]::new));
			return protocol;
		}
	}
}
