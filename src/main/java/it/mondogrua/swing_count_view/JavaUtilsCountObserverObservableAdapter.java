package it.mondogrua.swing_count_view;

import java.util.Observable;

import it.mondogrua.count.CountObserver;
import it.mondogrua.count.ObservableCount;

public class JavaUtilsCountObserverObservableAdapter extends Observable
        implements CountObserver {

    @Override
    public void setSubject(ObservableCount aCount) {
        aCount.addCountObserver(this);
    }

    @Override
    public void update() {
        notifyListeners();
    }

    private void notifyListeners() {
        setChanged();
        notifyObservers("");
        clearChanged();
    }
}
