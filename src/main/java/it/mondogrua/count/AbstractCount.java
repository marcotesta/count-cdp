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
    public Object getValue() {
        return _value;
    }

	@Override
	public void setValue(Object aValue) {
        _value = aValue;
    }

	@Override
	public Object getResetValue() {
        return _resetValue;
    }

    @Override
	public void setResetValue(Object aResetValue) {
        _resetValue = aResetValue;
    }

    @Override
	public void reset() {
        setValue(_resetValue);
    }

    @Override
	abstract public void increment();

    @Override
	abstract public void decrement();
}
