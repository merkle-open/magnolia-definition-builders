package com.merkle.oss.magnolia.definition.custom.contentapp.filter.status;

import info.magnolia.icons.MagnoliaIcons;
import info.magnolia.ui.contentapp.configuration.column.StatusColumnDefinition;
import info.magnolia.ui.datasource.enumeration.EnumDatasourceDefinition;
import info.magnolia.ui.filter.FilterOperator;
import info.magnolia.ui.filteringapp.filter.field.FilterDropdownMultiselectViewDefinition;

public class StatusFilterViewDefinition<T> extends FilterDropdownMultiselectViewDefinition<T> {
    public StatusFilterViewDefinition() {
        setName("status");
        setLabel("merkle.customDefinitions.contentapp.filter.status.label");
        setIcon(MagnoliaIcons.SPINNER_FULL.getCssClass());
        setFilterOperator(FilterOperator.EQUALS);
        setPropertyName("jcrPublishingStatus");
        final EnumDatasourceDefinition<StatusColumnDefinition.PublicationStatus> datasourceDefinition = new EnumDatasourceDefinition<>();
        datasourceDefinition.setEnumeration(StatusColumnDefinition.PublicationStatus.class);
        datasourceDefinition.setSorted(true);
        setDatasource(datasourceDefinition);
    }
}
