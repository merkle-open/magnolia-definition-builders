package com.merkle.oss.magnolia.definition.key.generator.configuration;

import info.magnolia.module.DefaultModuleVersionHandler;
import info.magnolia.module.InstallContext;
import info.magnolia.module.delta.Task;

import java.util.List;

import jakarta.inject.Inject;

public class KeyGeneratorModuleVersionHandler extends DefaultModuleVersionHandler {
	private final UpdateKeyGeneratorsSetupTask updateKeyGeneratorsSetupTask;

	@Inject
	public KeyGeneratorModuleVersionHandler(final UpdateKeyGeneratorsSetupTask updateKeyGeneratorsSetupTask) {
		this.updateKeyGeneratorsSetupTask = updateKeyGeneratorsSetupTask;
	}

	@Override
	protected List<Task> getStartupTasks(final InstallContext installContext) {
		return List.of(updateKeyGeneratorsSetupTask);
	}
}
