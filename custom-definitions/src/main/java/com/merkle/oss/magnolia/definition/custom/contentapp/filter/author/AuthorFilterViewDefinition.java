package com.merkle.oss.magnolia.definition.custom.contentapp.filter.author;

import info.magnolia.icons.MagnoliaIcons;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.ui.filter.FilterOperator;
import info.magnolia.ui.filteringapp.filter.field.FilterDropdownMultiselectViewDefinition;

import com.merkle.oss.magnolia.definition.custom.datasource.user.FilteringUsersDatasourceDefinition;

public class AuthorFilterViewDefinition<T> extends FilterDropdownMultiselectViewDefinition<T> {
    public AuthorFilterViewDefinition() {
        setName("template");
        setLabel("merkle.customDefinitions.contentapp.filter.author.label");
        setIcon(MagnoliaIcons.USER_PUBLIC.getCssClass());
        setFilterOperator(FilterOperator.EQUALS);
        setPropertyName(NodeTypes.Created.CREATED_BY);
        setDatasource(new FilteringUsersDatasourceDefinition());
    }
}
