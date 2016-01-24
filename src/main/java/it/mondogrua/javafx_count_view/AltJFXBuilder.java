package it.mondogrua.javafx_count_view;

import it.mondogrua.count.Count;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(Count count, PropertyObservableAdapter aObservable) {
		super(count, aObservable);
	}

	@Override
    protected Label makeDisplayBoxOn(PropertyObservableAdapter observable) {
        return new AltDisplayBox(observable);
    }
}

