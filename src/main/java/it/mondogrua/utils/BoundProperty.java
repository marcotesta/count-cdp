package it.mondogrua.utils;

import java.beans.PropertyChangeListener;

public interface BoundProperty {

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

}
