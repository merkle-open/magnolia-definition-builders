package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.dam.api.Item;
import info.magnolia.dam.app.field.DamLinkFieldDefinition;
import info.magnolia.ui.field.LinkFieldDefinition;

/**
 * builds a {@link LinkFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/Developing/Templating/Dialog-definition/Field-definition/List-of-fields/Link-field.html">magnolia Docs - Link field </a>
 * @author Merkle DACH
 */
public class AssetLinkDefinitionBuilder extends AbstractLinkFieldDefinitionBuilder<Item, DamLinkFieldDefinition, AssetLinkDefinitionBuilder> {

	public AssetLinkDefinitionBuilder() {
		super(DamLinkFieldDefinition::new);
	}

	public DamLinkFieldDefinition build(final String name) {
		return super.build(name, new DamLinkFieldDefinition().getDatasource());
	}
}
