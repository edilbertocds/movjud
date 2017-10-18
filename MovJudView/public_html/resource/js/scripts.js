function onFieldEnterKey(inputEvent){
    if (inputEvent.getKeyCode() == AdfKeyStroke.ENTER_KEY) {
          var inputTextField = inputEvent.getSource();
          var button = inputTextField.findComponent('botaoBuscar');
          //alert("Selected button: "+button);
          var partialSubmit = true;
          AdfActionEvent.queue(button,partialSubmit);
          event.cancel();
    }
}