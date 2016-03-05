package it.mondogrua.javafx_count_view;

import it.mondogrua.count.Count;
import javafx.beans.property.SimpleStringProperty;

public class SimpleStringPropertyCount extends SimpleStringProperty implements
        Count {

    private Count count;

    private SimpleStringPropertyCount(Count aCount) {
        super();
        this.count = aCount;
    }

    public static SimpleStringPropertyCount create(
            SimpleStringProperty simpleStringProperty, Count aCount) {

        SimpleStringPropertyCount simpleStringPropertyCount =
                new SimpleStringPropertyCount(aCount);

        simpleStringPropertyCount.bind(simpleStringProperty);

        return simpleStringPropertyCount;
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
        return count.getCountValue();
    }
}
