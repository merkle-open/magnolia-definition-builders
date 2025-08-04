package com.merkle.oss.magnolia.definition.custom.linkset.model.util;

import java.util.Collections;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.machinezoo.noexception.Exceptions;
import com.merkle.oss.magnolia.definition.custom.linkset.model.Link;

public class LinkQueryParamModifier {

	public Link withAppend(final Link extendedLink, final Map<String, String> queryParams) {
		return modify(extendedLink, queryParams, false);
	}

	public Link withReplace(final Link extendedLink, final Map<String, String> queryParams) {
		return modify(extendedLink, queryParams, true);
	}

	private Link modify(final Link link, final Map<String, String> queryParams, final boolean replace) {
		return link.withHref(
				setQueryParams(link.getHref(), queryParams, replace),
				setQueryParams(link.getExternalHref(), queryParams, replace)
		);
	}

	private String setQueryParams(final String href, final Map<String, String> queryParams, final boolean replace) {
		final URIBuilder uriBuilder = Exceptions.wrap().get(() -> new URIBuilder(href));
		if (replace) {
			uriBuilder.setParameters(Collections.emptyList());
		}
		queryParams.forEach(uriBuilder::addParameter);
		return Exceptions.wrap().get(uriBuilder::build).toString();
	}
}
