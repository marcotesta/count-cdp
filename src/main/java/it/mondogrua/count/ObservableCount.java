package it.mondogrua.count;

import java.util.ArrayList;
import java.util.List;

public class ObservableCount implements Count {

	private Count count;
	private final List<ObservableAdapter> observables = new ArrayList<>();
	
	public ObservableCount(Count count) {
		super();
		this.count = count;
	}
	
	public boolean add(ObservableAdapter observable) {
		return observables.add(observable);
	}
	
	@Override
	public Object getValue() {
		return count.getValue();
	}

	@Override
	public void setValue(Object aValue) {
		count.setValue(aValue);
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
		for(ObservableAdapter observable : observables) {
			observable.notify("getValue");
		}
	}
}
