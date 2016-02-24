package it.mondogrua.swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import it.mondogrua.utils.PluggableAdaptor;

@SuppressWarnings("serial")
public class DisplayBox extends JLabel implements Observer {

    private PluggableAdaptor adaptor;

    public DisplayBox(PluggableAdaptor adaptor) {
        this.adaptor = adaptor;
        setContent(getContentFrom());
    }

    @Override
    public void update(Observable observable, Object action) {
        setContent(getContentFrom());
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

    private String getContentFrom() {
        return adaptor.execute().toString();
    }
}
