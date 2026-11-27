package com.merkle.oss.magnolia.definition.custom.datasource.site;

import info.magnolia.module.site.Site;
import info.magnolia.module.site.SiteManager;
import info.magnolia.ui.datasource.DatasourceType;
import info.magnolia.ui.datasource.optionlist.Option;
import info.magnolia.ui.datasource.optionlist.OptionListDefinition;
import info.magnolia.ui.field.SelectFieldSupport;
import info.magnolia.ui.filter.DataFilter;

import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import com.merkle.oss.magnolia.definition.builder.datasource.OptionBuilder;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.ui.ItemCaptionGenerator;

import jakarta.inject.Inject;

@DatasourceType("siteDatasource")
public class SiteDatasourceDefinition extends OptionListDefinition {
	private boolean handlePrefixValue;

	@Override
	public String getName() {
		return "site";
	}

	public void setHandlePrefixValue(final boolean handlePrefixValue) {
		this.handlePrefixValue = handlePrefixValue;
	}
	public boolean isHandlePrefixValue() {
		return handlePrefixValue;
	}

	public static class SiteSelectFieldSupport implements SelectFieldSupport<Option> {
		private final SiteDatasourceDefinition datasourceDefinition;
		private final SiteManager siteManager;

		@Inject
		public SiteSelectFieldSupport(
			final SiteDatasourceDefinition datasourceDefinition,
			final SiteManager siteManager
		) {
			this.datasourceDefinition = datasourceDefinition;
			this.siteManager = siteManager;
		}

		@Override
		public DataProvider<Option, ?> getDataProvider() {
			return new SiteProvider(datasourceDefinition, siteManager);
		}

		@Override
		public ItemCaptionGenerator<Option> getItemCaptionGenerator() {
			return option -> StringUtils.defaultIfBlank(option.getLabel(), option.getName());
		}

		@Override
		public Converter<Option, String> defaultConverter() {
			return new Converter<>() {
				@Override
				public Result<String> convertToModel(final Option value, final ValueContext context) {
					return Result.ok(value.getValue());
				}
				@Override
				public Option convertToPresentation(final String value, final ValueContext context) {
					return getDataProvider().fetch(new Query<>())
						.filter(option -> Strings.CS.equals(option.getValue(), value))
						.findFirst()
						.orElse(null);
				}
			};
		}

		private static class SiteProvider extends AbstractDataProvider<Option, DataFilter> {
			private final SiteDatasourceDefinition datasourceDefinition;
			private final SiteManager siteManager;

			@Inject
			public SiteProvider(
				final SiteDatasourceDefinition datasourceDefinition,
				final SiteManager siteManager
			) {
				this.datasourceDefinition = datasourceDefinition;
				this.siteManager = siteManager;
			}

			@Override
			public boolean isInMemory() {
				return true;
			}

			@Override
			public int size(final Query<Option, DataFilter> query) {
				return (int) fetch(query).count();
			}

			@Override
			public Stream<Option> fetch(final Query<Option, DataFilter> query) {
				final Site defaultSite = siteManager.getDefaultSite();
				return siteManager.getSites().stream()
					.filter(site -> !Objects.equals(site.getName(), defaultSite.getName()))
					.map(site ->
						new OptionBuilder().label(site.getName()).build(site.getName(), getValue(site))
					);
			}

			private String getValue(final Site site) {
				if (datasourceDefinition.isHandlePrefixValue()) {
					return "/" + site.getName() + "/";
				}
				return site.getName();
			}
		}
	}
}
