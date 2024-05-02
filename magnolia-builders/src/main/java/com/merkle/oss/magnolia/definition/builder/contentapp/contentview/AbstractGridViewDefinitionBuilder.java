package com.merkle.oss.magnolia.definition.builder.contentapp.contentview;

import info.magnolia.ui.contentapp.browser.drop.DropConstraintDefinition;
import info.magnolia.ui.contentapp.configuration.GridViewDefinition;
import info.magnolia.ui.contentapp.configuration.column.ColumnDefinition;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

public class AbstractGridViewDefinitionBuilder<T, D extends GridViewDefinition<T>, B extends AbstractGridViewDefinitionBuilder<T, D, B>> extends AbstractContentViewDefinitionBuilder<T, D, B> {
    @Nullable
    protected List<ColumnDefinition<T>> columns;
    @Nullable
    protected DropConstraintDefinition dropConstraint;
    @Nullable
    protected Boolean multiSelectByRowClick;

    public AbstractGridViewDefinitionBuilder() {}
    public AbstractGridViewDefinitionBuilder(final D definition) {
        super(definition);
        Optional.ofNullable(definition.getColumns()).ifPresent(this::columns);
        Optional.ofNullable(definition.getDropConstraint()).ifPresent(this::dropConstraint);
        multiSelectByRowClick(definition.isMultiSelectByRowClick());
    }

    public B columns(final List<ColumnDefinition<T>> columns) {
        this.columns = columns;
        return self();
    }

    public B dropConstraint(final DropConstraintDefinition dropConstraint) {
        this.dropConstraint = dropConstraint;
        return self();
    }

    public B multiSelectByRowClick(final boolean multiSelectByRowClick) {
        this.multiSelectByRowClick = multiSelectByRowClick;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }
}
