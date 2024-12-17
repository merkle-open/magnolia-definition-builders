// copied from info.magnolia.ui.vaadin.ckeditor ckeditor5-text-field-connector
com_merkle_oss_magnolia_definition_custom_richtext_ExtendedCKEditor5TextField =
  function () {
    const connector = this;
    let state = this.getState();
    let editor;

    //different from magnolia
    let config = state.extendedConfig;
    config.heading.options.forEach((option) => option.class = option.clazz);
    //different from magnolia

    // name of the object produced by @magnolia/ckeditor5-integration
    let buildObject = window["ClassicEditor"];
    if (this.getState().editorType) {
      buildObject = CKEDITOR[this.getState().editorType];
      config = {'licenseKey': config.licenseKey};
    }

    buildObject.create(this.getElement(), config)
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
  };

