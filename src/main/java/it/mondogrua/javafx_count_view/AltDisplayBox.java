package it.mondogrua.javafx_count_view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

public class AltDisplayBox extends Label {
	
    private final ChangeListener<? super String> observer;

    public AltDisplayBox(PropertyObservableAdapter observable) {
    	observer = new ChangeListener<Object>() {
    		@Override
    		public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
    			
    			setText(newValue.toString());
    		}
    	};

    	observable.addObserver(observer);
		setText(observable.getValueSafe());
    }
}
