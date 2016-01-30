package it.mondogrua.javafx_count_view;

import it.mondogrua.javafx.AltDisplayBox;
import it.mondogrua.javafx.PropertyObservableValueModelObserver;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(PropertyObservableValueModelObserver aObservable) {
		super(aObservable);
	}

	@Override
    protected Label makeDisplayBoxOn(PropertyObservableValueModelObserver observable) {
        return new AltDisplayBox(observable);
    }
}

