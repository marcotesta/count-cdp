package it.mondogrua.javafx_count_view;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class JFXDisplayBox extends Label  {

    private final SimpleStringProperty observer;

    public JFXDisplayBox(SimpleStringProperty count) {
        this.observer = new SimpleStringProperty();
        textProperty().bind(Bindings.convert(observer));
        observer.bind(count);
    }

}
