package it.mondogrua.swing_count_view;

import it.mondogrua.count.Count;
import it.mondogrua.swing.AltDisplayBox;
import it.mondogrua.swing.BoundPropertyValueModel;
import it.mondogrua.utils.ValueModelAdaptor;

public class BoundPropertyCountSwingBuilder extends SwingBuilder {

    private BoundPropertyCount count;

    public BoundPropertyCountSwingBuilder(BoundPropertyCount aCount) {
        super(aCount);
        this.count = aCount;
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        AltDisplayBox displayBox = makeDisplayBoxOn();

        BoundPropertyValueModel aValueModel = new BoundPropertyValueModel(count,
                new ValueModelAdaptor(count, Count.GET_VALUE_METHOD));

        displayBox.bind(aValueModel);
        add(displayBox, x, y);
    }

    private AltDisplayBox makeDisplayBoxOn() {
        return new AltDisplayBox();
    }
}
