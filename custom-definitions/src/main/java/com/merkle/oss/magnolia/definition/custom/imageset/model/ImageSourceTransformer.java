package com.merkle.oss.magnolia.definition.custom.imageset.model;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.commons.lang3.NotImplementedException;

import com.merkle.oss.magnolia.definition.custom.imageset.ImageType;

import jakarta.annotation.Nullable;
import jakarta.inject.Inject;

public interface ImageSourceTransformer extends Predicate<ImageType> {
    Optional<ImageSource> transform(Locale locale, String assetId);
    boolean exists(String assetId);

    class Provider {
        private final Set<ImageSourceTransformer> imageSourceTransformers;

        @Inject
        public Provider(final Set<ImageSourceTransformer> imageSourceTransformers) {
            this.imageSourceTransformers = imageSourceTransformers;
        }

        public ImageSourceTransformer get(final ImageType imageType) {
            return imageSourceTransformers.stream()
                    .filter(transformer -> transformer.test(imageType)).findFirst()
                    .orElseThrow(() ->
                            new NotImplementedException("No source transformer configured for " + imageType)
                    );
        }
    }

    class ImageSource {
        private final String src;
        @Nullable
        private final String altText;

        public ImageSource(final String src, @Nullable final String altText) {
            this.src = src;
            this.altText = altText;
        }

        public String getSrc() {
            return src;
        }

        public Optional<String> getAltText() {
            return Optional.ofNullable(altText);
        }
    }
}
