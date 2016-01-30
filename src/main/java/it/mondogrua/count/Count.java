package it.mondogrua.count;

import it.mondogrua.utils.ValueModel;

public interface Count extends ValueModel {
	
	static final String INCREMENT_METHOD = "increment";
	static final String DECREMENT_METHOD = "decrement";
	static final String RESET_METHOD = "reset";


	Object getResetValue();

	void setResetValue(Object aResetValue);

	void reset();

	void increment();

	void decrement();
	
	public Object getModelValue() ;

	void setModelValue(Object aValue);
}