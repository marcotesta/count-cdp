package it.mondogrua.console_count;

import java.io.BufferedWriter;
import java.io.IOException;

import it.mondogrua.utils.ValueModel;

public class ValueWriter {

    public static final String PRINT_VALUE_METHOD = "printValue";

    private BufferedWriter out;
    private ValueModel valueModel;

    public ValueWriter(BufferedWriter out, ValueModel aValueModel) {
        this.out = out;
        this.valueModel = aValueModel;
        printValue();
    }

    public void printValue() {
        try {
            out.write(valueModel.getValue());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
