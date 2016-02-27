package it.mondogrua.console;

import java.io.PrintStream;

import it.mondogrua.count.CountObserver;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.utils.ValueModel;

public class DisplayStream implements CountObserver {

    private PrintStream out;
    private ValueModel valueModel;

    public DisplayStream(PrintStream out, ValueModel aValueModel) {
        this.out = out;
        this.valueModel = aValueModel;
        printValue();
    }

    @Override
    public void setSubject(ObservableCount aCount) {
        aCount.addCountObserver(this);
    }

    @Override
    public void update() {
        printValue();
    }

    private void printValue() {
        out.println(valueModel.getValue());
    }
}
