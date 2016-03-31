package it.mondogrua.console_count;

import static it.mondogrua.count.Count.DECREMENT_METHOD;
import static it.mondogrua.count.Count.INCREMENT_METHOD;
import static it.mondogrua.count.Count.RESET_METHOD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.mondogrua.count.Count;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.utils.ObserverAdaptor;
import it.mondogrua.utils.PluggableAdaptor;
import it.mondogrua.utils.ValueModel;
import it.mondogrua.utils.ValueModelAdaptor;

public class ConsoleBuilder {

    private List<Object> components = new ArrayList<Object>();
    private ObservableCount count;

    public ConsoleBuilder(ObservableCount aCount) {
        super();
        this.count = aCount;
    }

    public void addResetStreamListener(String regex, BufferedReader in) {
        add(makeCommandReaderOn(count, RESET_METHOD, regex, in));
    }

    public void addIncrementStreamListener(String regex, BufferedReader in) {
        add(makeCommandReaderOn(count, INCREMENT_METHOD, regex, in));
    }

    public void addDecrementStreamListener(String regex, BufferedReader in) {
        add(makeCommandReaderOn(count, DECREMENT_METHOD, regex, in));
    }

    public void addDisplayStream(BufferedWriter out) {
        ValueWriter displayStream = makeDisplayWriter(out, count,
                Count.GET_VALUE_METHOD);

        count.addObserver(new ObserverAdaptor(displayStream,
                ValueWriter.PRINT_VALUE_METHOD));
        components.add(displayStream);
    }

    private ValueWriter makeDisplayWriter(BufferedWriter out, Object aModel,
            String action) {
        ValueModel aValueModel = new ValueModelAdaptor(aModel, action);
        return new ValueWriter(out, aValueModel);
    }

    private LineReader makeCommandReaderOn(Object aModel, String anAction,
            String regex, BufferedReader in) {

        PluggableAdaptor command = new PluggableAdaptor(aModel, anAction);
        CommandTrigger commandTrigger = new CommandTrigger(command);

        StringFilter commandFilter = new StringFilter(regex);
        commandFilter.addListener(commandTrigger);

        LineReader commandReader = new LineReader(in);
        commandReader.addListener(commandFilter);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {

            @Override
            public void run() {
                commandReader.readLines();
            }});

        return commandReader;
    }

    private boolean add(LineReader makeStreamListener) {
        return components.add(makeStreamListener);
    }
}
