package com.merkle.oss.magnolia.definition.custom.richtext.config.heading;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a heading configuration.
 */
//   See implementation here:
//   https://github.com/ckeditor/ckeditor5/blob/v41.4.2/packages/ckeditor5-heading/src/utils.ts#L34
public class Heading {
    public final List<Option> options;

    public Heading(final List<HeadingOption> options) {
        this.options = options.stream().map(Option::new).collect(Collectors.toList());
    }

    /**
     * Represents a heading option.
     */
    //   See implementation here:
    // https://github.com/ckeditor/ckeditor5/blob/v41.4.2/packages/ckeditor5-heading/src/headingconfig.ts#L76
    public static class Option {
        public final String model;
        public final String view;
        public final String title;
        public final String clazz;

        public Option(final HeadingOption option) {
            this.model = option.getModel();
            this.view = option.getView().orElse(null);
            this.title = option.getTitle();
            this.clazz = option.getClazz();
        }
    }
}
