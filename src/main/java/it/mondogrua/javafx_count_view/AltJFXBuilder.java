package it.mondogrua.javafx_count_view;

import it.mondogrua.count.Count;
import it.mondogrua.javafx.AltDisplayBox;
import javafx.beans.property.SimpleStringProperty;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(SimpleStringProperty aObservable, Count aCount) {
        super(aObservable, aCount);
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        AltDisplayBox displayBox = new AltDisplayBox();
        displayBox.bind(observable);
        add(displayBox, x, y);
    }
}
