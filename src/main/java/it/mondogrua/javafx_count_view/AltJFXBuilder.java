package it.mondogrua.javafx_count_view;

import it.mondogrua.javafx.AltDisplayBox;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(PropertyCountObserverObservableAdapter aObservable) {
        super(aObservable);
    }

    @Override
    protected Label makeDisplayBoxOn(PropertyCountObserverObservableAdapter observable) {
        return new AltDisplayBox(observable);
    }
}
