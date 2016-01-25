package it.mondogrua.swing;

import java.util.Observable;

import it.mondogrua.utils.ObservableAdapter;
import it.mondogrua.utils.ValueModel;

public class JavaUtilsObservableAdapter extends Observable implements ObservableAdapter {

    public static final String GET_VALUE_METHOD = "getValue";

    private final ValueModel value;

	public JavaUtilsObservableAdapter(ValueModel aValue) {
		super();
		this.value = aValue;
	}

	@Override
	public void notifyObservers() {
        setChanged();
        notifyObservers(GET_VALUE_METHOD);
        clearChanged();
	}

	public Object getValue() {
		return value.getValue();
	}
}
