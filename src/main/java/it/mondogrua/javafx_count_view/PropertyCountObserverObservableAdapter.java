package it.mondogrua.javafx_count_view;

import it.mondogrua.count.Count;
import it.mondogrua.count.CountObserver;
import it.mondogrua.count.ObservableCount;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

public class PropertyCountObserverObservableAdapter extends SimpleStringProperty
        implements CountObserver, Count {

    private Count count;

    public PropertyCountObserverObservableAdapter() {
        super();
        set("");
    }

    @Override
    public void setSubject(ObservableCount aCount) {
        this.count = aCount;
        setValue(retrieveValue());
        aCount.addCountObserver(this);
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                setValue(retrieveValue());
            }
        });
    }

    @Override
    public void reset() {
        count.reset();
    }

    @Override
    public void increment() {
        count.increment();
    }

    @Override
    public void decrement() {
        count.decrement();
    }

    @Override
    public Object getCountValue() {
        return getValue();
    }

    private String retrieveValue() {
        return count.getCountValue().toString();
    }
}
