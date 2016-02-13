package it.mondogrua.count;

public abstract class AbstractCount implements Count {

    private Object _value;
    private Object _resetValue;

    public AbstractCount() {
        initialize();
    }

    protected void initialize() {
        reset();
    }

    @Override
    public Object getCountValue() {
        return _value;
    }

    public void setModelValue(Object aValue) {
        _value = aValue;
    }

    @Override
    public void reset() {
        setModelValue(_resetValue);
    }

    @Override
    abstract public void increment();

    @Override
    abstract public void decrement();

    protected void setResetValue(Object aResetValue) {
        _resetValue = aResetValue;
    }
}
