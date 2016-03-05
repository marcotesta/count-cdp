package it.mondogrua.swing_count_view;

import it.mondogrua.count.Count;
import it.mondogrua.swing.DisplayBox;
import it.mondogrua.swing.JavaUtilsObservableValueModel;
import it.mondogrua.utils.ValueModelAdaptor;

public class JavaUtilsObservableCountSwingBuilder extends SwingBuilder {

    private JavaUtilsObservableCount count;

    public JavaUtilsObservableCountSwingBuilder(
            JavaUtilsObservableCount aCount) {
        super(aCount);
        count = aCount;
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        DisplayBox displayBox = makeDisplayBoxOn();
        displayBox.bind(new JavaUtilsObservableValueModel(count,
                new ValueModelAdaptor(count, Count.GET_VALUE_METHOD)));
        add(displayBox, x, y);
    }

    private DisplayBox makeDisplayBoxOn() {
        return new DisplayBox();
    }
}
