package it.mondogrua.count;

public class IntegerCount extends AbstractCount {

    public IntegerCount() {
        super();
    }

    @Override
    protected void initialize() {
        setResetValue(0);
        super.initialize();
    };

    @Override
    public void increment() {
        setModelValue((Integer) getCountValue() + 1);
    }

    @Override
    public void decrement() {
        setModelValue((Integer) getCountValue() - 1);
    }
}
