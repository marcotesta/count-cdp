package it.mondogrua.javafx_count_view;

import it.mondogrua.javafx.AltDisplayBox;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(PropertyCountAdapter aObservable) {
		super(aObservable);
	}

	@Override
    protected Label makeDisplayBoxOn(PropertyCountAdapter observable) {
        return new AltDisplayBox(observable);
    }
}

