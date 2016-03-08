package it.mondogrua.swing_count_view;

import javax.swing.JLabel;

import it.mondogrua.count.Count;
import it.mondogrua.swing.PropertyChangeListenerJLableAdapter;
import it.mondogrua.swing.BoundPropertyValueModel;
import it.mondogrua.utils.ValueModelAdaptor;

public class BoundPropertyCountSwingBuilder extends CountSwingBuilder {

    private BoundPropertyCount count;

    public BoundPropertyCountSwingBuilder(BoundPropertyCount aCount) {
        super(aCount);
        this.count = aCount;
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        JLabel label = new JLabel();
        PropertyChangeListenerJLableAdapter jLableAdapter = makeJLableAdapter(label);

        BoundPropertyValueModel valueModel = new BoundPropertyValueModel(count,
                new ValueModelAdaptor(count, Count.GET_VALUE_METHOD));

        jLableAdapter.bind(valueModel);
        add(label, x, y);
    }

    private PropertyChangeListenerJLableAdapter makeJLableAdapter(JLabel label) {
        return new PropertyChangeListenerJLableAdapter(label);
    }
}
