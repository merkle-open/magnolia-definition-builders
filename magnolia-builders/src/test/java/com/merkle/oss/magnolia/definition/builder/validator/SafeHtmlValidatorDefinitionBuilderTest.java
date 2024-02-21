package com.merkle.oss.magnolia.definition.builder.validator;

import com.merkle.oss.magnolia.definition.builder.validator.SafeHtmlValidatorDefinitionBuilder.AttributeBuilder;
import com.merkle.oss.magnolia.definition.builder.validator.SafeHtmlValidatorDefinitionBuilder.ProtocolBuilder;
import info.magnolia.ui.editor.validator.NodeNameValidatorDefinition;
import info.magnolia.ui.field.SafeHtmlValidatorDefinition;
import info.magnolia.ui.field.SafeHtmlValidatorDefinition.Attribute;
import info.magnolia.ui.field.SafeHtmlValidatorDefinition.Protocol;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SafeHtmlValidatorDefinitionBuilderTest extends AbstractConfiguredFieldValidatorDefinitionBuilderTestCase {
	private final Equator<Attribute> attributeEquator = new Equator<>() {
		@Override
		public boolean equate(final Attribute a1, final Attribute a2) {
			return Objects.equals(a1.getTag(), a2.getTag()) && Arrays.equals(a1.getAttributes(), a2.getAttributes());
		}

		@Override
		public int hash(final Attribute a) {
			return Objects.hash(a.getTag(), Arrays.hashCode(a.getAttributes()));
		}
	};
	private final Equator<Protocol> protocolEquator = new Equator<>() {
		@Override
		public boolean equate(final Protocol p1, final Protocol p2) {
			return Objects.equals(p1.getTag(), p2.getTag()) && Objects.equals(p1.getAttribute(), p2.getAttribute()) && Arrays.equals(p1.getProtocols(), p2.getProtocols());
		}

		@Override
		public int hash(final Protocol p) {
			return Objects.hash(p.getTag(), p.getAttribute(), Arrays.hashCode(p.getProtocols()));
		}
	};

	@Test
	void testBuilder() {
		final SafeHtmlValidatorDefinition definition = super.assertValidator(new SafeHtmlValidatorDefinitionBuilder(), (name, builder) -> builder.build(name))
				.allowedTag("tag1")
				.allowedTag("tag2")
				.globallyAllowedAttribute("globalAttribute1")
				.globallyAllowedAttribute("globalAttribute2")
				.allowedProtocol("protocol1", "attribute", "p1", "p2")
				.allowedProtocol(new ProtocolBuilder().protocol("p1").build("protocol2", "attribute"))
				.allowedAttribute("attribute1", "a1", "a2")
				.allowedAttribute(new AttributeBuilder().attribute("a1").build("attribute2"))
				.build("someSafeHtmlValidator");
		assertEquals("someSafeHtmlValidator", definition.getName());
		assertEquals(List.of("tag1", "tag2"), definition.getAllowedTags());
		assertEquals(List.of("globalAttribute1", "globalAttribute2"), definition.getGloballyAllowedAttributes());


		assertTrue(
				CollectionUtils.isEqualCollection(
						List.of(
								new ProtocolBuilder().protocol("p1").protocol("p2").build("protocol1", "attribute"),
								new ProtocolBuilder().protocol("p1").build("protocol2", "attribute")
						),
						definition.getAllowedProtocols(),
						protocolEquator
				)
		);
		assertTrue(
				CollectionUtils.isEqualCollection(
						List.of(
								new AttributeBuilder().attribute("a1").attribute("a2").build("attribute1"),
								new AttributeBuilder().attribute("a1").build("attribute2")
						),
						definition.getAllowedAttributes(),
						attributeEquator
				)
		);

		final SafeHtmlValidatorDefinition emptyDefinition = new SafeHtmlValidatorDefinitionBuilder().build();
		assertEquals("safeHtmlValidator", emptyDefinition.getName());
		assertTrue(emptyDefinition.getAllowedTags().isEmpty());
		assertTrue(emptyDefinition.getGloballyAllowedAttributes().isEmpty());
		assertTrue(emptyDefinition.getAllowedProtocols().isEmpty());
		assertTrue(emptyDefinition.getAllowedAttributes().isEmpty());
	}
}
