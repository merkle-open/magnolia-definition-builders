package com.merkle.oss.magnolia.definition.builder.simple;

import info.magnolia.ui.field.UploadFieldDefinition;

import java.io.File;
import java.util.Optional;

import jakarta.annotation.Nullable;

/**
 * builds a {@link UploadFieldDefinition}
 * @see <a href="https://docs.magnolia-cms.com/product-docs/6.2/developing/templating/dialog-definition/field-definition/list-of-fields/upload-field">magnolia Docs - Upload field </a>
 * @author Merkle DACH
 */
public class UploadFieldDefinitionBuilder extends AbstractConfiguredFieldDefinitionBuilder<File, UploadFieldDefinition, UploadFieldDefinitionBuilder> {
	@Nullable
	private Long maxUploadSize;
	@Nullable
	private String allowedMimeTypePattern;
	@Nullable
	private String typeAbortMessage;
	@Nullable
	private String sizeAbortMessage;
	@Nullable
	private String selectNewLabel;
	@Nullable
	private String selectAnotherLabel;
	@Nullable
	private String deleteLabel;
	@Nullable
	private String dropZoneLabel;
	@Nullable
	private String inProgressLabel;
	@Nullable
	private String inProgressRatioLabel;
	@Nullable
	private String fileDetailHeaderLabel;
	@Nullable
	private String fileDetailNameLabel;
	@Nullable
	private String fileDetailSizeLabel;
	@Nullable
	private String fileDetailFormatLabel;
	@Nullable
	private String fileDetailSourceLabel;
	@Nullable
	private String successNoteLabel;
	@Nullable
	private String warningNoteLabel;
	@Nullable
	private String errorNoteLabel;
	@Nullable
	private String sizeInterruption;
	@Nullable
	private String typeInterruption;
	@Nullable
	private String userInterruption;
	@Nullable
	private String mismatchInterruption;

	public UploadFieldDefinitionBuilder() {}
	public UploadFieldDefinitionBuilder(final UploadFieldDefinition definition) {
		super(definition);
		maxUploadSize(definition.getMaxUploadSize());
		allowedMimeTypePattern(definition.getAllowedMimeTypePattern());
		typeAbortMessage(definition.getTypeAbortMessage());
		sizeAbortMessage(definition.getSizeAbortMessage());
		selectNewLabel(definition.getSelectNewLabel());
		selectAnotherLabel(definition.getSelectAnotherLabel());
		deleteLabel(definition.getDeleteLabel());
		dropZoneLabel(definition.getDropZoneLabel());
		inProgressLabel(definition.getInProgressLabel());
		inProgressRatioLabel(definition.getInProgressRatioLabel());
		fileDetailHeaderLabel(definition.getFileDetailHeaderLabel());
		fileDetailNameLabel(definition.getFileDetailNameLabel());
		fileDetailSizeLabel(definition.getFileDetailSizeLabel());
		fileDetailFormatLabel(definition.getFileDetailFormatLabel());
		fileDetailSourceLabel(definition.getFileDetailSourceLabel());
		successNoteLabel(definition.getSuccessNoteLabel());
		warningNoteLabel(definition.getWarningNoteLabel());
		errorNoteLabel(definition.getErrorNoteLabel());
		sizeInterruption(definition.getSizeInterruption());
		typeInterruption(definition.getTypeInterruption());
		userInterruption(definition.getUserInterruption());
		mismatchInterruption(definition.getMismatchInterruption());
	}

	public UploadFieldDefinitionBuilder maxUploadSize(final long maxUploadSize) {
		this.maxUploadSize = maxUploadSize;
		return self();
	}

	public UploadFieldDefinitionBuilder allowedMimeTypePattern(final String allowedMimeTypePattern){
		this.allowedMimeTypePattern = allowedMimeTypePattern;
		return self();
	}

	public UploadFieldDefinitionBuilder typeAbortMessage(final String typeAbortMessage){
		this.typeAbortMessage = typeAbortMessage;
		return self();
	}

	public UploadFieldDefinitionBuilder sizeAbortMessage(final String sizeAbortMessage){
		this.sizeAbortMessage = sizeAbortMessage;
		return self();
	}

