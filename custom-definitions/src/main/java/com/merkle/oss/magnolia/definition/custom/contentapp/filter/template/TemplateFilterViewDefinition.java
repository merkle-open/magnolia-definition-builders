package com.merkle.oss.magnolia.definition.custom.contentapp.filter.template;

import info.magnolia.icons.MagnoliaIcons;
import info.magnolia.ui.filteringapp.filter.field.FilterDropdownMultiselectViewDefinition;

import com.merkle.oss.magnolia.definition.custom.datasource.template.TemplateDatasourceDefinition;

public class TemplateFilterViewDefinition<T> extends FilterDropdownMultiselectViewDefinition<T> {
    public TemplateFilterViewDefinition() {
        setName("template");
        setLabel("merkle.customDefinitions.contentapp.filter.template.label");
        setIcon(MagnoliaIcons.VIEW.getCssClass());
        setDatasource(new TemplateDatasourceDefinition());
    }
}
