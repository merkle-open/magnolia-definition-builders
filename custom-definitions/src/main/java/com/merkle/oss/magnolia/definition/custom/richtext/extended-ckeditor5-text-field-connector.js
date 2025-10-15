function isEmptyObject(obj) {
  return obj.constructor === Object && Object.keys(obj).length === 0;
}

function isEmptyArray(obj) {
  return Array.isArray(obj) && obj.length === 0;
}

function pruneEmpty(obj) {
  return JSON.parse(JSON.stringify(obj), (key, value) => {
    if (value === null || isEmptyArray(value) || isEmptyObject(value)) {
      return undefined;
    }
    return value;
  });
}

function DisallowNestingTables(editor) {
  editor.model.schema.addChildCheck( ( context, childDefinition ) => {
    if ( childDefinition.name === 'table' && Array.from( context.getNames() ).includes( 'table' ) ) {
      return false;
    }
  });
}

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

      const updateAutomaticDecorators = (decorators) =>
        decorators && Object.values(decorators).filter((decorator) => decorator.mode === 'automatic').forEach(decorator =>
          decorator.callback = (url) => new RegExp(decorator.urlPredicateRegex).test(url)
        )

      const mapPattern = (pattern) => {
        pattern.name = new RegExp(pattern.name);
        pattern.classes = mapPropertyPattern(pattern.classes);
        pattern.styles = mapPropertyPattern(pattern.styles);
        pattern.attributes = mapPropertyPattern(pattern.attributes);
      }
      const mapPropertyPattern = (propertyPattern) => {
        if (Object.keys(propertyPattern).length === 0) {
          return true;
        }
        return propertyPattern;
      };

      const mapToolbarItems = (toolbarItems) => {
        return toolbarItems.map(mapToolbarItem);
      }
      const mapToolbarItem = (toolbarItem) => {
        if(toolbarItem.flatValue) {
          return toolbarItem.flatValue;
        }
        const nested = toolbarItem.nestedValue;
        nested.items = nested.items.map(mapToolbarItem)
        return nested;
      }
      config.toolbar.items = mapToolbarItems(config.toolbar.items);

      config.table.contentToolbar = mapToolbarItems(config.table.contentToolbar);
      config.table.tableToolbar = mapToolbarItems(config.table.tableToolbar);

      config.htmlSupport.allow.forEach(mapPattern);
      config.htmlSupport.disallow.forEach(mapPattern);
      config = pruneEmpty(config);
      const extraPlugins = [];
      if(!config.table.allowNesting) {
        extraPlugins.push(DisallowNestingTables)
      }
      config.extraPlugins = extraPlugins;
      updateAutomaticDecorators(config.link?.decorators);

      let CKEDITOR5 = window["CKEDITOR5"];
      // in case custom-builds are object-wrapped, otherwise, just use the original
      if (typeof window["CKEDITOR5"] !== 'function') {
        if (this.getState().editorType) {
          CKEDITOR5 = CKEDITOR5[this.getState().editorType];
        } else {
          CKEDITOR5 = CKEDITOR5["ClassicEditor"];
        }
      }

      CKEDITOR5.defaultConfig.mgnllink.decorators = {}; // delete default decorators (open in new tab)
      CKEDITOR5.defaultConfig.table = {}; // delete default table config

      if (config.printDebugLogs) {
        console.log("config: " + JSON.stringify(config));
        console.log("default config: " + JSON.stringify(CKEDITOR5.defaultConfig));
      }
      // different from magnolia

      // Import CSS inline
      if (!document.querySelector('style[data-mgnl-ckeditor-css]')) {
        const style = document.createElement('style');
        style.setAttribute('data-mgnl-ckeditor-css', 'true');
        style.textContent = `
        :root {
          --ck-z-default: 10000;
          --ck-z-panel: calc(var(--ck-z-default) + 999);
          --ck-z-dialog: calc(var(--ck-z-panel) + 1);
          --ck-color-base-background: #f5f5f5;
          --ck-image-insert-insert-by-url-width: 200px;
          --ck-color-focus-border-coordinates: 260, 13%, 9%;
        }
        .ck.ck-image-insert-url { width: 100%; }
        .ck-editor__editable { background: var(--ck-color-base-background); }
        .ck-editor__editable:hover:not(:focus) { border-color: #e1dfe4; }
        .ck.ck-editor__main>.ck-editor__editable:not(.ck-focused) { border-color: transparent; }
        .ck.ck-editor__top .ck-sticky-panel .ck-sticky-panel__content { border-width: 1px 1px 1px; }
        .ck.ck-editor__main>.ck-editor__editable:hover:not(.ck-focused) { border-color: #e1dfe4; }
      `;
        document.head.appendChild(style);
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
          newEditor.keystrokes.set('Enter', (evt, data) => {
            // prevent the enter key from submitting the form
            evt.stopPropagation();
          });
          newEditor.editing.view.change(writer => {
            if (this.getState().height != null) {
              writer.setStyle('height', this.getState().height, newEditor.editing.view.document.getRoot());
            }
          });

          // Handle changes from the server-side
          this.onReceiveEventFromServer = function (name, value) {
            // dispatch the event to the corresponding editor plugin listener
            newEditor.fire(name, value);
          };

          // Pass user interaction to the server-side
          newEditor.listenTo(newEditor, 'mgnlPluginButtonClicked', (evt, data) => {
            let connectorEvent = evt.name;
            if (evt.name.includes(':')) {
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
        //remove the data-mgnl-ckeditor-css
        document.querySelector('style[data-mgnl-ckeditor-css]').remove();
      }
    } catch (err) {
      console.error("Failed to init richtext editor", err);
      throw err;
    }
  };
