package it.mondogrua.swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import it.mondogrua.utils.PluggableAdaptor;

@SuppressWarnings("serial")
public class DisplayBox extends JLabel implements Observer {

    public DisplayBox(Observable aObservable, String action) {
        aObservable.addObserver(this);
        update(aObservable, action);
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object action) {
        setContent(getContentFrom(observable, (String) action));
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

    private String getContentFrom(Object model, String action) {

        return new PluggableAdaptor(model, action, new Object[] {}).execute()
                .toString();
    }
}
