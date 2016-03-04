package it.mondogrua.swing;

import java.util.Observable;
import java.util.Observer;

import it.mondogrua.count.Count;

public class JavaUtilsObservableCount extends Observable implements Count {

    private Count count;
    private Observable observable;

    public JavaUtilsObservableCount(Observable anObservable, Count aCount) {
        this.observable = anObservable;
        this.count = aCount;
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
    public  boolean hasChanged() {
        return observable.hasChanged();
    }

    @Override
    public int countObservers() {
        return observable.countObservers();
    }

}
