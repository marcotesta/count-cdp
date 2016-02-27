package it.mondogrua.swing_count_view;

import java.util.Observable;

import it.mondogrua.utils.Observer;

public class JavaUtilsCountObserverObservableAdapter extends Observable
        implements Observer {

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
