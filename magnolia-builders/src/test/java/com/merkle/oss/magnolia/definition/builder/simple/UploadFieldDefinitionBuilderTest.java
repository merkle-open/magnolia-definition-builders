package com.merkle.oss.magnolia.definition.builder.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import info.magnolia.ui.field.TextFieldDefinition;
import info.magnolia.ui.field.UploadFieldDefinition;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.merkle.oss.magnolia.definition.builder.AbstractFieldDefinitionBuilderTestCase;

class UploadFieldDefinitionBuilderTest extends AbstractFieldDefinitionBuilderTestCase {
	@Test
	void testBuilder() {
		final UploadFieldDefinition definition = super.assertField(new UploadFieldDefinitionBuilder(), (name, builder) -> builder.build(name), null)
				.maxUploadSize(42)
				.allowedMimeTypePattern("AllowedMimeTypePattern")
				.typeAbortMessage("TypeAbortMessage")
				.sizeAbortMessage("SizeAbortMessage")
				.selectNewLabel("SelectNewLabel")
				.selectAnotherLabel("SelectAnotherLabel")
				.deleteLabel("DeleteLabel")
				.dropZoneLabel("DropZoneLabel")
				.inProgressLabel("InProgressLabel")
				.inProgressRatioLabel("InProgressRatioLabel")
				.fileDetailHeaderLabel("FileDetailHeaderLabel")
				.fileDetailNameLabel("FileDetailNameLabel")
				.fileDetailSizeLabel("FileDetailSizeLabel")
				.fileDetailFormatLabel("FileDetailFormatLabel")
				.fileDetailSourceLabel("FileDetailSourceLabel")
				.successNoteLabel("SuccessNoteLabel")
				.warningNoteLabel("WarningNoteLabel")
				.errorNoteLabel("ErrorNoteLabel")
				.sizeInterruption("SizeInterruption")
				.typeInterruption("TypeInterruption")
				.userInterruption("UserInterruption")
				.mismatchInterruption("MismatchInterruption")
				.build("upload");

		assertEquals(42, definition.getMaxUploadSize());
		assertEquals("AllowedMimeTypePattern", definition.getAllowedMimeTypePattern());
		assertEquals("SelectNewLabel", definition.getSelectNewLabel());
		assertEquals("SelectAnotherLabel", definition.getSelectAnotherLabel());
		assertEquals("DeleteLabel", definition.getDeleteLabel());
		assertEquals("DropZoneLabel", definition.getDropZoneLabel());
		assertEquals("InProgressLabel", definition.getInProgressLabel());
		assertEquals("InProgressRatioLabel", definition.getInProgressRatioLabel());
		assertEquals("FileDetailHeaderLabel", definition.getFileDetailHeaderLabel());
		assertEquals("FileDetailNameLabel", definition.getFileDetailNameLabel());
		assertEquals("FileDetailSizeLabel", definition.getFileDetailSizeLabel());
		assertEquals("FileDetailFormatLabel", definition.getFileDetailFormatLabel());
		assertEquals("FileDetailSourceLabel", definition.getFileDetailSourceLabel());
		assertEquals("SuccessNoteLabel", definition.getSuccessNoteLabel());
		assertEquals("WarningNoteLabel", definition.getWarningNoteLabel());
		assertEquals("ErrorNoteLabel", definition.getErrorNoteLabel());
		assertEquals("SizeInterruption", definition.getSizeInterruption());
		assertEquals("TypeInterruption", definition.getTypeInterruption());
		assertEquals("UserInterruption", definition.getUserInterruption());

		final UploadFieldDefinition emptyDefinition = new UploadFieldDefinitionBuilder().build("upload");
		assertEquals(File.class, emptyDefinition.getType());
	}
}
