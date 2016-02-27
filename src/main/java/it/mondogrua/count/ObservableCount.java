package it.mondogrua.count;

import java.util.ArrayList;
import java.util.List;

import it.mondogrua.utils.Observable;
import it.mondogrua.utils.Observer;

public class ObservableCount implements Observable, Count {

    private Count count;
    private final List<Observer> observers = new ArrayList<>();

    public ObservableCount(Count count) {
        super();
        this.count = count;
    }

    @Override
    public boolean addObserver(Observer observer) {
        return observers.add(observer);
    }

    @Override
    public Object getCountValue() {
        return count.getCountValue();
    }

    @Override
    public void reset() {
        count.reset();
        notifyObservers();
    }

    @Override
    public void increment() {
        count.increment();
        notifyObservers();
    }

    @Override
    public void decrement() {
        count.decrement();
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
