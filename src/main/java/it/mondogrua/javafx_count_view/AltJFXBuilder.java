package it.mondogrua.javafx_count_view;

import it.mondogrua.count.Count;
import it.mondogrua.javafx.AltDisplayBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(SimpleStringProperty aObservable, Count aCount) {
        super(aObservable, aCount);
    }

    @Override
    protected Label makeDisplayBoxOn(SimpleStringProperty observable) {
        return new AltDisplayBox(observable);
    }
}
