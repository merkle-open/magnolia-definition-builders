package com.merkle.oss.magnolia.definition.custom.qrcode;

import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.ValueContext;
import info.magnolia.ui.field.factory.AbstractFieldFactory;

import javax.inject.Inject;
import javax.jcr.Node;

import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.vaadin.ui.Component;

public class QrCodeFieldFactory extends AbstractFieldFactory<String, QrCodeFieldDefinition> {
	private final QrCodeFieldDefinition definition;
	private final PowerNodeService powerNodeService;
	private final ValueContext<Node> valueContext;

	@Inject
	public QrCodeFieldFactory(
		final QrCodeFieldDefinition definition,
		final ComponentProvider componentProvider,
		final PowerNodeService powerNodeService,
		final ValueContext<Node> valueContext
	) {
		super(definition, componentProvider);
		this.definition = definition;
		this.powerNodeService = powerNodeService;
		this.valueContext = valueContext;
	}

	@Override
	protected Component createFieldComponent() {
		return new QrCodeField(definition, powerNodeService, valueContext);
	}
}
