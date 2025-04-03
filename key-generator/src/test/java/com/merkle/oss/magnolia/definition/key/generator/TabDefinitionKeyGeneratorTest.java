package com.merkle.oss.magnolia.definition.key.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.field.CompositeFieldDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.field.JcrMultiFieldDefinition;
import info.magnolia.ui.field.RegexpValidatorDefinition;
import info.magnolia.ui.field.TextFieldDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinitionKeyGenerator;
import info.magnolia.ui.framework.layout.ConfiguredTabDefinition;
import info.magnolia.ui.framework.layout.TabDefinition;
import info.magnolia.ui.framework.layout.TabbedLayoutDefinition;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabDefinitionKeyGeneratorTest extends AbstractKeyGeneratorTest {
    private TabDefinitionKeyGenerator keyGenerator;

    @BeforeEach
    void setUp() {
        keyGenerator = new TabDefinitionKeyGenerator(
                new KeyGeneratorUtil("FallbackDialog", "FallbackApp", "^idPrefix", Set.of(ConfiguredFormDefinition.class))
        );
    }

    @Test
    void keysFor_isMagnoliaModule() throws NoSuchMethodException {
        final ConfiguredTabDefinition tab = new ConfiguredTabDefinition();
        tab.setName("SomeTab");
        final TabDefinition decoratedTab = i18nIfy("someMagnoliaModuleIdPrefix:dialogs/SomeDialog", tab);
        assertEquals(
                List.of(
                        "someMagnoliaModuleIdPrefix.dialogs.SomeDialog.tabs.SomeTab.label",
                        "someMagnoliaModuleIdPrefix.dialogs.SomeDialog.tabs.SomeTab",
                        "tabs.SomeTab.label",
                        "tabs.SomeTab"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTab, TabDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_dialog() throws NoSuchMethodException {
        final ConfiguredTabDefinition tab = new ConfiguredTabDefinition();
        tab.setName("SomeTab");
        final TabDefinition decoratedTab = i18nIfy("idPrefix:dialogs/SomeDialog", tab);
        assertEquals(
                List.of(
                        "SomeDialog.tab.SomeTab.label",
                        "SomeDialog.tab.SomeTab",
                        "tabs.SomeTab.label",
                        "tabs.SomeTab"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTab, TabDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_app() throws NoSuchMethodException {
        final ConfiguredTabDefinition tab = new ConfiguredTabDefinition();
        tab.setName("SomeTab");
        final TabDefinition decoratedTab = i18nIfy("idPrefix:apps/SomeApp", "SomeApp", "SomeSubApp", tab);
        assertEquals(
                List.of(
                        "SomeApp.tab.SomeSubApp.SomeTab.label",
                        "SomeApp.tab.SomeSubApp.SomeTab",
                        "SomeApp.tab.SomeTab.label",
                        "SomeApp.tab.SomeTab",
                        "tabs.SomeTab.label",
                        "tabs.SomeTab"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTab, TabDefinition.class.getMethod("getLabel")))
        );
    }
}
