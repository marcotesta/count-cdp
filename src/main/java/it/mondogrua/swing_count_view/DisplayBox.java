package it.mondogrua.swing_count_view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class DisplayBox extends JLabel implements java.util.Observer {

    public DisplayBox(JavaUtilsObservableAdapter count, String action) {
        count.addObserver(this);
        update(count, action);
    }

    @Override  // java.util.Observer
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
            Object ret = method.invoke(model, new Object[]{});
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
