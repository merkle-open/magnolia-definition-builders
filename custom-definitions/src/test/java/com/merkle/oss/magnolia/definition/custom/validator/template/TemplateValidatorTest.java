package com.merkle.oss.magnolia.definition.custom.validator.template;

import com.vaadin.data.ValueContext;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.test.mock.jcr.MockNode;
import org.apache.jackrabbit.value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.jcr.Node;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TemplateValidatorTest {
	private TemplateValidator validator;

	@BeforeEach
	public void setUp() {
		validator = new TemplateValidator("someTemplate", "error");
	}

	@Test
	void apply_equalsTemplate_shouldReturnOk() {
		assertFalse(validator.apply(mockNode("someTemplate"), new ValueContext()).isError());
	}

	@Test
	void apply_differentTemplate_shouldReturnError() {
		assertTrue(validator.apply(mockNode("someOtherTemplate"), new ValueContext()).isError());
		assertEquals("error", validator.apply(mockNode("someOtherTemplate"), new ValueContext()).getErrorMessage());
	}

	private static Node mockNode(final String template) {
		return new MockNode("someNode", Map.of(
				NodeTypes.Renderable.TEMPLATE, new StringValue(template)
		), Collections.emptyMap());
	}
}