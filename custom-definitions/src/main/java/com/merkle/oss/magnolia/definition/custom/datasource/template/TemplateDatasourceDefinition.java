package com.merkle.oss.magnolia.definition.custom.datasource.template;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceType;

@DatasourceType("available-filtering-page-templatesDatasource")
public class TemplateDatasourceDefinition extends BaseDatasourceDefinition {
	@Override
	public String getName() {
		return "available-filtering-page-templates";
	}
}
