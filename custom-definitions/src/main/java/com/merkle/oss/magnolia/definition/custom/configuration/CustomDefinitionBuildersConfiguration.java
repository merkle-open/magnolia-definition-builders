package com.merkle.oss.magnolia.definition.custom.configuration;

import com.merkle.oss.magnolia.definition.custom.linkset.configuration.LinkSetConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
		LinkSetConfiguration.class
})
public class CustomDefinitionBuildersConfiguration {
}