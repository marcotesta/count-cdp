package it.mondogrua.javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

public class AltDisplayBox extends Label {

    public AltDisplayBox(SimpleStringProperty observable) {
        observable.addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                    Object newValue) {
                setText(newValue.toString());
            }
        });

        setText(observable.getValueSafe());
    }
}
