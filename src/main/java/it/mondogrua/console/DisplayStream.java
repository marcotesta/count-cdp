package it.mondogrua.console;

import java.io.PrintStream;

import it.mondogrua.utils.ValueModel;

public class DisplayStream  {

    public static final String PRINT_VALUE_METHOD = "printValue";

    private PrintStream out;
    private ValueModel valueModel;

    public DisplayStream(PrintStream out, ValueModel aValueModel) {
        this.out = out;
        this.valueModel = aValueModel;
        printValue();
    }

    public void printValue() {
        out.println(valueModel.getValue());
    }
}
