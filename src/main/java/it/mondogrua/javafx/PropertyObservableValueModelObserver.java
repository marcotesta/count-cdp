package it.mondogrua.javafx;

import it.mondogrua.count.Count;
import it.mondogrua.utils.CountObserver;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;

public class PropertyObservableValueModelObserver extends SimpleStringProperty implements CountObserver, Count {

	private Count count;

	public PropertyObservableValueModelObserver() {
		super();
        set("");
	}
	
	@Override
	public void setSubject(Count aCount) {
		this.count = aCount;
		setValue(retrieveValue());
	}

	@Override
	public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setValue(retrieveValue());
            }
       });
	}
	
    public void addObserver(ChangeListener<? super String> changeListener) {
    	addListener(changeListener);
	}
    
    public void addObserver(SimpleStringProperty propertyObserver) {
        propertyObserver.bind(this);
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

	@Override
	public void setModelValue(Object aValue) {
		setValue(aValue.toString());
	}

	@Override
	public Object getModelValue() {
		return getValue();
	}
    
    private String retrieveValue() {
		return count.getModelValue().toString();
	}
}
