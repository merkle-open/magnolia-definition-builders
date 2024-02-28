package com.merkle.oss.magnolia.definition.custom.validator.mimetype;

import com.vaadin.data.ValueContext;
import info.magnolia.dam.api.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MimeTypeValidatorTest {
	private MimeTypeValidator validator;

	@BeforeEach
	void setUp() {
		validator = new MimeTypeValidator(
				Set.of(
						"image/svg+xml",
						"image/png"
				),
				"error"
		);
	}

	@Test
	void apply_validMimeType_shouldReturnOk() {
		assertFalse(validator.apply(mockAsset("image/png"), new ValueContext()).isError());
	}

	@Test
	void apply_invalidMimeType_shouldReturnOk() {
		assertTrue(validator.apply(mockAsset("image/jpg"), new ValueContext()).isError());
		assertEquals("error", validator.apply(mockAsset("image/jpg"), new ValueContext()).getErrorMessage());
	}

	@Test
	void apply_noMimeType_shouldReturnError() {
		assertTrue(validator.apply(mockAsset(""), new ValueContext()).isError());
	}

	private Asset mockAsset(final String mimeType) {
		final Asset asset = Mockito.mock(Asset.class);
		Mockito.doReturn(mimeType).when(asset).getMimeType();
		return asset;
	}
}