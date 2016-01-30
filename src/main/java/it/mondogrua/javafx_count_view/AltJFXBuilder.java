package it.mondogrua.javafx_count_view;

import it.mondogrua.javafx.AltDisplayBox;
import it.mondogrua.javafx.PropertyObservableObserverCount;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(PropertyObservableObserverCount aObservable) {
		super(aObservable);
	}

	@Override
    protected Label makeDisplayBoxOn(PropertyObservableObserverCount observable) {
        return new AltDisplayBox(observable);
    }
}

