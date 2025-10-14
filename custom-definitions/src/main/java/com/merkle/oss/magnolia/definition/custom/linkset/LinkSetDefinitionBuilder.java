package com.merkle.oss.magnolia.definition.custom.linkset;

import com.merkle.oss.magnolia.definition.builder.simple.AssetLinkDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.CheckBoxFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.InternalLinkDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.simple.TextFieldDefinitionBuilder;
import com.merkle.oss.magnolia.definition.builder.validator.RegexpValidatorDefinitionBuilder;
import com.merkle.oss.magnolia.definition.custom.switchable.FieldOption;
import info.magnolia.ui.field.CheckBoxFieldDefinition;
import info.magnolia.ui.field.TextFieldDefinition;

import javax.annotation.Nullable;
import java.util.function.UnaryOperator;

public class LinkSetDefinitionBuilder extends AbstractSwitchableLinkSetDefinitionBuilder<LinkSetDefinitionBuilder> {
	public static final UnaryOperator<String> ANCHOR_ID_PROPERTY_NAME_PROVIDER = name -> name + "_anchorId";
	public static final UnaryOperator<String> LINK_TEXT_PROPERTY_NAME_PROVIDER = name -> name + "_text";
	public static final UnaryOperator<String> OPEN_IN_NEW_TAB_PROPERTY_NAME_PROVIDER = name -> name + "_in_new_window";
	protected static final String LABEL_PREFIX = "merkle.customDefinitions.linkSet.";
	protected static final String FIELD_LABEL_PREFIX = LABEL_PREFIX + "field.";
	private final boolean singleTree;

	public LinkSetDefinitionBuilder() {
		this(false, true, false);
	}

	protected LinkSetDefinitionBuilder(final boolean singleTree, final boolean switchableFieldI18n, final boolean removePreviouslySelected) {
		super(LABEL_PREFIX, switchableFieldI18n, removePreviouslySelected);
		this.singleTree = singleTree;
	}


	@Override
	protected FieldOption<LinkType> createFieldOption(final LinkType linkType) {
		if(LinkTypes.INTERNAL.equals(linkType)) {
			return internal(linkType);
		}
		if(LinkTypes.EXTERNAL.equals(linkType)) {
			return external(linkType);
		}
		if(LinkTypes.ASSET_DAM.equals(linkType)) {
			return asset(linkType);
		}
		throw new IllegalArgumentException("Unsupported link type " + linkType);
	}

	protected FieldOption<LinkType> internal(final LinkType linkType) {
		return new FieldOption<>(
				linkType,
				name -> new BasicLinkSetDefinitionBuilder<>()
						.singleTree(singleTree)
						.anchorId(anchor(name))
						.linkText(linkText(name))
						.openInNewWindow(openInNewTab(name))
						.label("")
						.build(name, new InternalLinkDefinitionBuilder().label(FIELD_LABEL_PREFIX + linkType.getLabel()).build(name))
		);
	}

	protected FieldOption<LinkType> external(final LinkType linkType) {
		return new FieldOption<>(
				linkType,
				name -> new BasicLinkSetDefinitionBuilder<>()
						.anchorId(anchor(name))
						.linkText(linkText(name))
						.openInNewWindow(openInNewTab(name))
						.label("")
						.build(name, new TextFieldDefinitionBuilder().label(FIELD_LABEL_PREFIX + linkType.getLabel()).build(name))
		);
	}

	protected FieldOption<LinkType> asset(final LinkType linkType) {
		return new FieldOption<>(
				linkType,
				name -> new BasicLinkSetDefinitionBuilder<>()
						.linkText(linkText(name))
						.openInNewWindow(openInNewTab(name))
						.label("")
						.build(name, new AssetLinkDefinitionBuilder().label(FIELD_LABEL_PREFIX + linkType.getLabel()).build(name))
		);
	}

	@Nullable
	protected CheckBoxFieldDefinition openInNewTab(final String name) {
		return new CheckBoxFieldDefinitionBuilder()
				.label(LABEL_PREFIX + "openInNewWindow.label")
				.buttonLabel(LABEL_PREFIX + "openInNewWindow.buttonLabel")
				.build(OPEN_IN_NEW_TAB_PROPERTY_NAME_PROVIDER.apply(name));
	}

	@Nullable
	protected TextFieldDefinition linkText(final String name) {
		return new TextFieldDefinitionBuilder().label(LABEL_PREFIX + "text.label").build(LINK_TEXT_PROPERTY_NAME_PROVIDER.apply(name));
	}

	@Nullable
	protected TextFieldDefinition anchor(final String name) {
		return new TextFieldDefinitionBuilder()
				.validator(new RegexpValidatorDefinitionBuilder()
						.pattern("^[a-zA-Z0-9]*$")
						.build("anchorPattern")
				)
				.label(LABEL_PREFIX + "anchorId.label")
				.build(ANCHOR_ID_PROPERTY_NAME_PROVIDER.apply(name));
	}
}
