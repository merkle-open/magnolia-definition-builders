package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;
import info.magnolia.dam.api.Item;
import info.magnolia.dam.app.data.AssetDatasourceDefinition;
import info.magnolia.dam.app.field.DamLinkFieldDefinition;
import info.magnolia.ui.contentapp.FilteringMode;
import info.magnolia.ui.field.LinkFieldBinder;
import info.magnolia.ui.field.LinkFieldDefinition;
import info.magnolia.ui.field.factory.LinkFieldFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssetLinkDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {

	@Test
	<T> void testBuilder() {
		super.assertLinkField(new AssetLinkDefinitionBuilder(), (name, builder) -> builder.build(name));

		final LinkFieldDefinition<Item>emptyDefinition = new AssetLinkDefinitionBuilder().build("comboBox");
		assertEquals("dam-app-core:chooser", emptyDefinition.getChooserId());
		assertTrue(emptyDefinition.isEditable());
		assertEquals(LinkFieldFactory.class, emptyDefinition.getFactoryClass());
		assertEquals(LinkFieldBinder.class, emptyDefinition.getFieldBinderClass());
		assertEquals(FilteringMode.CONTAINS, emptyDefinition.getFilteringMode());
		AssetDatasourceDefinition assetDatasource = (AssetDatasourceDefinition) new DamLinkFieldDefinition().getDatasource();
		AssetDatasourceDefinition testDatasource =  (AssetDatasourceDefinition)emptyDefinition.getDatasource();
		assertEquals(assetDatasource.getRootPath(), testDatasource.getRootPath());
		assertEquals(assetDatasource.getName(), testDatasource.getName());
		assertEquals(assetDatasource.getPreview().getVariationName(), testDatasource.getPreview().getVariationName());
	}
}
