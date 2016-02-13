package it.mondogrua.swing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

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

        Method method;
        try {
            method = model.getClass().getMethod(action);
            Object ret = method.invoke(model, new Object[] {});
            return ret.toString();
        } catch (SecurityException e) {
            // ...
        } catch (NoSuchMethodException e) {
            // ...
        } catch (IllegalAccessException e) {
            // ...
        } catch (IllegalArgumentException e) {
            // ...
        } catch (InvocationTargetException e) {
            // ...
        }
        return "";
    }
}
