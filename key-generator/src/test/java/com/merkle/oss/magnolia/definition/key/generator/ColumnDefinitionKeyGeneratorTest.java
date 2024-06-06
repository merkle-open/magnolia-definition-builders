package com.merkle.oss.magnolia.definition.key.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import info.magnolia.context.Context;
import info.magnolia.i18nsystem.bytebudddy.ByteBuddyI18nizer;
import info.magnolia.ui.chooser.definition.WorkbenchChooserDefinition;
import info.magnolia.ui.contentapp.configuration.ListViewDefinition;
import info.magnolia.ui.contentapp.configuration.WorkbenchDefinition;
import info.magnolia.ui.contentapp.configuration.column.ColumnDefinition;
import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ColumnDefinitionKeyGeneratorTest extends AbstractKeyGeneratorTest {
    private ColumnDefinitionKeyGenerator keyGenerator;

    @BeforeEach
    void setUp() {
        keyGenerator = new ColumnDefinitionKeyGenerator();
    }

    @Test
    <T> void keysFor() throws NoSuchMethodException {
        ConfiguredColumnDefinition<T> column = new ConfiguredColumnDefinition<>();
        column.setName("someColumn");
        final ConfiguredColumnDefinition<?> columnDefinition = i18nIfy("idPrefix:dialogs/SomeDialog", List.of(column), column);

        assertEquals(
                List.of(
                        "workbenchchooser.views.someColumn.label",
                        "workbenchchooser.views.someColumn",
                        "views.someColumn.label",
                        "views.someColumn",
                        "idPrefix.dialogs.SomeDialog.views.someColumn.label",
                        "idPrefix.dialogs.SomeDialog.views.someColumn"
                ),
                List.of(keyGenerator.keysFor((String)null, columnDefinition, ColumnDefinition.class.getMethod("getLabel")))
        );
    }

    private <T, D extends ColumnDefinition<T>> D i18nIfy(final String dialogId, final List<ColumnDefinition<T>> columns, final D definition) {
        final ListViewDefinition<T> listView = new ListViewDefinition<>();
        listView.setColumns(columns);
        final WorkbenchDefinition<T> workbench = new WorkbenchDefinition<>();
        workbench.setContentViews(List.of(listView));
        final WorkbenchChooserDefinition<T> dialog = new WorkbenchChooserDefinition<>();
        dialog.setWorkbench(workbench);
        dialog.setId(dialogId);
        final WorkbenchChooserDefinition<T> decoratedDialog = new ByteBuddyI18nizer(new TranslationServiceMock(), () -> Mockito.mock(Context.class)).decorate(dialog);

        return decoratedDialog.getWorkbench().getContentViews().stream()
                .filter(ListViewDefinition.class::isInstance)
                .map(viewDefinition -> (ListViewDefinition<T>)viewDefinition)
                .map(ListViewDefinition::getColumns)
                .flatMap(Collection::stream)
                .filter(column -> Objects.equals(column.getName(), definition.getName()))
                .findFirst()
                .map(d -> (D)d)
                .orElseThrow();
    }
}