package com.merkle.oss.magnolia.definition.custom.datasource.user;

import info.magnolia.ui.datasource.BaseDatasourceDefinition;
import info.magnolia.ui.datasource.DatasourceType;

@DatasourceType("available-filtering-usersDatasource")
public class FilteringUsersDatasourceDefinition extends BaseDatasourceDefinition {
	@Override
	public String getName() {
		return "available-filtering-users";
	}
}
