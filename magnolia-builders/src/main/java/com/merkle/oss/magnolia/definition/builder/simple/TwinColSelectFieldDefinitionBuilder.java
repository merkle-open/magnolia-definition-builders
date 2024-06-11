package com.merkle.oss.magnolia.definition.builder.simple;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionListDefinitionBuilder;
import info.magnolia.ui.datasource.DatasourceDefinition;
import info.magnolia.ui.field.TwinColSelectFieldDefinition;

import javax.annotation.Nullable;
import java.util.Optional;

public class TwinColSelectFieldDefinitionBuilder<T> extends AbstractMultiselectFieldDefinitionBuilder<T, DatasourceDefinition, TwinColSelectFieldDefinition<T>, TwinColSelectFieldDefinitionBuilder<T>> {
	@Nullable
	private String leftColumnCaption;
	@Nullable
	private String rightColumnCaption;

	public TwinColSelectFieldDefinitionBuilder() {}
	public TwinColSelectFieldDefinitionBuilder(final TwinColSelectFieldDefinition<T> definition) {
		super(definition);
		leftColumnCaption(definition.getLeftColumnCaption());
		rightColumnCaption(definition.getRightColumnCaption());
	}

	public TwinColSelectFieldDefinitionBuilder<T> leftColumnCaption(final String leftColumnCaption) {
		this.leftColumnCaption = leftColumnCaption;
		return self();
	}

	public TwinColSelectFieldDefinitionBuilder<T> rightColumnCaption(final String rightColumnCaption) {
		this.rightColumnCaption = rightColumnCaption;
		return self();
	}

	public <O extends OptionListDefinitionBuilder.OptionEnum> TwinColSelectFieldDefinition<T> build(final String name, final Class<O> optionsClass, final O... excludeOptions) {
		return build(name, new OptionListDefinitionBuilder().options(optionsClass, excludeOptions).build());
	}

	public TwinColSelectFieldDefinition<T> build(final String name, final DatasourceDefinition datasourceDefinition) {
		final TwinColSelectFieldDefinition<T> definition = new TwinColSelectFieldDefinition<>();
		super.populate(definition, name, datasourceDefinition);
		Optional.ofNullable(leftColumnCaption).ifPresent(definition::setLeftColumnCaption);
		Optional.ofNullable(rightColumnCaption).ifPresent(definition::setRightColumnCaption);
		return definition;
	}
}
