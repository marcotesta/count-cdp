package it.mondogrua.javafx_count_view;

import it.mondogrua.javafx.AltDisplayBox;

public class AltJFXBuilder extends JFXBuilder {

    public AltJFXBuilder(SimpleStringPropertyCount aCount) {
        super(aCount);
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        AltDisplayBox displayBox = new AltDisplayBox();
        displayBox.bind(count);
        add(displayBox, x, y);
    }
}
