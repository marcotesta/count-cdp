package it.mondogrua.console;

import static it.mondogrua.count.Count.DECREMENT_METHOD;
import static it.mondogrua.count.Count.INCREMENT_METHOD;
import static it.mondogrua.count.Count.RESET_METHOD;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import it.mondogrua.count.Count;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.utils.PluggableAdaptor;
import it.mondogrua.utils.ValueModelAdaptor;

public class ConsoleBuilder {

    private List<Object> components = new ArrayList<Object>();
    private ObservableCount observable;
    private Count count;

    public ConsoleBuilder(ObservableCount observable, Count aCount) {
        super();
        this.observable = observable;
        this.count = aCount;
    }

    public void addResetStreamListener(String regex, InputStream in) {
        add(makeStreamListener(count, RESET_METHOD, regex, in));
    }

    public void addIncrementStreamListener(String regex, InputStream in) {
        add(makeStreamListener(count, INCREMENT_METHOD, regex, in));
    }

    public void addDecrementStreamListener(String regex, InputStream in) {
        add(makeStreamListener(count, DECREMENT_METHOD, regex, in));
    }

    public void addDisplayStream(PrintStream out) {
        components.add(makeDisplayStream(out, observable,
                Count.GET_VALUE_METHOD));
    }

    private DisplayStream makeDisplayStream(PrintStream out,
            ObservableCount observable, String action) {
        ValueModelAdaptor adaptor = new ValueModelAdaptor(observable, action,
                new Object[] {});

        DisplayStream displayStream = new DisplayStream(out, adaptor);
        displayStream.setSubject(observable);
        return displayStream;
    }

    private StreamListener makeStreamListener(Object aModel, String anAction,
            String regex, InputStream in) {
        PluggableAdaptor aCommand = new PluggableAdaptor(aModel, anAction,
                new Object[] {});

        return new StreamListener(aCommand, in, regex);
    }

    private boolean add(StreamListener makeStreamListener) {
        return components.add(makeStreamListener);
    }
}
