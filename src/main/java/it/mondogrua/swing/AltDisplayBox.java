package it.mondogrua.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import it.mondogrua.utils.ValueModel;

@SuppressWarnings("serial")
public class AltDisplayBox extends JLabel implements PropertyChangeListener {

    private ValueModel valueModel;

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
                setText(value);
            }
        });
    }
}
