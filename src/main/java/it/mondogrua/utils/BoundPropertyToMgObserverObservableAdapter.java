package it.mondogrua.utils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BoundPropertyToMgObserverObservableAdapter implements
        BoundProperty, Observer {

    private PropertyChangeSupport propertyChangeSupport =
            new PropertyChangeSupport(this);

    @Override
    public void update() {
        Object source = this;
        String propertyName = "";
        Object oldValue = null;
        Object newValue = null;
        PropertyChangeEvent event = new PropertyChangeEvent(source,
                propertyName, oldValue, newValue);
        propertyChangeSupport.firePropertyChange(event);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
