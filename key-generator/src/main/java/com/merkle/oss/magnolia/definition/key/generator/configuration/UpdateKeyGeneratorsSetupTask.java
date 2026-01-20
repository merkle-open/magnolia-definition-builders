package com.merkle.oss.magnolia.definition.key.generator.configuration;

import info.magnolia.module.InstallContext;
import info.magnolia.module.delta.AbstractTask;
import info.magnolia.module.delta.TaskExecutionException;
import info.magnolia.ui.contentapp.configuration.column.ColumnDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;
import info.magnolia.ui.form.field.definition.FieldDefinition;
import info.magnolia.ui.framework.layout.TabDefinition;

import com.merkle.oss.magnolia.definition.key.generator.ColumnDefinitionKeyGenerator;
import com.merkle.oss.magnolia.definition.key.generator.EditorPropertyDefinitionKeyGenerator;
import com.merkle.oss.magnolia.definition.key.generator.FieldValidatorDefinitionKeyGenerator;
import com.merkle.oss.magnolia.definition.key.generator.KeyGeneratorUpdater;
import com.merkle.oss.magnolia.definition.key.generator.TabDefinitionKeyGenerator;

import jakarta.inject.Inject;

public class UpdateKeyGeneratorsSetupTask extends AbstractTask {
	private static final String TASK_NAME = "Update keyGenerators Task";
	private static final String TASK_DESCRIPTION = "This task updates the key generators in ByteBuddyI18nizer (I18nKeyGeneratorFactory).";
	private static final String PATH = "/modules/rendering/renderers";
    private final KeyGeneratorUpdater keyGeneratorUpdater;

	@Inject
    public UpdateKeyGeneratorsSetupTask(final KeyGeneratorUpdater keyGeneratorUpdater) {
		super(TASK_NAME, TASK_DESCRIPTION);
        this.keyGeneratorUpdater = keyGeneratorUpdater;
    }

	@Override
	public void execute(InstallContext installContext) throws TaskExecutionException {
		try {
			keyGeneratorUpdater.update(FieldDefinition.class, EditorPropertyDefinitionKeyGenerator.class);
			keyGeneratorUpdater.update(EditorPropertyDefinition.class, EditorPropertyDefinitionKeyGenerator.class);
			keyGeneratorUpdater.update(FieldValidatorDefinition.class, FieldValidatorDefinitionKeyGenerator.class);
			keyGeneratorUpdater.update(TabDefinition.class, TabDefinitionKeyGenerator.class);
			keyGeneratorUpdater.update(ColumnDefinition.class, ColumnDefinitionKeyGenerator.class);
		} catch (Exception e) {
			throw new TaskExecutionException(e.getMessage(), e);
		}
	}
}
