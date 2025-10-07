package com.merkle.oss.magnolia.definition.custom.example;

import info.magnolia.objectfactory.guice.AbstractGuiceComponentConfigurer;

import java.time.ZoneId;

import com.merkle.oss.magnolia.definition.key.generator.configuration.FallbackAppName;
import com.merkle.oss.magnolia.definition.key.generator.configuration.FallbackDialogName;
import com.merkle.oss.magnolia.definition.key.generator.configuration.IdRegexp;

/**
 * TODO remove, this is just for testing.
 */
public class GuiceComponentConfigurer extends AbstractGuiceComponentConfigurer {
    @Override
    protected void configure() {
        binder().bind(String.class)
                .annotatedWith(IdRegexp.class)
                .toInstance("^SomeModule|blossom-area-dialog:com.some.package");

        binder().bind(String.class)
                .annotatedWith(FallbackDialogName.class)
                .toInstance("CommonDialog");

        binder().bind(String.class)
                .annotatedWith(FallbackAppName.class)
                .toInstance("CommonApp");

        binder().bind(ZoneId.class).toInstance(ZoneId.systemDefault());
    }
}