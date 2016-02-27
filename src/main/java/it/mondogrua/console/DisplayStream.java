package it.mondogrua.console;

import java.io.PrintStream;

import it.mondogrua.utils.Observer;
import it.mondogrua.utils.ValueModel;

public class DisplayStream implements Observer {

    private PrintStream out;
    private ValueModel valueModel;

    public DisplayStream(PrintStream out, ValueModel aValueModel) {
        this.out = out;
        this.valueModel = aValueModel;
        printValue();
    }

    @Override
    public void update() {
        printValue();
    }

    private void printValue() {
        out.println(valueModel.getValue());
    }
}
