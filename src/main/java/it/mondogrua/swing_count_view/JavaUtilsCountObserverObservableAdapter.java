package it.mondogrua.swing_count_view;

import java.util.Observable;

import it.mondogrua.count.CountObserver;

public class JavaUtilsCountObserverObservableAdapter extends Observable
        implements CountObserver {

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
