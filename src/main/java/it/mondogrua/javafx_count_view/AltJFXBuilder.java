package it.mondogrua.javafx_count_view;

import it.mondogrua.utils.ValueModel;
import javafx.scene.control.Label;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(ValueModel count, PropertyObservableAdapter propertyObservableAdapter) {
		super(count, propertyObservableAdapter);
	}

	@Override
    protected Label makeDisplayBoxOn(final PropertyObservableAdapter count) {
        return new AltDisplayBox(count);
    }

}

