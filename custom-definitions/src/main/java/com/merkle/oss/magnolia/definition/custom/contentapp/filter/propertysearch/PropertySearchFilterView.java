package com.merkle.oss.magnolia.definition.custom.contentapp.filter.propertysearch;

import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.icons.MagnoliaIcons;
import info.magnolia.ui.filter.DataFilter;
import info.magnolia.ui.filter.FilterContext;
import info.magnolia.ui.filteringapp.filter.FilterView;
import info.magnolia.ui.theme.ResurfaceTheme;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import jakarta.inject.Inject;

public class PropertySearchFilterView extends VerticalLayout implements FilterView {
	private final PropertySearchFilterViewDefinition definition;
	private final FilterContext filterContext;
	private final SimpleTranslator i18n;

	private TextField searchField;

	@Inject
	public PropertySearchFilterView(final PropertySearchFilterViewDefinition definition, final FilterContext filterContext, final SimpleTranslator i18n) {
		this.definition = definition;
		this.filterContext = filterContext;
		this.i18n = i18n;
		setStyleName("full-text-search");
		setMargin(false);
		setSpacing(false);
		init();
	}

	protected void init() {
		searchField = new TextField();
		searchField.addStyleName("full-text-search-field");
		searchField.setPlaceholder(definition.getPlaceholder());

		final Button cleanText = new Button(MagnoliaIcons.CLOSE);
		cleanText.addStyleNames("close-button", "clear-button");
		cleanText.setVisible(false);
		cleanText.addClickListener((Button.ClickListener) event -> searchField.clear());

		final Label validationLabel = new Label();
		validationLabel.addStyleName("validation-label");
		validationLabel.setValue(i18n.translate("filters.fullTextSearch.validation.notReachLength"));
		validationLabel.setVisible(false);

		searchField.addValueChangeListener((HasValue.ValueChangeListener<String>) event -> {
			final boolean notReachLimitLength = StringUtils.isNotEmpty(event.getValue()) && event.getValue().length() < definition.getMinInputLength();
			if (!notReachLimitLength) {
				bindFilterContext(event.getValue());
			}
			validationLabel.setVisible(notReachLimitLength);
			setStyleName(ERROR_CSS, notReachLimitLength);
			setStyleName(HAS_VALUE_CSS, StringUtils.isNotEmpty(event.getValue()));
			cleanText.setVisible(StringUtils.isNotEmpty(event.getValue()));
		});

		final Button searchButton = new Button(MagnoliaIcons.SEARCH);
		searchButton.addStyleName(ResurfaceTheme.BUTTON_ICON);
		searchButton.addClickListener(event -> searchField.focus());

		final HorizontalLayout fieldLayout = new HorizontalLayout();
		fieldLayout.addStyleName("full-text-search-inner");
		fieldLayout.setSpacing(false);
		fieldLayout.setMargin(false);
		fieldLayout.addComponents(searchButton, searchField, cleanText);

		addComponents(fieldLayout, validationLabel);
	}

	protected void bindFilterContext(final Object selectedValue) {
		final DataFilter dataFilter = filterContext.getFilter().value().orElse(new DataFilter());
		if (ObjectUtils.isEmpty(selectedValue)) {
			dataFilter.getPropertyFilters().remove(definition.getPropertyName());
		} else {
			dataFilter.filter(definition.getPropertyName(), definition.getFilterOperator(), selectedValue);
		}
		filterContext.getFilter().set(dataFilter, true);
	}

	@Override
	public void reset() {
		searchField.clear();
	}

	@Override
	public void focus() {
		searchField.focus();
	}
}
