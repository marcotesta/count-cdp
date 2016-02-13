package it.mondogrua.swing_count_view;

import java.util.Observable;

import it.mondogrua.count.Count;
import it.mondogrua.count.CountObserver;

public class JavaUtilsCountObserverObservableAdapter extends Observable
        implements CountObserver, Count {

    private Count count;

    @Override
    public void setSubject(Count aCount) {
        this.count = aCount;
    }

    @Override
    public void update() {
        notifyListeners();
    }

    @Override
    public Object getCountValue() {
        return count.getCountValue();
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

    private void notifyListeners() {
        setChanged();
        notifyObservers(GET_VALUE_METHOD);
        clearChanged();
    }
}
