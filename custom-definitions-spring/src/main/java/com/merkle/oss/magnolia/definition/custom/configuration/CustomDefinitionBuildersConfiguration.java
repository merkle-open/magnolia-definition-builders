package com.merkle.oss.magnolia.definition.custom.configuration;

import com.merkle.oss.magnolia.definition.custom.colorpicker.configuration.ColorPickerConfiguration;
import com.merkle.oss.magnolia.definition.custom.imageset.configuration.ImageSetConfiguration;
import com.merkle.oss.magnolia.definition.custom.linkset.configuration.LinkSetConfiguration;
import com.merkle.oss.magnolia.definition.custom.videoset.configuration.VideoSetConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
		LinkSetConfiguration.class,
		ImageSetConfiguration.class,
		VideoSetConfiguration.class,
		ColorPickerConfiguration.class
})
public class CustomDefinitionBuildersConfiguration {
}