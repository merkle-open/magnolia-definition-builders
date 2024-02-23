package com.merkle.oss.magnolia.definition.key.generator;

import info.magnolia.config.NamedDefinition;
import info.magnolia.objectfactory.Components;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinitionKeyGenerator;

import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class EditorPropertyDefinitionKeyGenerator extends info.magnolia.ui.editor.i18n.EditorPropertyDefinitionKeyGenerator {
	private final KeyGeneratorUtil keyGeneratorUtil;
	private final FieldDefinitionKeyGenerator fieldDefinitionKeyGenerator;

	public EditorPropertyDefinitionKeyGenerator() {
		// can't inject anything in keyGenerators -.- see I18nKeyGeneratorFactory
		this(Components.getComponent(KeyGeneratorUtil.class), new FieldDefinitionKeyGenerator());
	}

	public EditorPropertyDefinitionKeyGenerator(
			final KeyGeneratorUtil keyGeneratorUtil,
			final FieldDefinitionKeyGenerator fieldDefinitionKeyGenerator
	) {
		this.keyGeneratorUtil = keyGeneratorUtil;
		this.fieldDefinitionKeyGenerator = fieldDefinitionKeyGenerator;
	}

	@Override
	public String[] keysFor(final String undecoratedResult, final EditorPropertyDefinition definition, final AnnotatedElement el) {
		if(definition instanceof FieldDefinition) {
			return fieldDefinitionKeyGenerator.keysFor(undecoratedResult, definition, el);
		}
		return super.keysFor(undecoratedResult, definition, el);
	}

	@Override
	protected void keysFor(
			final List<String> list,
			final EditorPropertyDefinition definition,
			final AnnotatedElement el) {
		final String dialogName = keyGeneratorUtil.getDialogName(definition);

		if(keyGeneratorUtil.isMagnoliaModule(definition)) {
			super.keysFor(list, definition, el);
		} else {
			addKey(list, getKeys(dialogName, definition, el));
			addKey(list, getKeys(keyGeneratorUtil.getFallbackDialogName(), definition, el));
		}
	}

	private String[] getKeys(final String dialogName, final EditorPropertyDefinition definition, final AnnotatedElement el) {
		return Stream.of(
				Stream.of(dialogName, "field"),
				streamNames(definition),
				Stream.of(
						replaceColons(definition.getName()),
						fieldOrGetterName(el)
				)
		).flatMap(Function.identity()).sequential().toArray(String[]::new);
	}

	private Stream<String> streamNames(final Object definition) {
		final List<Object> ancestors = getAncestors(definition);
		Collections.reverse(ancestors);
		return ancestors.stream()
				.filter(ancestor ->
						keyGeneratorUtil.getExcludedAncestors().stream().noneMatch(excluded -> excluded.isInstance(ancestor))
				)
				.filter(NamedDefinition.class::isInstance)
				.map(NamedDefinition.class::cast)
				.map(NamedDefinition::getName)
				.distinct();
	}
}
