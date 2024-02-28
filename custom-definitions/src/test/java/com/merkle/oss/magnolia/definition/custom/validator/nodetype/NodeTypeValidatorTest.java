package com.merkle.oss.magnolia.definition.custom.validator.nodetype;

import com.vaadin.data.ValueContext;
import info.magnolia.test.mock.jcr.MockNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.jcr.Node;

class NodeTypeValidatorTest {
	private NodeTypeValidator validator;

	@BeforeEach
	public void setUp() {
		validator = new NodeTypeValidator("mgnl:page", "error");
	}

	@Test
	void apply_equalsNodeType_shouldReturnOk() {
		assertFalse(validator.apply(mockNode("mgnl:page"), new ValueContext()).isError());
	}

	@Test
	void apply_differentNodeType_shouldReturnError() {
		assertTrue(validator.apply(mockNode("mgnl:component"), new ValueContext()).isError());
		assertEquals("error", validator.apply(mockNode("mgnl:component"), new ValueContext()).getErrorMessage());
	}

	private Node mockNode(final String nodeType) {
		return new MockNode("someNode", nodeType);
	}
}