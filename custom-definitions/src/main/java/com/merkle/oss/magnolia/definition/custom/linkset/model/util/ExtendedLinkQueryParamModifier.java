package com.merkle.oss.magnolia.definition.custom.linkset.model.util;

import com.merkle.oss.magnolia.definition.custom.linkset.model.Link;
import com.merkle.oss.magnolia.definition.custom.linkset.model.LinkModel;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class ExtendedLinkQueryParamModifier {

	public Link withAppend(final Link extendedLink, final Map<String, String> queryParams) {
		return modify(extendedLink, queryParams, false);
	}

	public Link withReplace(final Link extendedLink, final Map<String, String> queryParams) {
		return modify(extendedLink, queryParams, true);
	}

	private Link modify(final Link extendedLink, final Map<String, String> queryParams, final boolean replace) {
		return new LinkModel(
				extendedLink.getText(),
				setQueryParams(extendedLink.getHref(), queryParams, replace),
				setQueryParams(extendedLink.getExternalHref(), queryParams, replace),
				extendedLink.isOpenInNewWindow(),
				extendedLink.isExternal(),
				extendedLink.getLinkType()
		);
	}

	private String setQueryParams(final String href, final Map<String, String> queryParams, final boolean replace) {
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(href);
		if (replace) {
			uriComponentsBuilder.replaceQuery(null);
		}
		queryParams.forEach(uriComponentsBuilder::queryParam);
		return uriComponentsBuilder.toUriString();
	}
}
