package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.dam.api.Item;
import info.magnolia.dam.app.data.AssetDatasourceDefinition;
import info.magnolia.dam.app.field.DamLinkFieldDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

import javax.annotation.Nullable;

/**
 * builds a {@link LinkFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Link-field.html">magnolia Docs - Link field </a>
 * @author Merkle DACH
 */
public class AssetLinkDefinitionBuilder extends AbstractLinkFieldDefinitionBuilder<Item, DamLinkFieldDefinition, AssetLinkDefinitionBuilder> {

	@Nullable
	private String rootPath;

	public AssetLinkDefinitionBuilder() {}
	public AssetLinkDefinitionBuilder(final DamLinkFieldDefinition definition) {
		super(definition);
	}

	public DamLinkFieldDefinition build(final String name) {
		final DamLinkFieldDefinition definition = new DamLinkFieldDefinition();
		final AssetDatasourceDefinition datasource = (AssetDatasourceDefinition)definition.getDatasource();
		datasource.setRootPath(rootPath);
		super.populate(definition, name, datasource);
		return definition;
	}

	public AssetLinkDefinitionBuilder rootPath(final String rootPath) {
		this.rootPath = rootPath;
		return self();
	}
}
