package it.mondogrua.console_count;

import java.util.ArrayList;
import java.util.List;

public class StringFilter implements StringListener {

    private final List<StringListener> listeners =
            new ArrayList<StringListener>();

    private String regex;

    public StringFilter(String regex) {
        super();
        this.regex = regex;
    }

    @Override
    public void update(String string) {
        if (string.equals(regex)) {
            notifyListeners(string);
        }
    }

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String c) {
        for (StringListener listener : listeners) {
            listener.update(c);
        }
    }
}
