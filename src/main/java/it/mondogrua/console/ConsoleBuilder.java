package it.mondogrua.console;

import static it.mondogrua.count.Count.DECREMENT_METHOD;
import static it.mondogrua.count.Count.INCREMENT_METHOD;
import static it.mondogrua.count.Count.RESET_METHOD;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import it.mondogrua.count.Count;
import it.mondogrua.utils.Observable;
import it.mondogrua.utils.ObserverAdaptor;
import it.mondogrua.utils.PluggableAdaptor;
import it.mondogrua.utils.ValueModelAdaptor;

public class ConsoleBuilder {

    private List<Object> components = new ArrayList<Object>();
    private Observable observable;
    private Count count;

    public ConsoleBuilder(Observable observable, Count aCount) {
        super();
        this.observable = observable;
        this.count = aCount;
    }

    public void addResetStreamListener(String regex, InputStream in) {
        add(makeStreamListenerOn(count, RESET_METHOD, regex, in));
    }

    public void addIncrementStreamListener(String regex, InputStream in) {
        add(makeStreamListenerOn(count, INCREMENT_METHOD, regex, in));
    }

    public void addDecrementStreamListener(String regex, InputStream in) {
        add(makeStreamListenerOn(count, DECREMENT_METHOD, regex, in));
    }

    public void addDisplayStream(PrintStream out) {
        DisplayStream displayStream = makeDisplayStream(out, count,
                Count.GET_VALUE_METHOD);

        ObserverAdaptor observer = new ObserverAdaptor(displayStream, DisplayStream.PRINT_VALUE_METHOD);
        observable.addObserver(observer);
        components.add(displayStream);

    }

    private DisplayStream makeDisplayStream(PrintStream out, Object aModel,
            String action) {
        ValueModelAdaptor adaptor = new ValueModelAdaptor(aModel, action,
                new Object[] {});

        DisplayStream displayStream = new DisplayStream(out, adaptor);
        return displayStream;
    }

    private StreamListener makeStreamListenerOn(Object aModel, String anAction,
            String regex, InputStream in) {
        PluggableAdaptor aCommand = new PluggableAdaptor(aModel, anAction,
                new Object[] {});

        return new StreamListener(aCommand, in, regex);
    }

    private boolean add(StreamListener makeStreamListener) {
        return components.add(makeStreamListener);
    }
}
