package com.merkle.oss.magnolia.definition.custom.validator.datedependency;

import info.magnolia.ui.ValueContext;
import info.magnolia.ui.editor.LocaleContext;
import info.magnolia.ui.field.AbstractFieldValidatorFactory;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Optional;

import jakarta.inject.Inject;
import javax.jcr.Item;
import javax.jcr.Node;

import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.PowerNodeService;
import com.merkle.oss.magnolia.powernode.ValueConverter;
import com.vaadin.data.Validator;

public class DateDependencyValidatorFactory extends AbstractFieldValidatorFactory<DateDependencyValidatorDefinition, Temporal> {
	private final PowerNodeService powerNodeService;
	private final ValueContext<Item> valueContext;
	private final LocaleContext localeContext;

	@Inject
	public DateDependencyValidatorFactory(
			final PowerNodeService powerNodeService,
			final ValueContext<Item> valueContext,
			final LocaleContext localeContext,
			final DateDependencyValidatorDefinition definition
	) {
		super(definition);
		this.powerNodeService = powerNodeService;
		this.valueContext = valueContext;
		this.localeContext = localeContext;
	}

	@Override
	public Validator<Temporal> createValidator() {
		valueContext.getSingle()
				.filter(Item::isNode)
				.map(Node.class::cast)
				.map(powerNodeService::convertToPowerNode)
				.ifPresent(this::updateState);
		return new DateDependencyValidator(definition, getI18nErrorMessage());
	}

	private void updateState(final PowerNode item) {
		definition.getState().getPropertyNameI18nMapping().forEach((propertyName, i18n) ->
				definition.getState().update(propertyName, get(item, propertyName, i18n).orElse(null))
		);
	}

	private Optional<LocalDateTime> get(final PowerNode item, final String propertyName, final boolean i18n) {
		if(i18n) {
			return item.getProperty(propertyName, localeContext.getCurrent(), ValueConverter::getLocalDateTime);
		}
		return item.getProperty(propertyName, ValueConverter::getLocalDateTime);
	}
}
