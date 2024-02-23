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

	public LinkSetDefinitionBuilder() {
		super(LABEL_PREFIX);
	}

	@Override
	protected FieldOption<LinkType> createFieldOption(final LinkType linkType) {
		if(LinkTypes.INTERNAL.equals(linkType)) {
			return internal();
		}
		if(LinkTypes.EXTERNAL.equals(linkType)) {
			return external();
		}
		if(LinkTypes.ASSET_DAM.equals(linkType)) {
			return asset();
		}
		throw new IllegalArgumentException("Unsupported link type " + linkType);
	}

	protected FieldOption<LinkType> internal() {
		return new FieldOption<>(
				LinkTypes.INTERNAL,
				name -> new BasicLinkSetDefinitionBuilder<>()
						.anchorId(anchor(name))
						.linkText(linkText(name))
						.openInNewWindow(openInNewTab(name))
						.label("")
						.build(name, new InternalLinkDefinitionBuilder().label(LABEL_PREFIX + LinkTypes.INTERNAL.getLabel()).build(name))
		);
	}

	protected FieldOption<LinkType> external() {
		return new FieldOption<>(
				LinkTypes.EXTERNAL,
				name -> new BasicLinkSetDefinitionBuilder<>()
						.anchorId(anchor(name))
						.linkText(linkText(name))
						.openInNewWindow(openInNewTab(name))
						.label("")
						.build(name, new TextFieldDefinitionBuilder().label(LABEL_PREFIX + LinkTypes.EXTERNAL.getLabel()).build(name))
		);
	}

	protected FieldOption<LinkType> asset() {
		return new FieldOption<>(
				LinkTypes.ASSET_DAM,
				name -> new BasicLinkSetDefinitionBuilder<>()
						.linkText(linkText(name))
						.openInNewWindow(openInNewTab(name))
						.label("")
						.build(name, new AssetLinkDefinitionBuilder().label(LABEL_PREFIX + LinkTypes.ASSET_DAM.getLabel()).build(name))
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
