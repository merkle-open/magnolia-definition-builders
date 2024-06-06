package com.merkle.oss.magnolia.definition.custom.linkset;

import com.google.common.collect.ImmutableList;
import com.merkle.oss.magnolia.definition.custom.switchable.SwitchableForm;
import info.magnolia.ui.editor.CurrentItemProviderDefinition;
import info.magnolia.ui.editor.FormView;
import info.magnolia.ui.field.*;
import info.magnolia.ui.framework.layout.FieldLayoutDefinition;
import info.magnolia.ui.framework.layout.StackedLayoutProducer;

import javax.annotation.Nullable;
import javax.jcr.Node;
import java.util.List;
import java.util.Optional;

public class BasicLinkSetDefinition<L extends ConfiguredFieldDefinition<?>> extends ConfiguredComplexPropertyDefinition<Node> implements SwitchableForm {
	private final L link;
	private final boolean singleTree;
	@Nullable
	private TextFieldDefinition anchorId;
	@Nullable
	private TextFieldDefinition linkText;
	@Nullable
	private CheckBoxFieldDefinition openInNewWindow;

	public BasicLinkSetDefinition(final L link, final boolean singleTree) {
		this.link = link;
        this.singleTree = singleTree;
        setImplementationClass((Class) FormView.class);
		setItemProvider(new CurrentItemProviderDefinition<>());
	}

	public L getLink() {
		return link;
	}

	public Optional<TextFieldDefinition> getAnchorId() {
		return Optional.ofNullable(anchorId);
	}

	public void setAnchorId(@Nullable final TextFieldDefinition anchorId) {
		this.anchorId = anchorId;
	}

	public Optional<TextFieldDefinition> getLinkText() {
		return Optional.ofNullable(linkText);
	}

	public void setLinkText(@Nullable final TextFieldDefinition linkText) {
		this.linkText = linkText;
	}

	public Optional<CheckBoxFieldDefinition> getOpenInNewWindow() {
		return Optional.ofNullable(openInNewWindow);
	}

	public void setOpenInNewWindow(@Nullable final CheckBoxFieldDefinition openInNewWindow) {
		this.openInNewWindow = openInNewWindow;
	}

	@Override
	public List<EditorPropertyDefinition> getProperties() {
		final ImmutableList.Builder<EditorPropertyDefinition> propertiesBuilder = ImmutableList.builder();
		propertiesBuilder.add(link);
		getAnchorId().ifPresent(propertiesBuilder::add);
		getLinkText().ifPresent(propertiesBuilder::add);
		getOpenInNewWindow().ifPresent(propertiesBuilder::add);
		return propertiesBuilder.build();
	}

	@Override
	public FieldLayoutDefinition<?> getLayout() {
		return new StackedLayoutProducer.Definition();
	}

	@Override
	public void setI18n(final boolean i18n) {
		super.setI18n(i18n);
		if(!singleTree) {
			link.setI18n(i18n);
			getAnchorId().ifPresent(definition ->
					definition.setI18n(i18n)
			);
		}
		getLinkText().ifPresent(definition ->
				definition.setI18n(i18n)
		);
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		link.setReadOnly(readOnly);
		getAnchorId().ifPresent(definition ->
				definition.setReadOnly(readOnly)
		);
		getLinkText().ifPresent(definition ->
				definition.setReadOnly(readOnly)
		);
	}

	@Override
	public boolean isReadOnly() {
		return link.isReadOnly();
	}

	@Override
	public void setRequired(final boolean required) {
		link.setRequired(required);
	}

	@Override
	public boolean isRequired() {
		return link.isRequired();
	}

	@Override
	public void setValidators(final List<FieldValidatorDefinition> validator){
		link.setValidators(validator);
	}

	@Override
	public List<FieldValidatorDefinition> getValidators() {
		return link.getValidators();
	}
}
