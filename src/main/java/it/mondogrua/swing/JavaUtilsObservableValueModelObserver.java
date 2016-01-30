package it.mondogrua.swing;

import java.util.Observable;

import it.mondogrua.count.Count;
import it.mondogrua.utils.CountObserver;

public class JavaUtilsObservableValueModelObserver extends Observable implements CountObserver, Count {
    
	private Count count;
	private Object value = "";
	
	@Override
	public void setSubject(Count aCount) {
		this.count = aCount;
		setModelValue(retrieveValue());
	}

	@Override
	public void update() {
		setModelValue(retrieveValue());
	}
	
	@Override
	public Object getModelValue() {
		return value;
	}

	@Override
	public void setModelValue(Object aValue) {
		this.value = aValue;
        notifyListeners();
	}

	@Override
	public Object getResetValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResetValue(Object aResetValue) {
		// TODO Auto-generated method stub
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

	private String retrieveValue() {
		return this.count.getModelValue().toString();
	}

	private void notifyListeners() {
		setChanged();
        notifyObservers(GET_VALUE_METHOD);
        clearChanged();
	}
}
