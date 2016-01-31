package it.mondogrua.count;

import java.util.ArrayList;
import java.util.List;

public class ObservableCount implements Count {

	private Count count;
	private final List<CountObserver> observers = new ArrayList<>();
	
	public ObservableCount(Count count) {
		super();
		this.count = count;
	}
	
	public boolean addCountObserver(CountObserver observer) {
		observer.setSubject(this);
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
		for(CountObserver observer : observers) {
			observer.update();
		}
	}
}
