package com.merkle.oss.magnolia.definition.custom.switchable;

import info.magnolia.ui.editor.FormDefinition;
import info.magnolia.ui.field.EditorPropertyDefinition;
import info.magnolia.ui.field.FieldValidatorDefinition;

import javax.jcr.Node;
import java.util.List;

public interface SwitchableForm extends FormDefinition<Node> {
	void setI18n(boolean i18n);
	boolean isI18n();

	void setReadOnly(boolean readOnly);
	boolean isReadOnly();

	void setRequired(boolean required);
	boolean isRequired();

	void setValidators(List<FieldValidatorDefinition> validator);
	List<FieldValidatorDefinition> getValidators();

	void setAdditionalProperties(List<EditorPropertyDefinition> additionalProperties);
}
