package it.mondogrua.javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class JFXDisplayBox extends Label {

    public void bind(SimpleStringProperty property) {
        textProperty().bind(property);
    }
}
