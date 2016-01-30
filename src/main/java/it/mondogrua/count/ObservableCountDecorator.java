package it.mondogrua.count;

import java.util.ArrayList;
import java.util.List;

import it.mondogrua.utils.CountObserver;

public class ObservableCountDecorator implements Count {

	private Count count;
	private final List<CountObserver> observers = new ArrayList<>();
	
	public ObservableCountDecorator(Count count) {
		super();
		this.count = count;
	}
	
	public boolean addCountObserver(CountObserver observer) {
		observer.setSubject(this);
		return observers.add(observer);
	}
	
	@Override
	public Object getModelValue() {
		return count.getModelValue();
	}

	@Override
	public void setModelValue(Object aValue) {
		count.setModelValue(aValue);
		notifyObservers();
	}

	@Override
	public Object getResetValue() {
		return count.getResetValue();
	}

	@Override
	public void setResetValue(Object aResetValue) {
		count.setResetValue(aResetValue);
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
		for(CountObserver observer : observers) {
			observer.update();
		}
	}
}
