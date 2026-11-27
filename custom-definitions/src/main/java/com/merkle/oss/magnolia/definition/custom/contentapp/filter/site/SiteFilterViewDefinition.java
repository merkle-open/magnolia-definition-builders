package com.merkle.oss.magnolia.definition.custom.contentapp.filter.site;

import info.magnolia.icons.MagnoliaIcons;
import info.magnolia.ui.filter.FilterOperator;
import info.magnolia.ui.filteringapp.filter.field.FilterDropdownMultiselectViewDefinition;

import com.merkle.oss.magnolia.definition.custom.datasource.site.SiteDatasourceDefinition;

public class SiteFilterViewDefinition<T> extends FilterDropdownMultiselectViewDefinition<T> {
	public SiteFilterViewDefinition() {
		setName("site");
		setLabel("merkle.customDefinitions.contentapp.filter.site.label");
		setIcon(MagnoliaIcons.SITEMAPS.getCssClass());
		setFilterOperator(FilterOperator.STARTS_WITH);
		setPropertyName("jcrPath");
		final SiteDatasourceDefinition siteDatasourceDefinition = new SiteDatasourceDefinition();
		siteDatasourceDefinition.setHandlePrefixValue(true);
		setDatasource(siteDatasourceDefinition);
	}
}
