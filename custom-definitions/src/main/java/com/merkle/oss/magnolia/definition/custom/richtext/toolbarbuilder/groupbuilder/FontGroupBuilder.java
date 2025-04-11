package com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.groupbuilder;

import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontColor;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontFamily;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldConfig.FontSize;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.merkle.oss.magnolia.definition.custom.richtext.toolbarbuilder.ToolbarGroup;

public class FontGroupBuilder extends AbstractToolbarGroupBuilder<FontGroupBuilder> {
	@Nullable
    private List<String> fonts;
	@Nullable
	private List<String> fontSizes;
	@Nullable
	private List<String> fontColors;

	public FontGroupBuilder() {
		super("font");
	}

	public FontGroupBuilder heading() {
		return option("heading");
	}

	public FontGroupBuilder fontFamily(final List<String> fonts) {
        this.fonts = fonts;
        return option("fontFamily");
	}

	public FontGroupBuilder fontSize(final List<String> fontSizes) {
        this.fontSizes = fontSizes;
        return option("fontSize");
	}

	public FontGroupBuilder fontColor(final List<String> fontColors) {
        this.fontColors = fontColors;
        return option("fontColor");
	}

	@Override
	public ToolbarGroup build() {
		final ToolbarGroup toolbarGroup = super.build();
		return new FontToolbarGroup(
				toolbarGroup,
				Stream.ofNullable(fonts).flatMap(Collection::stream).map(font -> new FontFamily.Option(font.trim())).collect(Collectors.toList()),
				Stream.ofNullable(fontSizes).flatMap(Collection::stream).map(font -> new FontSize.Option(font.trim())).collect(Collectors.toList()),
				Stream.ofNullable(fontColors).flatMap(Collection::stream).map(font -> new FontColor.ColorOption("#"+font.trim())).collect(Collectors.toList())
		);
	}

	public static class FontToolbarGroup extends ToolbarGroup {
        private final List<FontFamily.Option> fonts;
        private final List<FontSize.Option> fontSizes;
        private final List<FontColor.ColorOption> fontColors;

        public FontToolbarGroup(
				final ToolbarGroup wrapped,
				final List<FontFamily.Option> fonts,
				final List<FontSize.Option> fontSizes,
				final List<FontColor.ColorOption> fontColors
		) {
			super(wrapped.getName(), wrapped.getItems());
            this.fonts = fonts;
            this.fontSizes = fontSizes;
            this.fontColors = fontColors;
        }

		public List<FontFamily.Option> getFonts() {
			return fonts;
		}

		public List<FontSize.Option> getFontSizes() {
			return fontSizes;
		}

		public List<FontColor.ColorOption> getFontColors() {
			return fontColors;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof FontToolbarGroup that)) {
				return false;
			}
			if (!super.equals(o)) {
				return false;
			}
            return Objects.equals(fonts, that.fonts) && Objects.equals(fontSizes, that.fontSizes) && Objects.equals(fontColors, that.fontColors);
		}

		@Override
		public int hashCode() {
			return Objects.hash(super.hashCode(), fonts, fontSizes, fontColors);
		}

		@Override
		public String toString() {
			return "FontToolbarGroup{" +
					"fonts=" + fonts +
					", fontSizes=" + fontSizes +
					", fontColors=" + fontColors +
					"} " + super.toString();
		}
	}
}
