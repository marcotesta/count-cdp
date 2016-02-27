package it.mondogrua.utils;

import java.util.Observable;

public class JavaUtilsToMgObserverObservableAdapter extends Observable
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
