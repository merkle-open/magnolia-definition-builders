package com.merkle.oss.magnolia.definition.builder.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.field.ComboBoxFieldDefinition;
import info.magnolia.ui.field.TokenFieldDefinition;

import java.util.Collection;
import java.util.Collections;

import javax.jcr.Node;

import org.junit.jupiter.api.Test;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;

class TokenFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {
    @Test
    void testBuilder() {
        final ComboBoxFieldDefinition<Node> comboBoxField = new ComboBoxFieldDefinitionBuilder<Node>().build("comboBox", new BaseDatasourceDefinition());
        final TokenFieldDefinition<Node> definition = super.assertField(new TokenFieldDefinitionBuilder<Node>(), (name, builder) -> builder.build(name), Collections.emptySet())
                .comboBox(comboBoxField)
                .build("token");
        assertEquals(comboBoxField, definition.getComboBox());

        final TokenFieldDefinition<Node> emptyDefinition = new TokenFieldDefinitionBuilder<Node>().build("token");
        assertEquals(Collection.class, emptyDefinition.getType());
    }
}