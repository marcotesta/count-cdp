package it.mondogrua.javafx_count_view;

import it.mondogrua.count.ObservableAdapter;
import it.mondogrua.utils.ValueModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;

public class PropertyObservableAdapter extends SimpleStringProperty implements ObservableAdapter {

    private final ValueModel value;

	public PropertyObservableAdapter(ValueModel aValue) {
		super();
		this.value = aValue;
        set(aValue.getValue().toString());
	}

	@Override
	public void notify(String attribute) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                set(value.getValue().toString());
            }
       });
	}
	
    public void addObserver(ChangeListener<? super String> changeListener) {
    	addListener(changeListener);
	}
    
    public void addObserver(SimpleStringProperty propertyObserver) {
        propertyObserver.bind(this);
    }

}
