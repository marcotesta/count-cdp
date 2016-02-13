package it.mondogrua.javafx;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class JFXDisplayBox extends Label {

    public JFXDisplayBox(SimpleStringProperty property) {
        textProperty().bind(Bindings.convert(property));
    }
}
