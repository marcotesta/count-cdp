package it.mondogrua.swing_count_view;

import javax.swing.JLabel;

import it.mondogrua.count.Count;
import it.mondogrua.swing.JavaUtilsObserverJLabelAdapter;
import it.mondogrua.swing.JavaUtilsObservableValueModel;
import it.mondogrua.utils.ValueModelAdaptor;

public class JavaUtilsObservableCountSwingBuilder extends CountSwingBuilder {

    private JavaUtilsObservableCount count;

    public JavaUtilsObservableCountSwingBuilder(
            JavaUtilsObservableCount aCount) {
        super(aCount);
        count = aCount;
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        JLabel label = new JLabel();
        JavaUtilsObserverJLabelAdapter jLableAdapter = makeJLableAdapter(label);
        JavaUtilsObservableValueModel valueModel = new JavaUtilsObservableValueModel(count,
                new ValueModelAdaptor(count, Count.GET_VALUE_METHOD));
        jLableAdapter.bind(valueModel);
        add(label, x, y);
    }

    private JavaUtilsObserverJLabelAdapter makeJLableAdapter(JLabel label) {
        return new JavaUtilsObserverJLabelAdapter(label);
    }
}
