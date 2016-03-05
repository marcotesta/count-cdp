package it.mondogrua.swing;

import java.beans.PropertyChangeListener;

import it.mondogrua.utils.BoundProperty;
import it.mondogrua.utils.ValueModel;

public class BoundPropertyValueModel implements BoundProperty, ValueModel {

    private ValueModel valueModel;
    private BoundProperty boundProperty;

    public BoundPropertyValueModel(BoundProperty aBoundProperty,
            ValueModel aValueModel) {
        super();
        this.boundProperty = aBoundProperty;
        this.valueModel = aValueModel;
    }

    @Override
    public String getValue() {
        return valueModel.getValue();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        boundProperty.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        boundProperty.removePropertyChangeListener(listener);
    }
}
