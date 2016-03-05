package it.mondogrua.swing_count_view;

import java.beans.PropertyChangeListener;

import it.mondogrua.count.Count;
import it.mondogrua.utils.BoundProperty;

public class BoundPropertyCount implements BoundProperty, Count {

    private Count count;
    private BoundProperty boundProperty;

    public BoundPropertyCount(BoundProperty aBoundProperty, Count aCount) {
        super();
        this.count = aCount;
        this.boundProperty = aBoundProperty;
    }

    @Override
    public void reset() {
        count.reset();
    }

    @Override
    public void increment() {
        count.increment();
    }

    @Override
    public void decrement() {
        count.decrement();
    }

    @Override
    public Object getCountValue() {
        return count.getCountValue();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        boundProperty.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        boundProperty.removePropertyChangeListener(listener);
    }
}
