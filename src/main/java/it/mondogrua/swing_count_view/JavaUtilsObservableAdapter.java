package it.mondogrua.swing_count_view;

import it.mondogrua.count.ObservableAdapter;
import it.mondogrua.utils.ValueModel;

public class JavaUtilsObservableAdapter extends java.util.Observable implements ObservableAdapter {

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
