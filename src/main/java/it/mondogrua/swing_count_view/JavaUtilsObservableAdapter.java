package it.mondogrua.swing_count_view;

import java.util.Observable;

import it.mondogrua.count.ObservableAdapter;
import it.mondogrua.utils.ValueModel;

public class JavaUtilsObservableAdapter extends Observable implements ObservableAdapter {

    static final String GET_VALUE_METHOD = "getValue";

    private final ValueModel value;

	public JavaUtilsObservableAdapter(ValueModel aValue) {
		super();
		this.value = aValue;
	}

	@Override
	public void notify(String attribute) {
        setChanged();
        notifyObservers(attribute);
        clearChanged();
	}

	public Object getValue() {
		return value.getValue();
	}
}
