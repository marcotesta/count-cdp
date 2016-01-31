package it.mondogrua.count;

import it.mondogrua.utils.Date;

public class DateCount extends AbstractCount {

    public DateCount() {
        super();
    }

    @Override
    protected void initialize() {
        setResetValue(new Date());
        super.initialize();
    };


    @Override
    public void increment() {
        setModelValue(((Date)getCountValue()).addDays(1));
    }

    @Override
    public void decrement() {
        setModelValue(((Date)getCountValue()).addDays(-1));
    }

}
