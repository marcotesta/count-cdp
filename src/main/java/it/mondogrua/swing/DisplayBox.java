package it.mondogrua.swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import it.mondogrua.utils.ValueModel;

@SuppressWarnings("serial")
public class DisplayBox extends JLabel implements Observer {

    private ValueModel valueModel;

    public DisplayBox(ValueModel aValueModel) {
        this.valueModel = aValueModel;
        setContent(valueModel.getValue());
    }

    @Override
    public void update(Observable observable, Object action) {
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

    public void bind(Observable observable) {
        observable.addObserver(this);
    }
}
