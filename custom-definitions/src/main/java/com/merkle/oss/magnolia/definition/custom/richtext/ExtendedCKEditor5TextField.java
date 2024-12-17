package com.merkle.oss.magnolia.definition.custom.richtext;

import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextField;
import info.magnolia.ui.vaadin.ckeditor.CKEditor5TextFieldState;

import com.vaadin.annotations.JavaScript;

/**
 * Server side component for the ExtendedCKEditor 5 JavaScript rich-text editor.
 */
@JavaScript({
        //a virtual path, which is redirected to an actual build specified in the microprofile.
        //for developing set 'magnolia.ckeditor5.build' microprofile property.
        "vaadin://ckeditor5.js",
        "extended-ckeditor5-text-field-connector.js"
})
public class ExtendedCKEditor5TextField extends CKEditor5TextField {

    @Override
    protected State getState() {
        return (State) super.getState();
    }

    @Override
    protected State getState(final boolean markAsDirty) {
        return (State) super.getState(markAsDirty);
    }

    public void setConfig(final ExtendedCKEditor5TextFieldConfig config) {
        getState().extendedConfig = config;
        getState().config = config;
    }

    /**
     * Shared state for the {@link ExtendedCKEditor5TextField}.
     */
    public static class State extends CKEditor5TextFieldState {
        public ExtendedCKEditor5TextFieldConfig extendedConfig;
    }
}
