package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.ai.automations.config.ActionConfiguration;
import info.magnolia.ai.automations.ui.fields.AiRichTextFieldDefinition;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AiRichTextFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {
    @Test
    void testBuilder() {
        final CurrentItemProviderDefinition itemProvider = new CurrentItemProviderDefinition<>();
        final AiRichTextFieldDefinition definition = super.assertComplexField(new AiRichTextFieldDefinitionBuilder(), (name, builder) -> builder.build(name), itemProvider)
                .alignment(true)
                .images(true)
                .lists(false)
                .source(true)
                .tables(true)
                .height(42)
                .colors("someColors")
                .fonts("someFonts")
                .fontSizes("someFontSizes")
                .taskId("someTaskId")
                .action("someAction", new ActionConfiguration())
                .build("richText");

        Assertions.assertTrue(definition.isAlignment());
        Assertions.assertTrue(definition.isImages());
        assertFalse(definition.isLists());
        Assertions.assertTrue(definition.isSource());
        Assertions.assertTrue(definition.isTables());
        assertEquals("someColors", definition.getColors());
        assertEquals("someFonts", definition.getFonts());
        assertEquals("someFontSizes", definition.getFontSizes());
        assertEquals(42, definition.getHeight());
        assertEquals("someTaskId", definition.getTaskId());
        assertTrue(definition.getActions().containsKey("someAction"));

        final AiRichTextFieldDefinition emptyDefinition = new AiRichTextFieldDefinitionBuilder().build("richText");
        assertEquals(FormView.class, emptyDefinition.getImplementationClass());
        assertEquals(CurrentItemProviderDefinition.class, emptyDefinition.getItemProvider().getClass());
    }
}
