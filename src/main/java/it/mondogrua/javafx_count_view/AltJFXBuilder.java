package it.mondogrua.javafx_count_view;

import it.mondogrua.javafx.AltDisplayBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(SimpleStringProperty aObservable) {
        super(aObservable);
    }

    @Override
    protected Label makeDisplayBoxOn(SimpleStringProperty observable) {
        return new AltDisplayBox(observable);
    }
}