	public UploadFieldDefinitionBuilder selectNewLabel(final String selectNewLabel){
		this.selectNewLabel = selectNewLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder selectAnotherLabel(final String selectAnotherLabel){
		this.selectAnotherLabel = selectAnotherLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder deleteLabel(final String deleteLabel){
		this.deleteLabel = deleteLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder dropZoneLabel(final String dropZoneLabel){
		this.dropZoneLabel = dropZoneLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder inProgressLabel(final String inProgressLabel){
		this.inProgressLabel = inProgressLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder inProgressRatioLabel(final String inProgressRatioLabel){
		this.inProgressRatioLabel = inProgressRatioLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder fileDetailHeaderLabel(final String fileDetailHeaderLabel){
		this.fileDetailHeaderLabel = fileDetailHeaderLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder fileDetailNameLabel(final String fileDetailNameLabel){
		this.fileDetailNameLabel = fileDetailNameLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder fileDetailSizeLabel(final String fileDetailSizeLabel){
		this.fileDetailSizeLabel = fileDetailSizeLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder fileDetailFormatLabel(final String fileDetailFormatLabel){
		this.fileDetailFormatLabel = fileDetailFormatLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder fileDetailSourceLabel(final String fileDetailSourceLabel){
		this.fileDetailSourceLabel = fileDetailSourceLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder successNoteLabel(final String successNoteLabel){
		this.successNoteLabel = successNoteLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder warningNoteLabel(final String warningNoteLabel){
		this.warningNoteLabel = warningNoteLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder errorNoteLabel(final String errorNoteLabel){
		this.errorNoteLabel = errorNoteLabel;
		return self();
	}

	public UploadFieldDefinitionBuilder sizeInterruption(final String sizeInterruption){
		this.sizeInterruption = sizeInterruption;
		return self();
	}

	public UploadFieldDefinitionBuilder typeInterruption(final String typeInterruption){
		this.typeInterruption = typeInterruption;
		return self();
	}

	public UploadFieldDefinitionBuilder userInterruption(final String userInterruption){
		this.userInterruption = userInterruption;
		return self();
	}

	public UploadFieldDefinitionBuilder mismatchInterruption(final String mismatchInterruption){
		this.mismatchInterruption = mismatchInterruption;
		return self();
	}

	public UploadFieldDefinition build(final String name) {
		final UploadFieldDefinition definition = new UploadFieldDefinition();
		super.populate(definition, name);
		Optional.ofNullable(maxUploadSize).ifPresent(definition::setMaxUploadSize);
		Optional.ofNullable(allowedMimeTypePattern).ifPresent(definition::setAllowedMimeTypePattern);
		Optional.ofNullable(typeAbortMessage).ifPresent(definition::setTypeAbortMessage);
		Optional.ofNullable(sizeAbortMessage).ifPresent(definition::setSizeAbortMessage);
		Optional.ofNullable(selectNewLabel).ifPresent(definition::setSelectNewLabel);
		Optional.ofNullable(selectAnotherLabel).ifPresent(definition::setSelectAnotherLabel);
		Optional.ofNullable(deleteLabel).ifPresent(definition::setDeleteLabel);
		Optional.ofNullable(dropZoneLabel).ifPresent(definition::setDropZoneLabel);
		Optional.ofNullable(inProgressLabel).ifPresent(definition::setInProgressLabel);
		Optional.ofNullable(inProgressRatioLabel).ifPresent(definition::setInProgressRatioLabel);
		Optional.ofNullable(fileDetailHeaderLabel).ifPresent(definition::setFileDetailHeaderLabel);
		Optional.ofNullable(fileDetailNameLabel).ifPresent(definition::setFileDetailNameLabel);
		Optional.ofNullable(fileDetailSizeLabel).ifPresent(definition::setFileDetailSizeLabel);
		Optional.ofNullable(fileDetailFormatLabel).ifPresent(definition::setFileDetailFormatLabel);
		Optional.ofNullable(fileDetailSourceLabel).ifPresent(definition::setFileDetailSourceLabel);
		Optional.ofNullable(successNoteLabel).ifPresent(definition::setSuccessNoteLabel);
		Optional.ofNullable(warningNoteLabel).ifPresent(definition::setWarningNoteLabel);
		Optional.ofNullable(errorNoteLabel).ifPresent(definition::setErrorNoteLabel);
		Optional.ofNullable(sizeInterruption).ifPresent(definition::setSizeInterruption);
		Optional.ofNullable(typeInterruption).ifPresent(definition::setTypeInterruption);
		Optional.ofNullable(userInterruption).ifPresent(definition::setUserInterruption);
		Optional.ofNullable(mismatchInterruption).ifPresent(definition::setMismatchInterruption);
		return definition;
	}
}
