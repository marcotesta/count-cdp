package it.mondogrua.console;

import java.io.PrintStream;

import it.mondogrua.count.Count;
import it.mondogrua.count.CountObserver;
import it.mondogrua.count.ObservableCount;

public class DisplayStream implements CountObserver {

    private PrintStream out;
    private Count count;

    public DisplayStream(PrintStream out) {
        this.out = out;
    }

    @Override
    public void update() {
        printValue();
    }

    private void printValue() {
        out.println(retrieveValue());
    }

    @Override
    public void setSubject(ObservableCount aCount) {
        this.count = aCount;
        printValue();
        aCount.addCountObserver(this);
    }

    private String retrieveValue() {
        if (count == null) {
            return "";
        }
        return count.getCountValue().toString();
    }
}
