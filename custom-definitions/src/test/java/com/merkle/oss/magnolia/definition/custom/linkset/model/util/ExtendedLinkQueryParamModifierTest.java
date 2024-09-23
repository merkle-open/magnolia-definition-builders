package com.merkle.oss.magnolia.definition.custom.linkset.model.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.merkle.oss.magnolia.definition.custom.linkset.LinkTypes;
import com.merkle.oss.magnolia.definition.custom.linkset.model.Link;
import com.merkle.oss.magnolia.definition.custom.linkset.model.LinkModel;

class ExtendedLinkQueryParamModifierTest {
    private static final Link LINK = new LinkModel(
            "linkText",
            "/de/test?param1=42",
            "https://someDomain.com/de/test?param1=42",
            false,
            false,
            LinkTypes.INTERNAL
    );

    @Test
    void withAppend() {
        assertEquals(
                new LinkModel(
                        "linkText",
                        "/de/test?param1=42&param2=43",
                        "https://someDomain.com/de/test?param1=42&param2=43",
                        false,
                        false,
                        LinkTypes.INTERNAL
                ),
                new ExtendedLinkQueryParamModifier().withAppend(LINK, Map.of("param2", "43"))
        );
    }

    @Test
    void withReplace() {
        assertEquals(
                new LinkModel(
                        "linkText",
                        "/de/test?param2=43",
                        "https://someDomain.com/de/test?param2=43",
                        false,
                        false,
                        LinkTypes.INTERNAL
                ),
                new ExtendedLinkQueryParamModifier().withReplace(LINK, Map.of("param2", "43"))
        );
    }
}