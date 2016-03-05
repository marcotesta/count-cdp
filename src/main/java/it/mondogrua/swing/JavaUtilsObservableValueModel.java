package it.mondogrua.swing;

import java.util.Observable;
import java.util.Observer;

import it.mondogrua.utils.ValueModel;

public class JavaUtilsObservableValueModel extends Observable implements
        ValueModel {

    private ValueModel valueModel;
    private Observable observable;

    public JavaUtilsObservableValueModel(Observable anObservable,
            ValueModel valueModel) {
        super();
        this.observable = anObservable;
        this.valueModel = valueModel;
    }

    @Override
    public String getValue() {
        return valueModel.getValue();
    }

    @Override
    public void addObserver(Observer o) {
        observable.addObserver(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observable.deleteObserver(o);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        observable.notifyObservers(arg);
    }

    @Override
    public void deleteObservers() {
        observable.deleteObservers();
    }

    @Override
    public boolean hasChanged() {
        return observable.hasChanged();
    }

    @Override
    public int countObservers() {
        return observable.countObservers();
    }
}
