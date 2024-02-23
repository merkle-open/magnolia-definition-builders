package com.merkle.oss.magnolia.definition.custom.videoset;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import org.apache.commons.lang3.StringUtils;

public class VimeoTextValueConverter implements Converter<String, String> {
	public static final String PREFIX = "vimeo:";

	@Override
	public Result<String> convertToModel(final String value, final ValueContext context) {
		final String trimmed = StringUtils.trim(value);
		if(StringUtils.isNotEmpty(trimmed)) {
			return Result.ok(PREFIX + trimmed);
		}
		return Result.ok(trimmed);
	}

	@Override
	public String convertToPresentation(final String value, final ValueContext context) {
		return StringUtils.trim(StringUtils.removeStart(value, PREFIX));
	}
}
