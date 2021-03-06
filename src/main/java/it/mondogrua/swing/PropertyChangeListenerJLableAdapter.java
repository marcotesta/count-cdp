package it.mondogrua.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import it.mondogrua.utils.ValueModel;

public class PropertyChangeListenerJLableAdapter implements
        PropertyChangeListener {

    private JLabel label;
    private ValueModel valueModel;

    public PropertyChangeListenerJLableAdapter(JLabel label) {
        super();
        this.label = label;
    }

    public void bind(BoundPropertyValueModel aValueModel) {
        this.valueModel = aValueModel;
        setContent(valueModel.getValue());
        aValueModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setContent(valueModel.getValue());
    }

    // Private Methods --------------------------------------------------------

    private void setContent(String value) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                label.setText(value);
            }
        });
    }
}
