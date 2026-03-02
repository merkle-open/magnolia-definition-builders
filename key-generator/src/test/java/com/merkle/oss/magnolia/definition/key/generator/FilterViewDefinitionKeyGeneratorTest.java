package com.merkle.oss.magnolia.definition.key.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import info.magnolia.ui.contentapp.configuration.BrowserDescriptor;
import info.magnolia.ui.contentapp.configuration.WorkbenchDefinition;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.editor.ConfiguredFormDefinition;
import info.magnolia.ui.filteringapp.configuration.ConfiguredFilterViewDefinition;
import info.magnolia.ui.filteringapp.filter.FilterView;
import info.magnolia.ui.filteringapp.filter.FilterViewDefinition;
import info.magnolia.ui.filteringapp.filter.field.SearchViewDefinition;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterViewDefinitionKeyGeneratorTest extends AbstractKeyGeneratorTest {

    private FilterViewDefinitionKeyGenerator keyGenerator;

    @BeforeEach
    void setUp() {
        keyGenerator = new FilterViewDefinitionKeyGenerator(
                new KeyGeneratorUtil("FallbackDialog", "FallbackApp","^idPrefix", Set.of(ConfiguredFormDefinition.class))
        );
    }

    @Test
    void keysFor_isMagnoliaModule_label() throws NoSuchMethodException {
        final ConfiguredFilterViewDefinition<?> filterViewDefinition = new ConfiguredFilterViewDefinition<>();
        filterViewDefinition.setName("someFilter");
        final FilterViewDefinition<?> decoratedTextField = i18nIfy("someMagnoliaModuleIdPrefix:apps/SomeApp", filterViewDefinition);
        assertEquals(
                List.of(new info.magnolia.ui.filteringapp.filter.FilterViewDefinitionKeyGenerator().keysFor(null, decoratedTextField, ConfiguredFilterViewDefinition.class.getMethod("getLabel"))),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, ConfiguredFilterViewDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_dialog_label() throws NoSuchMethodException {
        final ConfiguredFilterViewDefinition<?> filterViewDefinition = new ConfiguredFilterViewDefinition<>();
        filterViewDefinition.setName("someFilter");
        final FilterViewDefinition<?> decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", filterViewDefinition);
        assertEquals(
                List.of(
                        "SomeApp.browser.workbench.filters.someFilter.label",
                        "SomeApp.browser.workbench.filters.someFilter",
                        "filters.someFilter.label",
                        "filters.someFilter"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, ConfiguredFilterViewDefinition.class.getMethod("getLabel")))
        );
    }

    @Test
    void keysFor_dialog_placeholder() throws NoSuchMethodException {
        final SearchViewDefinition filterViewDefinition = new SearchViewDefinition();
        filterViewDefinition.setName("someFilter");
        final FilterViewDefinition<?> decoratedTextField = i18nIfy("idPrefix:apps/SomeApp", filterViewDefinition);
        assertEquals(
                List.of(
                        "SomeApp.browser.workbench.filters.someFilter.placeholder",
                        "filters.someFilter.placeholder"
                ),
                List.of(keyGenerator.keysFor((String)null, decoratedTextField, SearchViewDefinition.class.getMethod("getPlaceholder")))
        );
    }

    private <T, V extends FilterView> FilterViewDefinition<V> i18nIfy(final String appId, final FilterViewDefinition<V> filterView) {
        final ContentAppDescriptorWithId contentApp = new ContentAppDescriptorWithId(appId);
        contentApp.setName("SomeApp");
        final BrowserDescriptor<T, DatasourceDefinition> browser = new BrowserDescriptor<>();
        browser.setName("browser");
        contentApp.setSubApps(Map.of(browser.getName(), browser));
        final WorkbenchDefinition<T> workbench = new WorkbenchDefinition<>();
        workbench.setFilters(List.of(filterView));
        browser.setWorkbench(workbench);
        final ContentAppDescriptorWithId decorateContentApp = byteBuddyI18nizer.decorate(contentApp);
        return (FilterViewDefinition<V>) ((BrowserDescriptor<T, DatasourceDefinition>) decorateContentApp.getSubApps().get(browser.getName())).getWorkbench().getFilters().get(0);
    }
}
