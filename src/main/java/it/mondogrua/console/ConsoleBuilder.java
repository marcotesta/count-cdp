package it.mondogrua.console;

import static it.mondogrua.count.Count.DECREMENT_METHOD;
import static it.mondogrua.count.Count.INCREMENT_METHOD;
import static it.mondogrua.count.Count.RESET_METHOD;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.TeeInputStream;

import it.mondogrua.count.ObservableCount;
import it.mondogrua.utils.PluggableAdaptor;

public class ConsoleBuilder {

    private List<Object> components = new ArrayList<Object>();
    private ObservableCount count;

    public ConsoleBuilder(ObservableCount count) {
        super();
        this.count = count;
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
        components.add(makeDisplayStream(count, out));
    }

    private DisplayStream makeDisplayStream(ObservableCount count,
            PrintStream out) {
        DisplayStream displayStream = new DisplayStream(out);
        count.addCountObserver(displayStream);
        return displayStream;
    }

    private StreamListener makeStreamListener(Object aModel, String anAction,
            String regex, InputStream in) {
        PluggableAdaptor aCommand = new PluggableAdaptor(aModel, anAction,
                new Object[] {});

        return new StreamListener(aCommand, in, regex);
    }

    public InputStream[] split(InputStream in) {
        InputStream[] ret;
        try {

            PipedInputStream pins = new PipedInputStream();
            InputStream teeInputStream = new TeeInputStream(in,
                    new PipedOutputStream(pins));

            ret = new InputStream[] { teeInputStream, pins };
        } catch (IOException e) {
            ret = new InputStream[] { in, in };
        }
        return ret;
    }

    private boolean add(StreamListener makeStreamListener) {
        return components.add(makeStreamListener);
    }
}
