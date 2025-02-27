// copied from info.magnolia.ui.vaadin.ckeditor ckeditor5-text-field-connector
com_merkle_oss_magnolia_definition_custom_richtext_ExtendedCKEditor5TextField =
  function () {
    try {
      const connector = this;
      let state = this.getState();
      let editor;

      //different from magnolia
      let config = state.extendedConfig;
      config.heading.options.forEach((option) => option.class = option.clazz);
      Object.values(config.link.decorators).filter((decorator) => decorator.mode === 'automatic').forEach((decorator) =>
        decorator.callback = (url) => new RegExp(decorator.urlPredicateRegex).test(url)
      );
      //different from magnolia

      let CKEDITOR5 = window["CKEDITOR5"];
      // in case custom-builds are object-wrapped, otherwise, just use the original
      if (typeof window["CKEDITOR5"] !== 'function') {
        if (this.getState().editorType) {
          CKEDITOR5 = CKEDITOR5[this.getState().editorType];
        } else {
          CKEDITOR5 = CKEDITOR5["ClassicEditor"];
        }
      }

      CKEDITOR5.create(this.getElement(), config)
        .then(newEditor => {
          let data = this.getState().value;
          if (data != null) {
            newEditor.setData(data);
          }
          newEditor.model.document.on('change:data', () => {
            connector.onDataChange(editor.getData());
          });
          newEditor.keystrokes.set( 'Enter', ( evt, data ) => {
            // prevent the enter key from submitting the form
            evt.stopPropagation();
          } );
          newEditor.editing.view.change( writer => {
            if (this.getState().height != null) {
              writer.setStyle('height', this.getState().height, newEditor.editing.view.document.getRoot());
            }
          } );

          // Handle changes from the server-side
          this.onReceiveEventFromServer = function (name, value) {
            // dispatch the event to the corresponding editor plugin listener
            newEditor.fire(name, value);
          };

          // Pass user interaction to the server-side
          newEditor.listenTo(newEditor, 'mgnlPluginButtonClicked', (evt, data) => {
            let connectorEvent = evt.name;
            if (evt.name.includes(':')){
              connectorEvent = evt.name.split(':')[1];
            }
            connector.onReceiveEventFromClient(connectorEvent, data);
          })

          editor = newEditor;
        })
        .catch(error => {
          console.error(error);
        });

      // Destroy the editor when the component is detached from UI
      this.onUnregister = function () {
        editor.destroy().catch(error => console.error(error));
      }
    } catch(err) {
      console.error("Failed to init richtext editor", err);
      throw err;
    }
  };