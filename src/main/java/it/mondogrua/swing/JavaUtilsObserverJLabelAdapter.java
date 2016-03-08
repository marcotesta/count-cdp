package it.mondogrua.swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import it.mondogrua.utils.ValueModel;

public class JavaUtilsObserverJLabelAdapter implements Observer {

    private JLabel label;
    private ValueModel valueModel;

    public JavaUtilsObserverJLabelAdapter(JLabel aLabel) {
        label = aLabel;
    }

    public void bind(JavaUtilsObservableValueModel aValueModel) {
        this.valueModel = aValueModel;
        setContent(valueModel.getValue());
        aValueModel.addObserver(this);
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
                label.setText(value);
            }
        });
    }
}
