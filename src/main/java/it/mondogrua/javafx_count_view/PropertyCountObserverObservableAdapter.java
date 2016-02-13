package it.mondogrua.javafx_count_view;

import it.mondogrua.count.Count;
import it.mondogrua.count.CountObserver;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;

public class PropertyCountObserverObservableAdapter extends SimpleStringProperty implements CountObserver, Count {

	private Count count;

	public PropertyCountObserverObservableAdapter() {
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
		return getValue();
	}

	private String retrieveValue() {
		return count.getCountValue().toString();
	}
}
